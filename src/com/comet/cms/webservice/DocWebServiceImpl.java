package com.comet.cms.webservice;

import com.comet.cms.domain.CmsArticle;
import com.comet.cms.domain.CmsTask;
import com.comet.cms.manager.CmsTaskManager;
import com.comet.core.orm.hibernate.Page;
import com.comet.core.utils.CryptUtil;
import com.comet.core.utils.DateTimeHelper;
import com.comet.core.utils.SpringUtils;
import com.comet.cms.model.DataResults;
import com.comet.system.domain.SysDept;
import com.comet.system.domain.SysUser;
import com.comet.system.manager.SysUserManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;

import javax.jws.WebService;
import java.util.*;

/**
 * Created by dell on 2014/10/24.
 */
@WebService
public class DocWebServiceImpl implements DocWebService {
    private static SysUserManager sysUserManager;

    private static CmsTaskManager cmsTaskManager;

    /**
     * 公文待办信息：公司收文、公司发文、公司签报文、部门收文、部门发文
     */
    public static int ArcFileWaitingWork = 1;
    /**
     * 公文已办信息：公司收文、公司发文、公司签报文、部门收文、部门发文
     */
    public static int ArcFileDoneWork = 2;
    /**
     * 日常安排
     */
    public static int DailySchedule = 8;

    static {
        sysUserManager = (SysUserManager)SpringUtils.getBean("sysUserManager");
        cmsTaskManager = (CmsTaskManager)SpringUtils.getBean("cmsTaskManager");
    }

    /**
     * 将DataResults转换为JSON字符串
     *
     * @param dataResults
     * @return
     */
    private String dataResultsToJsonStr(DataResults dataResults) {
        JSONObject jsonObj = JSONObject.fromObject(dataResults);
        if (jsonObj != null && !jsonObj.isNullObject() && !jsonObj.isEmpty()) {
            String jsonArrayStr = jsonObj.toString();
            System.out.println(jsonArrayStr);
            return jsonArrayStr;
        }
        return null;
    }

    /**
     * 将List转换为JSON字符串
     *
     * @param obj
     * @return
     */
    private String listToJsonStr(List obj) {
        JSONArray jsonObj = JSONArray.fromObject(obj);
        if (jsonObj != null && !jsonObj.isEmpty()) {
            String jsonArrayStr = jsonObj.toString();
            return jsonArrayStr;
        }
        return null;
    }

    @Override
    public String login(String userid, String password) throws Exception {
        DataResults dataResults = new DataResults();
        List list = new ArrayList();
        Map map=new HashMap();

        SysUser user = sysUserManager.getByUsername(userid);

        if(user == null) {
            map.put("result", "当前用户不存在");
            map.put("resulttype", "0");
        } else {
            if(!CryptUtil.decrypt(user.getPassword()).equals(password)) {
                map.put("result", "您输入的口令不正确");
                map.put("resulttype", "0");
            } else {
                map.put("resulttype", "1");
                map.put("userid", userid);
                map.put("username", user.getDisplayName());

                // 取得用户所在的部门
                SysDept dept = sysUserManager.getUserDept(user.getId());
                if(dept != null) {
                    map.put("userdeptName", dept.getName());
                } else {
                    map.put("userdeptName", "");
                }
            }
        }

        list.add(map);
        dataResults.setResultDataDesc("查询成功");
        dataResults.setResultList(list);
        return dataResultsToJsonStr(dataResults);
    }

    /**
     * 查询公文种类
     *
     * @return
     */
    public String getfilekind(){
        List list=new ArrayList();
        Map map=new HashMap();
        Map map1=new HashMap();
        Map map2=new HashMap();
        Map map3=new HashMap();
        Map map4=new HashMap();
        map.put("title", "公司收文");
        map.put("value", "ReceiveFile");
        map1.put("title", "公司发文");
        map1.put("value", "SendFile");
        map2.put("title", "内呈文");
        map2.put("value", "SignFile");
        map3.put("title", "协作函");
        map3.put("value", "DeptSendFile");
        map4.put("title", "部门收文");
        map4.put("value", "DeptReceiveFile");
        list.add(map);
        list.add(map1);
        list.add(map2);
        list.add(map3);
        list.add(map4);
        DataResults dataResults = new DataResults();
        dataResults.setResultDataDesc("文件类型查询成功");
        dataResults.setResultList(list);
        String ret = dataResultsToJsonStr(dataResults);

        return ret;
    }

