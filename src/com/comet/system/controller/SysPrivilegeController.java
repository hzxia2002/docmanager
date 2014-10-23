package com.comet.system.controller;

import com.comet.system.daoservice.SysPrivilegeService;
import com.comet.system.domain.SysPrivilege;
import com.comet.core.controller.BaseCRUDActionController;
import com.comet.core.export.excel.ExcelExporter;
import com.comet.core.export.pdf.PdfExporter;
import com.comet.core.orm.hibernate.Page;
import com.comet.core.orm.hibernate.QueryTranslate;
import com.comet.core.utils.ReflectionUtils;
import com.comet.system.manager.SysPrivilegeManager;
import com.comet.system.tree.Node;
import com.comet.system.tree.TreeBranch;
import com.comet.system.tree.ZTreeNode;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @version 1.0
 * @author: System
 */
@Controller
public class SysPrivilegeController extends BaseCRUDActionController<SysPrivilege> {
    @Autowired
	private SysPrivilegeService sysPrivilegeService;

    @Autowired
    private SysPrivilegeManager sysPrivilegeManager;

	/**
	 * 取得树数据
	 *
	 * @param type
	 *            节点类型
	 * @param uid
	 *            节点ID
	 * @throws Exception
	 *             异常
	 */
    @RequestMapping
    @ResponseBody
    public List<Node> tree(String type,String uid, String id) throws Exception {
        TreeBranch treeBranch = new TreeBranch();
        type = StringUtils.defaultString(type, "");

        if(StringUtils.isEmpty(uid)) {
            ZTreeNode treeNode = new ZTreeNode();
            treeNode.setId("root");
            treeNode.setIsParent(true);
            treeNode.setIsLeaf(false);
            treeNode.setOpen(true);
//            treeNode.setHasChildren(true);
            treeNode.setName("根节点");
            treeNode.setText("根节点");
            treeNode.setUid("root");
            treeBranch.addTreeNode(treeNode);
        } else {
            String hql = "from SysPrivilege where parent.id is null order by treeId";

            if (!StringUtils.equals(uid, "root")) {
                hql = "from SysPrivilege where parent.id = " + uid + " order by treeId";
            }

            List<SysPrivilege> sysPrivileges = sysPrivilegeService.findByQuery(hql);

            for (SysPrivilege sysPrivilege : sysPrivileges) {
                ZTreeNode treeNode = new ZTreeNode();
                treeNode.setId(sysPrivilege.getClass().getSimpleName() + "_" + sysPrivilege.getId());
                if(sysPrivilege.getIsLeaf() != null && !sysPrivilege.getIsLeaf()){
                    treeNode.setIsParent(true);
                    treeNode.setOpen(false);
                }else{
                    treeNode.setIsLeaf(true);
                }
                treeNode.setName(sysPrivilege.getName());
                treeNode.setText(sysPrivilege.getName());
                treeNode.setUid(sysPrivilege.getId().toString());
                treeNode.setType(sysPrivilege.getClass().getSimpleName());
                treeNode.setTreeId(sysPrivilege.getTreeId());
                treeBranch.addTreeNode(treeNode);
            }
        }

        return treeBranch.getTreeNodeList();
	}

    /**
     * 查询数据
     *
     * @param page
     * @param condition
     * @return
     */
	@RequestMapping
    @ResponseBody
	public Page<SysPrivilege> grid(Page page, String condition) {
		try {
            page.setAutoCount(true);

            String hql = "from SysPrivilege t where 1=1 " + page.getOrderByString("t.treeId asc");

            QueryTranslate queryTranslate = new QueryTranslate(hql, condition);

            // 查询
            page = sysPrivilegeService.findByPage(page, queryTranslate.toString());
		} catch (Exception e) {
            log.error("error", e);
		}

        return page;
	}

