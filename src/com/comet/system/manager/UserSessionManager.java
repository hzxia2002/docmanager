package com.comet.system.manager;

import com.comet.system.daoservice.SysUserService;
import com.comet.system.domain.SysUser;
import com.comet.core.security.user.BaseUser;
import com.comet.core.security.util.SpringSecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Project:bcscmis
 *
 * <p>
 * </p>
 *
 * Create On 2009-12-25 下午05:49:23
 *
 * @author <a href="mailto:hzxia2002@gmail.com">XiaHongzhong</a>
 * @version 1.0
 */
@Service
public class UserSessionManager {
	@Autowired
	private SysUserService sysUserService;

	/**
	 * 取得当前登录用户Id
	 *
	 * @return 登录用户id
	 */
	public Long getLoginedUserId() {
		BaseUser loginUser = SpringSecurityUtils.getCurrentUser();

		if (loginUser != null) {
			return loginUser.getId();
		}

		return null;
	}

	/**
	 * 取得当前登录的SysUser对象
	 *
	 * @return 当前登录用户
	 */
	public SysUser getLoginedUser() {
		Long userId = getLoginedUserId();

		if (userId != null) {
			return sysUserService.get(userId);
		}

		return null;
	}
}