    /**
     * 为手机网站得到业务信息列表
     *
     * @param corpID
     *            单位ID
     * @param empId
     *            员工ID
     * @param type
     *            信息类别
     * @param rowsOfpage 每页记录数
     * @param synDate
     *            分页查询时间基准
     * @param numpage
     *            显示第几页
     * @param whereSQL
     *            名称查询条件：name=&title=&content=&......
     * @return 信息列表DataMessage[]
     * @throws Exception
     */
    public String getDataMessageList(String corpID, String empId, String type,
                                     String synDate, String rowsOfpage, String numpage, String whereSQL)
            throws Exception {
        System.out.println("接口调用getDataMessageList....");

        if(StringUtils.isEmpty(type) || StringUtils.equals(type, "0")) {
            return null;
        }

        int typeInt = Integer.parseInt(type);

        // 信息类型
//		if (type == null || type.intValue() == 0)
//			return null;

        rowsOfpage = StringUtils.defaultIfEmpty(rowsOfpage, "100");
        numpage = StringUtils.defaultIfEmpty(numpage, "1");

        int rowsOfpageInt = Integer.parseInt(rowsOfpage);
        int numpageInt = Integer.parseInt(numpage);

//		if (rowsOfpage == null || numpage == null) {
//			numpage = new Integer(1);
//			rowsOfpage = new Integer(100);
//		}

        DataResults dataResults = new DataResults();
        long offset = (numpageInt - 1) * rowsOfpageInt;
        long limit = rowsOfpageInt;

        Page page = new Page();
        page.setPage(numpageInt);
        page.setPageSize(rowsOfpageInt);

        if (typeInt == ArcFileWaitingWork) {
            // 待办工作
            page = cmsTaskManager.queryTasks(page, "0");
        } else if (typeInt == ArcFileDoneWork) {
            // 已办工作
            page = cmsTaskManager.queryTasks(page, "1");
        }

        dataResults.setResultDataTime(DateTimeHelper.getDate(new Date()));

        ArrayList<Map> list = new ArrayList<Map>();

        List<CmsTask> rows = page.getRows();
        for (CmsTask task : rows) {
            HashMap hashMap = new HashMap();
            //公文名称
            CmsArticle article = task.getArticle();
            String title = article.getTitle();
            hashMap.put("fileTitle", title);

            ArrayList titleList = new ArrayList();
            HashMap valueMap = new HashMap();
            String content = article.getContent();
            valueMap.put("value", (content != null && content.length() > 10) ?(content.substring(0,10)+"..."):content);
            valueMap.put("title",title);
            valueMap.put("taskId",task.getId());
            titleList.add(valueMap);
            hashMap.put("base1", titleList);
            hashMap.put("base", titleList);

            //附件

            ArrayList attachments = new ArrayList();
            HashMap attachmentMap;
            String attachPath;
            String fileName;
            if(article.getAttachPath()!=null){
                attachmentMap = new HashMap();
                attachPath = article.getAttachPath();
                fileName = attachPath.substring(attachPath.indexOf("_") + 1);
                attachmentMap.put("attachname",fileName);
                attachmentMap.put("attachid",fileName);
                attachmentMap.put("urllink",attachPath);
                attachments.add(attachmentMap);
            }

            hashMap.put("attachments", attachments);
            list.add(hashMap);
        }

        dataResults.setResultList(list);
        dataResults.setResultDataType("1");
        dataResults.setResultDataDesc("查询数据成功！");

        // 服务器端计算是否有下一页

        if(page.getTotal()>page.getPage()){
            numpageInt = numpageInt + 1;
            dataResults.setNextpage(numpageInt);
        }else {
            dataResults.setNextpage(0);
        }
//        int rowCount = dataResults.getRowcount();
//        if((rowsOfpageInt * numpageInt) < rowCount){
//            numpageInt = numpageInt + 1;
//            dataResults.setNextpage(numpageInt);
//        }else{
//            dataResults.setNextpage(0);
//        }

        return dataResultsToJsonStr(dataResults);
    }

