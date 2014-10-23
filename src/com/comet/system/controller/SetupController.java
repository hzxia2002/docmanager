package com.comet.system.controller;

import com.comet.core.utils.DBUtils;
import com.comet.core.utils.SystemInfo;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: dell
 * Date: 11-8-9
 * Time: 下午11:12
 * Description: <p>安装程序Controller</p>
 *
 * @Version 1.0
 */
@Controller
public class SetupController {
    private static String DB_TYPE_ORACLE = "oracle";
    private static String DB_TYPE_MSSQL = "mssql";

    private static String ORACLE_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static String ORACLE_CONN_STRING = "jdbc:oracle:thin:@{0}:{1}:{2}"; // jdbc:oracle:thin:@localhost:1521:orcl

    private static String MSSQL_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static String MSSQL_CONN_STRING = "jdbc:sqlserver:{0}:{1};DatabaseName={2}"; // jdbc:sqlserver://192.168.1.5:1433;DatabaseName=weblog

    @RequestMapping
    public String init() throws Exception {
        return "view/setup/index";
    }

    /**
     * 校核LicenceKey的有效性
     *
     * @param lincenceKey
     * @return
     */
    @RequestMapping
    @ResponseBody
    public Map<String, String> validateLicence(@RequestParam("licenceKey") String lincenceKey) {
        Map<String, String> map = new HashMap<String, String>();

        if (StringUtils.equals(lincenceKey, "12345678")) {
            map.put("result", "true");
        } else {
            map.put("result", "false");
        }

        return map;
    }

    /**
     * 校核数据库连接的有效性
     *
     * @param dbType
     * @param dbIp
     * @param dbPort
     * @param dbName
     * @param dbUserName
     * @param dbPassword
     * @return
     */
    @RequestMapping
    @ResponseBody
    public Map<String, String> validateDbConnection(@RequestParam("dbType") String dbType,
                                                    @RequestParam("dbIp") String dbIp,
                                                    @RequestParam("dbPort") String dbPort,
                                                    @RequestParam("dbName") String dbName,
                                                    @RequestParam("dbUserName") String dbUserName,
                                                    @RequestParam("dbPassword") String dbPassword) {
        Map<String, String> map = new HashMap<String, String>();

        String connString = "";
        String driverName = "";

        if (dbType.equals(this.DB_TYPE_ORACLE)) {
            connString = MessageFormat.format(this.ORACLE_CONN_STRING, dbIp, dbPort, dbName);
            driverName = this.ORACLE_DRIVER;
        } else {
            connString = MessageFormat.format(this.MSSQL_CONN_STRING, dbIp, dbPort, dbName);
            driverName = this.MSSQL_DRIVER;
        }

        // 建立数据库连接测试
        try {
            DBUtils.getConnection(driverName, connString, dbUserName, dbPassword);

            DBUtils.close(true);

            map.put("result", "true");
        } catch (Exception e) {
            e.printStackTrace();

            map.put("result", "false");
            map.put("msg", "取得数据库连接失败:" + e.getLocalizedMessage());
        }

        return map;
    }

    /**
     * 初始化数据库
     *
     * @param dbType
     * @param dbIp
     * @param dbPort
     * @param dbName
     * @param dbUserName
     * @param dbPassword
     * @return
     */
    @RequestMapping
    @ResponseBody
    public Map<String, String> initDb(HttpServletRequest request,
                                      @RequestParam("dbType") String dbType,
                                      @RequestParam("dbIp") String dbIp,
                                      @RequestParam("dbPort") String dbPort,
                                      @RequestParam("dbName") String dbName,
                                      @RequestParam("dbUserName") String dbUserName,
                                      @RequestParam("dbPassword") String dbPassword) {
        Map<String, String> map = new HashMap<String, String>();

        String driverName = this.getDriver(dbType);
        String connString = this.getConnString(dbType, dbIp, dbPort, dbName);

        // 建立数据库连接测试
        try {
            DBUtils.getConnection(driverName, connString, dbUserName, dbPassword);
            DBUtils.getStatement();

            // 取得数据库文件
            System.out.println(request.getRealPath("WEB-INF/sql/create_table.sql"));

            String filePath = request.getRealPath("WEB-INF/sql/create_table.sql");
            String sqlAll = FileUtils.readFileToString(new File(filePath));

            String[] sqlArray = StringUtils.split(sqlAll, ";");

            for (String sql : sqlArray) {
                DBUtils.execute(sql);
            }

            filePath = request.getRealPath("WEB-INF/application.properties");

            // 书写数据库连接字符串
            this.initDbProperties(dbType, dbIp, dbPort, dbName, dbUserName, dbPassword, filePath);

            map.put("result", "true");
        } catch (Exception e) {
            e.printStackTrace();

            map.put("result", "false");
            map.put("msg", "初始化数据库失败:" + e.getMessage());
        } finally {
            DBUtils.close(false);
        }

        return map;
    }

