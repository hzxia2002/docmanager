package com.comet.system.controller;

import com.comet.core.orm.hibernate.Condition;
import com.comet.system.daoservice.SysCodeDetailService;
import com.comet.system.daoservice.SysCodeService;
import com.comet.system.domain.SysCode;
import com.comet.system.domain.SysCodeDetail;
import com.comet.system.manager.SysCodeManager;
import com.comet.core.controller.BaseCRUDActionController;
import com.comet.core.orm.hibernate.Page;
import com.comet.core.orm.hibernate.QueryTranslate;
import com.comet.core.utils.ReflectionUtils;
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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @version 1.0
 * @author: System
 */
@Controller
public class SysCodeController extends BaseCRUDActionController<SysCode> {
    @Autowired
    private SysCodeService sysCodeService;

    @Autowired
    private SysCodeDetailService sysCodeDetailService;

    @Autowired
    private SysCodeManager sysCodeManager;

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
            treeNode.setName("系统代码树");
            treeNode.setText("系统代码树");
            treeNode.setUid("root");
            treeBranch.addTreeNode(treeNode);
        } else if(StringUtils.equals(uid, "root")){
            String hql = "select distinct c from SysCode c left join fetch c.sysCodeDetails where c.parent.id is null order by c.treeId";

            List<SysCode> sysCodes = sysCodeService.findByQuery(hql);

            for (SysCode sysCode : sysCodes) {
                ZTreeNode treeNode = new ZTreeNode();
                treeNode.setId(sysCode.getClass().getSimpleName() + "_" + sysCode.getId());
                if(sysCode.getSysCodeDetails().size()>0){
                    treeNode.setIsParent(true);
                    treeNode.setOpen(false);
                }else{
                    treeNode.setIsLeaf(true);
                }
                treeNode.setName(sysCode.getName());
                treeNode.setText(sysCode.getName());
                treeNode.setUid(sysCode.getId().toString());
                treeNode.setType(sysCode.getClass().getSimpleName());
                treeNode.setTreeId(sysCode.getTreeId());
                treeBranch.addTreeNode(treeNode);
            }
        }else{
            List<SysCodeDetail> sysCodeDetails = new ArrayList<SysCodeDetail>();
            if(type.equals("SysCode")){
                String hql = "from SysCodeDetail where sysCode.id="+ uid+ " order by treeId";
                sysCodeDetails = sysCodeDetailService.find(hql);
            }else if(type.equals("SysCodeDetail")){
                String hql = "from SysCodeDetail where parent.id="+ uid+ " order by treeId";
                sysCodeDetails = sysCodeDetailService.find(hql);
            }
            for (SysCodeDetail sysCodeDetail : sysCodeDetails) {
                ZTreeNode treeNode = new ZTreeNode();
                treeNode.setId(sysCodeDetail.getClass().getSimpleName() + "_" + sysCodeDetail.getId());
                if(sysCodeDetail.getIsLeaf()!=null&&!sysCodeDetail.getIsLeaf()){
                    treeNode.setIsParent(true);
                    treeNode.setOpen(false);
                }else{
                    treeNode.setIsLeaf(true);
                }
                treeNode.setName(sysCodeDetail.getName());
                treeNode.setText(sysCodeDetail.getName());
                treeNode.setUid(sysCodeDetail.getId().toString());
                treeNode.setType(sysCodeDetail.getClass().getSimpleName());
                treeNode.setTreeId(sysCodeDetail.getTreeId());
                treeBranch.addTreeNode(treeNode);
            }
            
        }

        return treeBranch.getTreeNodeList();
	}

    /**
     * 取得代码详细列表信息
     *
     * @param code
     * @return
     */
    @RequestMapping
    @ResponseBody
    public List<SysCodeDetail> getCodeDetailList(String code, String isNeed) {
        try {
            List<SysCodeDetail> ret = new ArrayList<SysCodeDetail>();
            List<SysCodeDetail> list = sysCodeManager.getCodeDetailListByCode(code, null);

            if(StringUtils.equalsIgnoreCase(isNeed, "true") || StringUtils.equalsIgnoreCase(isNeed, "1")) {
                SysCodeDetail bean = new SysCodeDetail();
                bean.setCode("");
                bean.setName("--请选择--");
                ret.add(0, bean);

                ret.addAll(list);
            } else {
                ret = list;
            }

            return ret;
        } catch (Exception e) {
            log.error("error", e);

            return null;
        }
    }

    @RequestMapping
    @ResponseBody
    public List<SysCode> getCodeChildren(String code) {
        try {
            List<SysCode> ret = sysCodeManager.findChildren(code);

            return ret;
        } catch (Exception e) {
            log.error("error", e);

            return null;
        }
    }

    @RequestMapping
    @ResponseBody
    public List<SysCodeDetail> getCodeDetailListById(Long parentId) {
        try {
            String hql = "from SysCodeDetail t where t.parent.id =" + parentId + " order by t.treeId asc";

//            if(parentId!=null){
//                SysCode sysCode = sysCodeService.get(parentId);
//                return sysCodeManager.getCodeDetailListByCode(sysCode.getCode());
//            }else{
//                return new ArrayList<SysCodeDetail>();
//            }

            return sysCodeDetailService.find(hql);
        } catch (Exception e) {
            log.error("error", e);

            return null;
        }
    }

    @RequestMapping
    @ResponseBody
    public List<SysCodeDetail> getCodeDetailListByParent(String sysCode, String detailCode) {
        try {
            String hql = "from SysCodeDetail t where t.sysCode.code='" + sysCode + "' ";
            hql += " and t.code like '" + detailCode + "%'";
            hql += " order by t.code asc";

            return sysCodeDetailService.find(hql);
        } catch (Exception e) {
            log.error("error", e);

            return null;
        }
    }

	@RequestMapping
    @ResponseBody
	public Page<SysCode> grid(Page page, String condition) {
		try {
            page.setAutoCount(true);

            String hql = "from SysCode t where 1=1 " + page.getOrderByString("t.treeId asc");

            QueryTranslate queryTranslate = new QueryTranslate(hql, condition);

            // 查询
            page = sysCodeService.findByPage(page, queryTranslate.toString());
		} catch (Exception e) {
            log.error("error", e);
		}

        return page;
	}

    /**
     * 初始化新增
     *
     * @param model
     * @param entity
     * @return
     * @throws Exception
     */
    @RequestMapping
    public String initAdd(Model model, SysCode entity,Long parentId) throws Exception {
        try {

            if(parentId!=null){
                entity .setParent( sysCodeService.get(parentId));
            }

            model.addAttribute("bean", entity);
        } catch (Exception e) {
            log.error("error", e);
        }

        return "view/sys_new/sysCodeEdit";
    }

    @RequestMapping
    public String init(Model model, SysCode entity) throws Exception {
        try {
            if(entity != null && entity.getId() != null) {
                entity = sysCodeService.get(entity.getId());

                model.addAttribute("bean", entity);
            }
        } catch (Exception e) {
            log.error("error", e);
        }

        return "view/sys_new/sysCodeEdit";
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
        SysCode sysCode = sysCodeService.get(id);

        model.addAttribute("bean", sysCode);
        return "view/sys_new/sysCodeView";
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
    public void save(HttpServletResponse response, Model model, @ModelAttribute("bean") SysCode entity)
            throws Exception {
        try {
            String[] columns = new String[]{
//                    "id",
//                    "parent",
                    "code",
                    "name",
                    "isReserved",
//                    "isLeaf",
//                    "treeId",
                    "description"
            };

            SysCode target;
            if (entity.getId() != null) {
                target = sysCodeService.get(entity.getId());

                ReflectionUtils.copyBean(entity, target, columns);
            } else {
                target = entity;
            }

            sysCodeService.save(target);
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
        sysCodeService.delete(id);

        sendSuccessJSON(response, "删除成功");
    }
}