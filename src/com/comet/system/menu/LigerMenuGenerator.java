package com.comet.system.menu;

import com.comet.system.domain.SysMenu;
import com.comet.core.security.privilege.Privilege;
import com.comet.core.security.util.SpringSecurityUtils;
import com.comet.core.utils.StringHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: dell
 * Date: 12-2-16
 * Time: 下午10:06
 */
public class LigerMenuGenerator {
    public static final Logger log = LoggerFactory.getLogger(LigerMenuGenerator.class);

    /**
     * 将菜单列表序列化成字符串
     *
     * @param list
     * @return
     */
    public static String generateMenu(List<SysMenu> list) {
        String ret = "{ items: [ ";
        List<SysMenu> tmpList = setMenuChildren(list);
        List<SysMenu> topList = getTopMenu(tmpList);

        for(SysMenu menu : topList) {
            ret += getTopNode(menu);

            if(menu.getChildren() != null && menu.getChildren().size() > 0) {
                ret += ", menu :";
                ret += getMenuListAsString(menu.getChildren());
            }

            ret += "},";
        }

        ret = StringHelper.removeLastLetter(ret);

        ret += "]}";

        return ret;
    }


    public static List<Map> generateTreeMenu(List<SysMenu> list){
        List<Map> treeDataList = new ArrayList<Map>();
        for (SysMenu sysMenu : list) {
            HashMap map = new HashMap();
            map.put("text",sysMenu.getName());
            map.put("id",sysMenu.getId());
            if(sysMenu.getParent()!=null){
                map.put("pid",sysMenu.getParent().getId());
            }
            if(sysMenu.getIsLeaf()){
                map.put("url",sysMenu.getUrl());
            }

            treeDataList.add(map);
        }
        return treeDataList;
    }

    public static List<Map> generateJuiMenu(List<SysMenu> list){
        List<Map> treeDataList = new ArrayList<Map>();
        //菜单链保存
        HashMap tempMap = new HashMap();
        HashMap menuMap ;
        for (SysMenu sysMenu : list) {
            Long id = sysMenu.getId();

            menuMap = new HashMap();
            menuMap.put("text",sysMenu.getName());
            menuMap.put("id",id);
            menuMap.put("click","function(item){menuItemClick(item);}");
            if(sysMenu.getIsLeaf()){
                menuMap.put("url",sysMenu.getUrl());
            }
            //直接创建子菜单容器
            String key = "list" + sysMenu.getId();
            if(!sysMenu.getIsLeaf()){
                if(tempMap.get(key)==null){
                    ArrayList childRenList = new ArrayList();
                    tempMap.put(key, childRenList);
                    //根据菜单级别，添加菜单children的属性：一级菜单：menu，二级菜单及以下：children
                    if(sysMenu.getParent()!=null){
                        menuMap.put("items",childRenList);
                    }  else{
                        HashMap map = new HashMap();
                        map.put("items",childRenList);
                        menuMap.put("menu",map);
                    }
                }
            }
            //子菜单找不到父节点创建的菜单容器，为父节点创建菜单容器
            SysMenu sysMenuParent = sysMenu.getParent();
            if(sysMenuParent !=null){
                String listKey = "list" + sysMenuParent.getId();
                List childList =(List) tempMap.get(listKey);

                if(childList == null){
                    childList = new ArrayList();
                    tempMap.put(listKey, childList);
                }

                childList.add(menuMap);
            }
            if(sysMenu.getParent()==null){
                treeDataList.add(menuMap);
            }

        }
        return treeDataList;
    }

    /**
     * 取得顶层节点
     *
     * @param menu
     * @return
     */
    public static String getTopNode(SysMenu menu) {
        String ret = "{text : '" + menu.getName() + "', id:'" + menu.getTreeId() + "'";

        return ret;
    }

    /**
     * 将菜单列表序列化成字符串
     *
     * @param list
     * @return
     */
    public static String getMenuListAsString(List<SysMenu> list) {
        // 取得需要进行权限判断的菜单
        Map<String, Privilege> menuMap = SpringSecurityUtils.getMenuPrivilegesMap();

        // 取得当前登录人菜单权限
        Map<String, Privilege> userPrivilege = SpringSecurityUtils.getUserMenuPrivilegesMap();

        int i = 0;
        String ret = "{ width: 160, items:[";

        for(SysMenu menu : list) {
            if(i > 0 && i < list.size()) {
                ret = ret + ",";
            }

            // 判断权限
            if(!menuMap.containsKey(menu.getPrivilege()) ||
                    (menuMap.containsKey(menu.getPrivilege())
                            && userPrivilege.containsKey(menu.getPrivilege()))) {
                ret += getMenuAsString(menu);
                i++;
            }
        }

        ret += "]}";

        return ret;
    }

    /**
     * 将菜单项序列化成字符串
     *
     * @param menu
     * @return
     */
    public static String getMenuAsString(SysMenu menu) {
        String ret = "{text: '" + menu.getName() + "', id:'" + menu.getTreeId() + "'," +
                " url:'" + menu.getUrl() + "', target:'" + menu.getTarget() + "'," +
                " jsEvent:'" + menu.getJsEvent() + "',";
        ret += " parentName:'" + getParentPath(menu) + "',";

        if(menu.getIsLeaf()) {
            ret += "click: itemClick}";
        } else {
            List<SysMenu> children = menu.getChildren();

            ret += getMenuChildren(children);

            ret += "}";
        }

        return ret;
    }