    /**
     * 导入数据
     *
     * @param request
     * @param dbType
     * @param dbIp
     * @param dbPort
     * @param dbName
     * @param dbUserName
     * @param dbPassword
     * @return
     */
    @RequestMapping
    @ResponseBody
    public Map<String, String> importData(HttpServletRequest request,
                                          @RequestParam("dbType") String dbType,
                                          @RequestParam("dbIp") String dbIp,
                                          @RequestParam("dbPort") String dbPort,
                                          @RequestParam("dbName") String dbName,
                                          @RequestParam("dbUserName") String dbUserName,
                                          @RequestParam("dbPassword") String dbPassword) {
        Map<String, String> map = new HashMap<String, String>();

        String driverName = this.getDriver(dbType);
        String connString = this.getConnString(dbType, dbIp, dbPort, dbName);

        // 建立数据库连接测试
        try {
            DBUtils.getConnection(driverName, connString, dbUserName, dbPassword);
            DBUtils.getStatement();

            // 取得数据库文件
            String filePath = request.getRealPath("WEB-INF/sql/create_table.sql");
            String sqlAll = FileUtils.readFileToString(new File(filePath));

            String[] sqlArray = StringUtils.split(sqlAll, ";");

            for (String sql : sqlArray) {
                DBUtils.execute(sql);
            }

            // 取得系统版本
            String version = SystemInfo.getVersion();

            map.put("systemVersion", version);
            map.put("result", "true");
        } catch (Exception e) {
            e.printStackTrace();

            map.put("result", "false");
            map.put("msg", "初始化数据库失败:" + e.getMessage());
        } finally {
            DBUtils.close(false);
        }

        return map;
    }

    /**
     * 重命名web.xml
     *
     * @param request
     * @return
     */
    @RequestMapping
    @ResponseBody
    public Map<String, String> renameWebXml(HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();

        String filePath = request.getRealPath("WEB-INF");

        try {
            File webFile = new File(filePath + File.separator + "web-backup.xml");
            File backupFile = new File(filePath + File.separator + "web.xml_bak");
            File file = new File(filePath + File.separator + "web.xml");

            FileUtils.copyFile(file, backupFile);
            FileUtils.copyFile(webFile, file);
        } catch (Exception e) {
            e.printStackTrace();

            map.put("result", "false");
            map.put("msg", "初始化web.xml文件失败:" + e.getMessage());
        }

        map.put("result", "true");
        return map;
    }

    /**
     * 初始化数据库属性配置文件
     *
     * @param dbType
     * @param dbIp
     * @param dbPort
     * @param dbName
     * @param dbUserName
     * @param dbPassword
     */
    protected void initDbProperties(String dbType, String dbIp, String dbPort, String dbName, String dbUserName, String dbPassword, String filePath) {
        try {
            File file = new File(filePath);

            String driveName = this.getDriver(dbType);
            String connString = this.getConnString(dbType, dbIp, dbPort, dbName);

            String initString = FileUtils.readFileToString(file);

            Collection lines = new ArrayList();

            lines.add("driverClass=" + driveName);
            lines.add("jdbcUrl=" + connString);
            lines.add("user=" + dbUserName);
            lines.add("password=" + dbPassword);
            lines.add(initString);

            FileUtils.writeLines(file, lines);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 取得驱动类型
     *
     * @param dbType
     * @return
     */
    protected String getDriver(String dbType) {
        String driveName = "";

        if (dbType.equals(this.DB_TYPE_ORACLE)) {
            return this.ORACLE_DRIVER;
        } else {
            return this.MSSQL_DRIVER;
        }
    }

    /**
     * 取得数据库连接字符串
     *
     * @param dbType
     * @param dbIp
     * @param dbPort
     * @param dbName
     * @return
     */
    protected String getConnString(String dbType, String dbIp, String dbPort, String dbName) {
        if (dbType.equals(this.DB_TYPE_ORACLE)) {
            return MessageFormat.format(this.ORACLE_CONN_STRING, dbIp, dbPort, dbName);
        } else {
            return MessageFormat.format(this.MSSQL_CONN_STRING, dbIp, dbPort, dbName);
        }
    }
}
