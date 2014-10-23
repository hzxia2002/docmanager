package com.comet.system.controller;

import com.comet.core.controller.BaseCRUDActionController;
import com.comet.core.entity.Treeable;
import com.comet.core.service.TreeManager;
import com.comet.core.utils.StringHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * author:Chen Junping
 */
@Controller
public class CommonPageController extends BaseCRUDActionController {

    private Logger logger = LoggerFactory.getLogger(CommonPageController.class);

    @Autowired
    private TreeManager treeManager;

    /**
     * 树节点向上移动
     * 其中param为sql，比如type_id=1
     *
     * @param clazz .样式如：id=data|<id>218</id><class>BpmProcessTemplate</class><param></param>
     * @param model .
     */
    @SuppressWarnings("unchecked")
    @RequestMapping
    public void moveUp(HttpServletResponse response, String clazz, Long id, String param, Model model) throws Exception {
        treeManager.moveUpNode((Class<Treeable>) Class.forName(clazz), id, param);
        treeManager.flush();
        sendSuccessJSON(response, "节点上移成功");
    }

    /**
     * 树节点向移动
     * 其中param为sql，比如type_id=1
     *
     * @param clazz .样式如：id=data|<id>218</id><class>BpmProcessTemplate</class><param></param>
     * @param model .
     */
    @SuppressWarnings("unchecked")
    @RequestMapping
    public void moveDown(HttpServletResponse response, String clazz, Long id, String param, Model model) throws Exception {
        treeManager.moveDownNode((Class<Treeable>) Class.forName(clazz), id, param);
        treeManager.flush();
        sendSuccessJSON(response, "节点下移成功");
    }

    /**
     * 树重新排序
     * 其中param为sql，比如type_id=1
     *
     * @param clazz .样式如：id=Sample_12
     * @param model .
     */
    @RequestMapping
    //public String treeReorder(@RequestParam Long id,@RequestParam String column,@RequestParam Long parentId,Model model) throws Exception {
    public void treeReorder(HttpServletResponse response, String clazz, String column, Model model) throws Exception {
//        if (column != null && parentId != null) {
//            treeManager.saveReorderTree(Class.forName(className), parentId, column);
//        } else if (column != null) {
//
//            treeManager.saveReorderTree(Class.forName(className), column);
//        } else if (parentId != null) {
//            treeManager.saveReorderTree(Class.forName(className), parentId, null);
//        } else {
//            treeManager.saveReorderTree(Class.forName(className));
//        }

        if (!StringHelper.isEmpty(column)) {
            treeManager.saveReorderTree(Class.forName(clazz), column);
        } else {
            treeManager.saveReorderTree(Class.forName(clazz));
        }
        treeManager.flush();

        sendSuccessJSON(response, "树排序成功");
    }

    /**
     * 非树型结构的上移
     *
     * @param clazz,xml方式，含有class（类名）,condition（条件）eg.and
     *              t.project.id=1 ,orderFieldName（排序的字段）
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping
    public void simpleMoveUp(HttpServletResponse response, String clazz, Long id, String param, String orderFieldName, Model model) throws Exception {
        treeManager.simpleMoveUp(Class.forName(clazz), orderFieldName, param, id);
        treeManager.flush();
        sendSuccessJSON(response, "节点上移成功");
    }

    /**
     * 非树型结构的下移
     *
     * @param clazz,xml方式，含有class（类名）,condition（条件）eg.and
     *              t.project.id=1 ,orderFieldName（排序的字段）
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping
    public void simpleMoveDown(HttpServletResponse response, String clazz, Long id, String param, String orderFieldName, Model model) throws Exception {
        treeManager.simpleMoveDown(Class.forName(clazz), orderFieldName, param, id);
        treeManager.flush();
        sendSuccessJSON(response, "节点下移成功");
    }
}
