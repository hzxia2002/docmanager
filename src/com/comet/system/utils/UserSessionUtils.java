package com.comet.system.utils;

import com.comet.system.domain.SysUser;
import com.comet.system.manager.UserSessionManager;
import com.comet.core.utils.SpringUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Project:bcscmis
 *
 * <p>
 * </p>
 *
 * Create On 2010-1-13 下午11:17:57
 *
 * @author <a href="mailto:hzxia2002@gmail.com">XiaHongzhong</a>
 * @version 1.0
 */
public class UserSessionUtils {
	private static final Logger log = LoggerFactory.getLogger(UserSessionUtils.class);

    // 有效
    public static String USER_STATUS_VALID = "1";

    // 无效
    public static String USER_STATUS_INVALID = "0";

    // 锁定
    public static String USER_STATUS_LOCKED = "2";

    // 未激活
    public static String USER_STATUS_UNACTIVATED = "4";

	private static UserSessionUtils instance = null;

	/**
	 * 构造函数
	 */
	private UserSessionUtils() {

	}

	/**
	 * 取得UserSessionUtils的实例
	 *
	 * @return UserSessionUtils实例
	 */
	public static UserSessionUtils getInstance() {
		if (instance == null) {
			instance = new UserSessionUtils();
		}

		return instance;
	}

	/**
	 * 获取UserSession bean
	 *
	 * @return the userSession
	 */
	public UserSessionManager getUserSession() {
		try {
//			return (UserSessionManager) getApplicationContext().getBean("userSessionManager");
			return (UserSessionManager) SpringUtils.getBean("userSessionManager");
		} catch (Exception e) {
			log.error("error", e);
		}

		return null;
	}

	/**
	 * 取得当前登录用户Id
	 *
	 * @return 登录用户id
	 */
	public Long getLoginedUserId() {
		return getUserSession().getLoginedUserId();
	}

	/**
	 * 取得当前登录的SysUser对象
	 *
	 * @return 当前登录用户
	 */
	public SysUser getLoginedUser() {
		return getUserSession().getLoginedUser();
	}

    /**
     * 用户是否有效
     *
     * @param user
     * @return
     */
    public Boolean isUserValid(SysUser user) {
        if(StringUtils.equals(user.getStatus(), this.USER_STATUS_VALID)) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    /**
     * 用户是否无效
     *
     * @param user
     * @return
     */
    public Boolean isUserInvalid(SysUser user) {
        if(StringUtils.equals(user.getStatus(), this.USER_STATUS_INVALID)) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    /**
     * 用户是否锁定
     *
     * @param user
     * @return
     */
    public Boolean isUserLocked(SysUser user) {
        if(StringUtils.equals(user.getStatus(), this.USER_STATUS_LOCKED)) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public Boolean isUserUnactivated(SysUser user) {
        if(StringUtils.equals(user.getStatus(), this.USER_STATUS_UNACTIVATED)) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }
}