    /**
     * 取得三级及n级子菜单
     *
     * @param children
     * @return
     */
    public static String getMenuChildren(List<SysMenu> children) {
        String ret = "children:";

        for(int i = 0; i<children.size(); i++) {
            SysMenu menu = children.get(i);

            if(i > 0) {
                ret = "," + ret;
            }

            ret += "[{text: '" + menu.getName() + "', id:'" + menu.getTreeId() + "'," +
                    " url:'" + menu.getUrl() + "', target:'" + menu.getTarget() + "'," +
                    " jsEvent:'" + menu.getJsEvent() + "',";
            ret += " parentName:'" + getParentPath(menu) + "',";

            ret += "";

            if(menu.getIsLeaf()) {
                ret += "click: itemClick}]";
            } else {
                List<SysMenu> tmpList = menu.getChildren();

                ret += getMenuChildren(tmpList);
                ret += "}]";
            }

            i++;
        }

        return ret;
    }

    /**
     * 重新设置菜单的子菜单
     *
     * @param list
     * @return
     */
    public static List<SysMenu> setMenuChildren(List<SysMenu> list) {
        List<SysMenu> ret = new ArrayList<SysMenu>();

        for(SysMenu menu : list) {
            if(!menu.getIsLeaf()) {
                String treeId = menu.getTreeId();

                List<SysMenu> children = new ArrayList<SysMenu>();

                for(SysMenu tmp : list) {
                    String tmpTreeId = tmp.getTreeId();

                    if(tmpTreeId.length() == treeId.length() + 6 && tmpTreeId.startsWith(treeId)) {
                        children.add(tmp);
                    }
                }

                menu.setChildren(children);
                ret.add(menu);
            }
        }

        return ret;
    }

    /**
     * 取得顶层菜单
     *
     * @param list
     * @return
     */
    public static List<SysMenu> getTopMenu(List<SysMenu> list) {
        List<SysMenu> ret = new ArrayList<SysMenu>();

        Map map = SpringSecurityUtils.getAllPrivilegesMap();

        // 取得需要进行权限判断的菜单
        Map<String, Privilege> menuMap = SpringSecurityUtils.getMenuPrivilegesMap();

        // 取得当前登录人
        Map<String, Privilege> userPrivilege = SpringSecurityUtils.getUserMenuPrivilegesMap();

        for(SysMenu menu : list) {
            if(menu.getParent() == null) {
                // 判断权限
                if(!menuMap.containsKey(menu.getPrivilege()) ||
                        (menuMap.containsKey(menu.getPrivilege())
                                && userPrivilege.containsKey(menu.getPrivilege()))) {
                    ret.add(menu);
                }
            }
        }

        return ret;
    }

    /**
     * 取得父节点路径
     *
     * @param menu
     * @return
     */
    public static String getParentPath(SysMenu menu) {
        String ret = "";
        SysMenu parent = menu.getParent();

        if(parent != null) {
            ret += parent.getName() + "&gt;&gt;";

            ret += getParentPath(parent);
        }

        return ret;
    }

    public static void main(String[] args) {
        List<SysMenu> list = new ArrayList<SysMenu>();

        SysMenu menu = new SysMenu();
        menu.setTreeId("00001");
        menu.setIsLeaf(false);
        menu.setName("00001");
        list.add(menu);

        menu = new SysMenu();
        menu.setTreeId("00001.00001");
        menu.setIsLeaf(true);
        menu.setName("00001.00001");
        list.add(menu);

        menu = new SysMenu();
        menu.setTreeId("00001.00002");
        menu.setIsLeaf(true);
        menu.setName("00001.00002");
        list.add(menu);

        menu = new SysMenu();
        menu.setTreeId("00001.00003");
        menu.setIsLeaf(true);
        menu.setName("00001.00003");
        list.add(menu);

        menu = new SysMenu();
        menu.setTreeId("00001.00004");
        menu.setIsLeaf(true);
        menu.setName("00001.00004");
        list.add(menu);

        menu.setTreeId("00002");
        menu.setIsLeaf(false);
        menu.setName("00002");
        list.add(menu);

        menu = new SysMenu();
        menu.setTreeId("00002.00001");
        menu.setIsLeaf(true);
        menu.setName("00002.00001");
        list.add(menu);

        menu = new SysMenu();
        menu.setTreeId("00002.00002");
        menu.setIsLeaf(true);
        menu.setName("00002.00002");
        list.add(menu);

        menu = new SysMenu();
        menu.setTreeId("00002.00003");
        menu.setIsLeaf(true);
        menu.setName("00002.00003");
        list.add(menu);

        menu = new SysMenu();
        menu.setTreeId("00002.00004");
        menu.setIsLeaf(true);
        menu.setName("00002.00004");
        list.add(menu);

        log.info(generateMenu(list));
    }
}
