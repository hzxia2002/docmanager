package com.comet.system.manager;

import com.comet.system.daoservice.SysMenuService;
import com.comet.system.domain.SysMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * </p>
 * User: <a href="mailto:hzxia2002@gmail.com">Jackie</a> Date: 2009-11-23 Time:
 * 23:40:36 Version: 1.0
 */
@Service
public class SysMenuManager {
    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 取得所有的菜单，根据treeId进行排序
     *
     * @return
     */
    public List<SysMenu> getAllMenu() {
        String hql = "from SysMenu order by treeId asc";

        return sysMenuService.find(hql);
    }

    /**
     * 按照isValid查询所有的菜单
     *
     * @param status
     * @return
     */
    public List<SysMenu> getAllMenu(Boolean status) {
        String hql = "from SysMenu where 1=1 ";

        if(status != null) {
            hql += " and isValid = " + status;
        }

        hql += " order by treeId asc";

        return sysMenuService.find(hql);
    }
}