    /**
     * 得到已做的任务,综合查询，添加主要查询条件 文件标题F.TITLE AS "fileTitle",
     * 接收时间to_char(W.STDT,'yyyy-mm-dd hh24:mi:ss') AS "stdt", 主题词
     * 来文单位F.FILE_SOURCE AS "receiveCorp", 文件类型F.FILE_KIND1 AS "sendKind"
     *
     * @param empId
     * @return
     * @throws Exception
     * 文件标题，文件类型，来文单位，文件编号(流水号)， 接收时间，
     */
    public String findDoneTasks2(String empId, String synDate,
                                 String rowsOfpage, String numpage, String whereSQL,
                                 String receiveCorp, String filekind1, String fileTitle,
                                 String fileCode, String stdt1, String stdt2) throws Exception {
        try {
            System.out.println("进入findDoneTasks2");
            String jsonStr = "";
            Map map = new HashMap();
            if (!StringUtils.isEmpty(receiveCorp)) {
                map.put("receiveCorp", receiveCorp);
            }
            if (!StringUtils.isEmpty(filekind1)) {
                map.put("fileKind1", filekind1);
            }
            if (!StringUtils.isEmpty(fileCode)) {
                map.put("fileCode", fileCode);
            }
            if (!StringUtils.isEmpty(fileTitle)) {
                map.put("fileTitle", fileTitle);
            }
//            if (!StringUtils.isEmpty(stdt1)) {
//                map.put("stdt1", DateUtil.toDate(stdt1, DateUtil.DEFAULT_DATETIME_PATTERN));
//            }
//            if (!StringUtil.isEmpty(stdt2)) {
//                map.put("stdt2", DateUtil.toDate(stdt2, DateUtil.DEFAULT_DATETIME_PATTERN));
//            }
            System.out.println("准备执行findDoneTasks方法");

            int rowsOfpageInt = Integer.parseInt(rowsOfpage);
            int numpageInt = Integer.parseInt(numpage);

            jsonStr = findDoneTasks("", empId, synDate, "", 100, rowsOfpageInt,
                    numpageInt, map);

            System.out.println("进入findDoneTasks2，数据返回");
            return jsonStr;
        } catch (Exception e) {
            e.printStackTrace();
            DataResults dataResults = new DataResults();
            dataResults.setResultDataType("-1");
            dataResults.setResultDataDesc("请检查findDoneTasks2方法！" + e.getMessage());
            return dataResultsToJsonStr(dataResults);
        }
    }

    /**
     * 得到已做的任务
     *
     * @param empId
     * @return
     * @throws Exception
     */
    private String findDoneTasks(String corpID, String empId, String synDate,
                                 String alink, int width, int rowsOfpage, int numpage, Map map)
            throws Exception {
        long offset = (numpage - 1) * rowsOfpage;
        long limit = rowsOfpage;
//        DataResults dataResults = SWUtil.getFinsTasks(empId, "B.STDT DESC",
//                offset, limit, map);

        DataResults dataResults = new DataResults();

        //add  用于服务器端计算是否有下一页
        int rowCount = dataResults.getRowcount();
        if((rowsOfpage * numpage) < rowCount){
            numpage = numpage + 1;
            dataResults.setNextpage(numpage);
        }else{
            dataResults.setNextpage(0);
        }
        return dataResultsToJsonStr(dataResults);
    }
}
