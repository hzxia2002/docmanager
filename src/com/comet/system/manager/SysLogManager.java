package com.comet.system.manager;

import com.comet.system.daoservice.SysLogService;
import com.comet.system.daoservice.SysUserService;
import com.comet.system.domain.SysLog;
import com.comet.system.domain.SysUser;
import com.comet.system.utils.Constants;
import com.comet.core.security.count.UserLogger;
import com.comet.core.utils.DateTimeHelper;
import com.comet.core.webservice.security.WSLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * Project:bcscmis
 *
 * <p>
 * 系统日志逻辑处理类
 * </p>
 *
 * Create On 2009-12-19 下午04:24:35
 *
 * @author <a href="mailto:hzxia2002@gmail.com">XiaHongzhong</a>
 * @version 1.0
 */
@Service("userLogger")
public class SysLogManager implements UserLogger, WSLogService {
	@Autowired
	private SysLogService sysLogService;

	@Autowired
	private SysUserService sysUserService;

    @Autowired
    private SysUserManager sysUserManager;

	@Autowired
	private SysCodeManager sysCodeManager;

	/**
	 * 保存日志
	 *
	 * @param bean
	 *            SysLog
	 */
	public void save(SysLog bean) {
		// bean.setLogTime(new Timestamp(System.currentTimeMillis()));

		sysLogService.save(bean);
	}

	/**
	 * 保存日志信息
	 *
	 * @param id
	 *            用户ID
	 * @param ipAddress
	 *            ip地址
	 * @param logType
	 *            日志类型
	 */
	public void save(Long id, String ipAddress, String logType) {
		SysUser user = sysUserService.get(id);

		this.save(user, ipAddress, logType);
	}

	/**
	 * 保存日志
	 *
	 * @param loginUser
	 *            登录用户
	 * @param ipAddress
	 *            用户地址
	 * @param logType
	 *            日志类型
	 */
	public void save(SysUser loginUser, String ipAddress, String logType) {
		SysLog bean = new SysLog();

		bean.setIpAddress(ipAddress);
		bean.setLogType(sysCodeManager.getCodeListByCode(
				Constants.LOG_TYPE_CODE, logType));
		bean.setUser(loginUser);

		this.save(bean);
	}

	public void log(Long id, String ipAddress, String logType) {
		save(id, ipAddress, logType);
	}

    /**
     * 记录日志
     *
     * @param username
     * @param serviceName
     * @param userIp
     * @param sessionId
     * @param logType
     */
    public void log(String username, String serviceName, String userIp, String sessionId, String logType) {
        SysUser user = sysUserManager.getByUsername(username);

        SysLog bean = new SysLog();

        bean.setPageUrl(serviceName);
        bean.setSessionid(sessionId);
        bean.setIpAddress(userIp);
        bean.setEnterTime(DateTimeHelper.getTimestamp());

        bean.setLogType(sysCodeManager.getCodeListByCode(
                Constants.LOG_TYPE_CODE, logType));
        bean.setUser(user);

        this.save(bean);
    }

    /**
     * 记录Web Service日志
     *
     * @param username
     * @param serviceName
     */
    public void log(String username, String serviceName) {
        this.log(username, serviceName, null, null);
    }

    /**
     * 记录Web Service日志
     *
     * @param username
     * @param serviceName
     * @param userIp
     * @param sessionId
     */
    public void log(String username, String serviceName, String userIp, String sessionId) {
        this.log(username, serviceName, userIp, sessionId, Constants.LOG_TYPE_WS);
    }
}
