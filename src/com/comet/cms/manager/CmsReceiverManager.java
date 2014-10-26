package com.comet.cms.manager;

import com.comet.cms.daoservice.CmsReceiverService;
import com.comet.system.daoservice.SysRoleService;
import com.comet.system.daoservice.SysUserService;
import com.comet.system.domain.SysRole;
import com.comet.system.domain.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dell
 * Date: 13-6-5
 * Time: 下午10:37
 * To change this template use File | Settings | File Templates.
 */
@Service
public class CmsReceiverManager {
    private static final Logger logger = LoggerFactory
            .getLogger(CmsReceiverManager.class);

    @Autowired
    private CmsReceiverService cmsReceiverService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysUserService sysUserService;

    /**
     * 查询已经选中的角色
     *
     * @param articleId
     * @return
     */
    public List<SysRole> querySelectedRoles(Long articleId) {
        String hql = "select t.role from CmsReceiver t where t.article.id = " + articleId;
        hql += " order by t.role.code asc";

        return sysRoleService.find(hql);
    }

    /**
     * 查询已经选中的用户
     *
     * @param articleId
     * @return
     */
    public List<SysUser> querySelectedUsers(Long articleId) {
        String hql = "select t.user from CmsReceiver t where t.article.id = " + articleId;
        hql += " order by t.user.id asc";

        return sysUserService.find(hql);
    }
}