    /**
     * 导出Excel
     *
     * @param page
     * @param condition
     * @param colModel
     * @param model
     * @return
     */
    @RequestMapping
    public String exportExcel(Page page, String condition, String colModel,
                              Model model) {
        try {
            page.setAutoCount(true);

            String hql = "from SysPrivilege t where 1=1 " + page.getOrderByString("t.treeId asc");
            QueryTranslate queryTranslate = new QueryTranslate(hql, condition);

            String filePath = "D://temp";
            String fileName = "test.xls";

            ExcelExporter.export(
                    colModel,
                    sysPrivilegeService, filePath, fileName,
                    queryTranslate.toString());

            model.addAttribute("filePath", filePath);
            model.addAttribute("fileName", fileName);
        } catch (Exception e) {
            log.error("error", e);
        }

        return "common/dataExport";
    }

    @RequestMapping
    public String exportPdf(Page page, String condition, String colModel,
                              Model model) {
        try {
            page.setAutoCount(true);

            String hql = "from SysPrivilege t where 1=1 " + page.getOrderByString("t.treeId asc");
            QueryTranslate queryTranslate = new QueryTranslate(hql, condition);

            String filePath = "D://temp";
            String fileName = "test.pdf";

            PdfExporter.export(
                    colModel,
                    sysPrivilegeService, filePath, fileName,
                    queryTranslate.toString());

            model.addAttribute("filePath", filePath);
            model.addAttribute("fileName", fileName);
        } catch (Exception e) {
            log.error("error", e);
        }

        return "common/dataExport";
    }

    /**
     * 初始化
     *
     * @param model
     * @param entity
     * @return
     * @throws Exception
     */
    @RequestMapping
    public String init(Model model, SysPrivilege entity) throws Exception {
        try {
            if(entity != null && entity.getId() != null) {
                entity = sysPrivilegeService.get(entity.getId());

            }
            model.addAttribute("bean", entity);
        } catch (Exception e) {
            log.error("error", e);
        }

        return "view/sys_new/sysPrivilegeEdit";
    }

    /**
     * 新增初始化
     *
     * @param model
     * @param entity
     * @return
     * @throws Exception
     */
    @RequestMapping
    public String initAdd(Model model, SysPrivilege entity) throws Exception {
        try {
            if(entity == null) {
                entity = new SysPrivilege();
            }

            model.addAttribute("bean", entity);
        } catch (Exception e) {
            log.error("error", e);
        }

        return "view/sys_new/sysPrivilegeEdit";
    }

    /**
     * 查看
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping
    public String view(Model model, Long id) {
        SysPrivilege sysPrivilege = sysPrivilegeService.get(id);

        model.addAttribute("bean", sysPrivilege);
        return "view/sys_new/sysPrivilegeView";
    }

    /**
     * 保存
     *
     * @param response
     * @param model
     * @param entity
     * @throws Exception
     */
    @RequestMapping
    public void save(HttpServletResponse response, Model model, @ModelAttribute("bean") SysPrivilege entity)
            throws Exception {
        try {
            String[] columns = new String[]{
//                    "id",
//                    "parent",
                    "type",
                    "code",
                    "name",
                    "tag",
                    "url",
                    "definition",
                    "description"
//                   , "isLeaf",
//                    "treeId"
            };

            SysPrivilege target;
            if (entity.getId() != null) {
                target = sysPrivilegeService.get(entity.getId());

                ReflectionUtils.copyBean(entity, target, columns);
            } else {
                target = entity;
            }

            sysPrivilegeService.save(target);
            sendSuccessJSON(response, "保存成功");
        } catch (Exception e) {
            log.error("error", e);
            super.processException(response, e);
        }
    }

    /**
     * 删除
     *
     * @param response
     * @param id
     * @throws Exception
     */
    @RequestMapping
    public void delete(HttpServletResponse response, Long id) throws Exception {
        sysPrivilegeManager.delete(id);

        sendSuccessJSON(response, "删除成功");
    }
}