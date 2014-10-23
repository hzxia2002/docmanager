package com.comet.system.tree;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 12-2-7
 * Time: 上午10:43
 * To change this template use File | Settings | File Templates.
 */
public class TreeBranch {
    List<Node> treeNodeList;

    public List<Node> getTreeNodeList() {
        if(treeNodeList == null){
            treeNodeList = new ArrayList<Node>();
        }
        return treeNodeList;
    }

    public void setTreeNodeList(List<Node> treeNodeList) {
        this.treeNodeList = treeNodeList;
    }

    public void addTreeNode(Node treeNode){
        getTreeNodeList().add(treeNode);
    }

    /**
     *
     * @param hasCheckBox 是否要添加checkbox 多选框
     * @return
     */
    public String toJsonString(boolean hasCheckBox){
        JsonConfig jsonConfig = new JsonConfig();
        if(!hasCheckBox){
            jsonConfig.setExcludes(new String[]{"checked"});
        }
        JSONArray tranfer = JSONArray.fromObject(getTreeNodeList(),jsonConfig);
        return tranfer.toString();
    }

}
