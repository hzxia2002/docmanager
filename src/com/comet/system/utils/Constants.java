package com.comet.system.utils;

/**
 * Created with IntelliJ IDEA.
 * User: dell
 * Date: 12-4-5
 * Time: 下午10:31
 * To change this template use File | Settings | File Templates.
 */
public interface Constants {
    /**
     * 日志分类-登录
     */
    public static final String LOG_TYPE_CODE = "LOG_TYPE";

    /**
     * 日志分类-登录
     */
    public static final String LOG_TYPE_LOGIN = "1";

    /**
     * 日志分类-正常
     */
    public static final String LOG_TYPE_NORMAL = "2";

    /**
     * 日志分类-错误
     */
    public static final String LOG_TYPE_ERROR = "3";

    /**
     * 日志分类-退出
     */
    public static final String LOG_TYPE_LOGOUT = "4";

    /**
     * 日志分类-web service
     */
    public static final String LOG_TYPE_WS = "5";

    public static final String ROLE_SYS_ADMIN = "ROLE_SYS_ADMIN"; // 系统管理员

    public static final String ROLE_USER = "ROLE_USER"; // 站点用户

    public static final String PROJECT_WAITING = "1"; // 等待分配
    public static final String PROJECT_START = "2"; // 分配完成，项目进行中
    public static final String PROJECT_COMPLETED = "3"; // 项目已完工

    public static final String TASK_DOING = "1"; // 翻译进行中
    public static final String TASK_WAIT_FOR_REVIEW = "2";   // 翻译完成等待审稿
    public static final String TASK_REDOING = "3"; // 重新翻译
    public static final String TASK_COMPLETED = "4"; // 翻译结束

    public static final String COM_MANAGER = "0";   //  管理公司
    public static final String COM_CLIENT = "1";   //   客户公司
    public static final String COM_TRANSFERS = "2"; // 翻译人员团体

    public static final String USER_STATUS_NORMAL = "1"; // 用户状态，正常

    public static final String USER_STATUS_LOCKED = "2"; // 用户状态，锁定

    public static final String USER_STATUS_DISABLED = "0"; // 用户状态，失效

    public static final String USER_STATUS_UNACTIVED = "4"; // 用户状态，未激活

    // 会员
    public static final String  MEMBER = "11";

    public static String[] COMPANY_TYPE_NAME = {"中信信托","合资公司","合作伙伴","会员","销售单位","权益消费地点"};
    public static String[] COMPANY_TYPE = {"0","1","2","3","4","5"};
}
