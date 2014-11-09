package com.comet.cms.controller;

import com.comet.cms.daoservice.CmsArticleService;
import com.comet.cms.daoservice.CmsReceiverService;
import com.comet.cms.daoservice.CmsTaskService;
import com.comet.cms.domain.*;
import com.comet.cms.utils.CmsUtils;
import com.comet.core.controller.BaseCRUDActionController;
import com.comet.core.orm.hibernate.Page;
import com.comet.core.orm.hibernate.QueryTranslate;
import com.comet.core.security.util.SpringSecurityUtils;
import com.comet.core.utils.DateTimeHelper;
import com.comet.core.utils.FileUtils;
import com.comet.core.utils.ReflectionUtils;
import com.comet.system.daoservice.SysUserRoleService;
import com.comet.system.domain.SysRole;
import com.comet.system.domain.SysUser;
import com.comet.system.domain.SysUserRole;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @author: System
 */
@Controller
public class CmsArticleController extends BaseCRUDActionController<CmsCategory> {
    @Autowired
    private CmsArticleService cmsArticleService;

    @Autowired
    private CmsReceiverService cmsReceiverService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private CmsTaskService cmsTaskService;

    public static final String[] PERMIT_IMAGE_TYPE = new String[]{"jpg", "jpeg", "gif", "png"};

    @Value("${filePath}")
    private String filePath;

    /**
     * 查询列表数据
     *
     * @param page
     * @param condition
     * @param request
     * @return
     */
    @RequestMapping
    @ResponseBody
    public Page<CmsCategory> grid(Page page, String condition, HttpServletRequest request) {
        try {
            page.setAutoCount(true);

            String hql = "from CmsArticle t where 1=1 " + page.getOrderByString("t.id desc");

            QueryTranslate queryTranslate = new QueryTranslate(hql, condition);

            // 查询
            page = cmsArticleService.findByPage(page, queryTranslate.toString());
        } catch (Exception e) {
            log.error("error", e);
        }

        return page;
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
    public String init(Model model, CmsArticlePropertity entity) throws Exception {
        try {
            if(entity != null && entity.getId() != null) {
                 entity =  (CmsArticlePropertity)cmsArticleService.get(entity.getId());
            } else if(entity != null) {
                entity.setPublishDate(new Date(System.currentTimeMillis()));
                entity.setIsValid(Boolean.TRUE);
            }

            model.addAttribute("bean", entity);
        } catch (Exception e) {
            log.error("error", e);
        }

        return "view/cms/cmsArticleEdit";
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
        CmsArticle bean = cmsArticleService.get(id);

        model.addAttribute("bean", bean);
        return "view/cms/cmsArticleView";
    }

    /**
     * 保存
     *
     * @param request
     * @param response
     * @param model
     * @param entity
     * @return
     * @throws Exception
     */
    @RequestMapping
    public String save(HttpServletRequest request, HttpServletResponse response, Model model,
                       @ModelAttribute("bean") CmsArticlePropertity entity)
            throws Exception {
        try {
            String[] columns = new String[]{
                    "category",
                    "title",
                    "content",
                    "isTop",
                    "isValid",
                    "publishDate",
                    "linkUrl",
                    "keyword"
            };
            String[] columns2 = new String[]{"roleIds","userIds"};

            // 如果有图片，上传图片
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Iterator it = multipartRequest.getFileNames();
            Map map = null;
            String thumbPath = "";
            String attachPath = "";

            while (it.hasNext()) {
                String name = (String) it.next();
                boolean isImage = false;

                CommonsMultipartFile orginalFile = (CommonsMultipartFile) multipartRequest
                        .getFile(name);

                // 判断文件类型

                if(name.equals("image")) {
                    String fileType = FileUtils.getFileType(FileUtils.getFileName(orginalFile));
                    if(fileType != null && fileType.length() > 0) {
                        fileType = fileType.toLowerCase();

                        if(!FileUtils.isValidsFileType(fileType, PERMIT_IMAGE_TYPE)) {
                            isImage = false;
                            model.addAttribute("msg", "文件类型不正确，请重新上传!");
                            model.addAttribute("bean", entity);

                            return "view/cms/cmsArticleEdit";
                        }
                    }

                    isImage = true;
                }

                if (!FileUtils.isEmptyFile(orginalFile)) {
                    map = this.saveToDisk(orginalFile, isImage, request);

                    if(isImage) {
                        thumbPath = map.get("filePath").toString() + map.get("thumbFileName").toString();
                    } else {
                        attachPath = map.get("filePath").toString() + map.get("newFileName").toString();
                    }
                }
            }

            CmsArticle target;
            if (entity.getId() != null) {
                target = cmsArticleService.get(entity.getId());

                ReflectionUtils.copyBean(entity, target, columns);

                target.setUpdateUser(SpringSecurityUtils.getCurrentUser().getLoginName());
                target.setUpdateTime(DateTimeHelper.getTimestamp());
            } else {
//                target = entity;
                target = new CmsArticle();
                ReflectionUtils.copyBean(entity, target, columns);

                target.setCreateUser(SpringSecurityUtils.getCurrentUser().getLoginName());
                target.setCreateTime(DateTimeHelper.getTimestamp());
                target.setVisitTimes(0L);
            }

            if(StringUtils.isNotBlank(attachPath)) {
                target.setAttachPath(attachPath);
            }

            if(StringUtils.isNotBlank(thumbPath)) {
                target.setThumbPath(thumbPath);
            }

            cmsArticleService.save(target);
            String roleIds = entity.getRoleIds();
            String userIds = entity.getUserIds();
            String roles[]=roleIds.split(",");
            String users[]=userIds.split(",");
            Map param = new HashMap();
            CmsReceiver cmsReceiver;
            CmsTask cmsTask;
            SysRole sysRole;
            SysUser sysUser;
            List<SysUserRole> result;
            List<CmsReceiver> result1;
            if(roleIds!=null||!roleIds.equals("")){
                for(String role:roles){
                    result = sysUserRoleService.find(" from SysUserRole  where  role = "+role);
                    for(int i=0;i<result.size();i++){
                        cmsReceiver = new CmsReceiver();
                        cmsTask = new CmsTask();
                        sysRole = new SysRole();
                        sysUser = new SysUser();
                        sysUser.setId(result.get(i).getUser().getId());
                        sysRole.setId(Long.valueOf(role));
                        cmsReceiver.setUser(sysUser);
                        cmsReceiver.setRole(sysRole);
                        cmsReceiver.setArticle(target);
                        cmsTask.setArticle(target);
                        cmsTask.setUser(sysUser);
                        cmsReceiverService.save(cmsReceiver);
                        cmsTaskService.save(cmsTask);
                    }
                }
            }
            if(userIds!=null||!userIds.equals("")){
                for(String user:users){
                    result1 = cmsReceiverService.find(" from CmsReceiver  where  user = "+user);
                    if(result1.size()==0){
                        cmsReceiver = new CmsReceiver();
                        cmsTask = new CmsTask();
                        sysUser = new SysUser();
                        sysUser.setId(Long.valueOf(user));
                        cmsReceiver.setUser(sysUser);
                        cmsReceiver.setArticle(target);
                        cmsTask.setArticle(target);
                        cmsTask.setUser(sysUser);
                        cmsReceiverService.save(cmsReceiver);
                        cmsTaskService.save(cmsTask);
                    }
                }
            }
            model.addAttribute("bean", entity);
        } catch (Exception e) {
            log.error("error", e);
            super.processException(response, e);
        }

        return "view/cms/cmsArticleEdit";
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
        cmsArticleService.delete(id);

        sendSuccessJSON(response, "删除成功");
    }

    /**
     * 删除附件及图片
     *
     * @param response
     * @param id
     * @param delType
     * @throws Exception
     */
    @RequestMapping
    public void deleteFile(HttpServletResponse response, Long id, String delType) throws Exception {
        CmsArticle bean = cmsArticleService.get(id);
        String msg = "";

        if(StringUtils.equals(delType, "0")) {
            bean.setThumbPath("");
            msg = "图片";
        } else if(StringUtils.equals(delType, "1")) {
            bean.setAttachPath("");
            msg = "附件";
        }

        cmsArticleService.update(bean);

        sendSuccessJSON(response, "删除" + msg + "成功");
    }

    /**
     * 将文件保存到磁盘
     *
     * @param orginalFile
     * @param request
     * @return
     * @throws java.io.IOException
     */
    private Map saveToDisk(CommonsMultipartFile orginalFile, boolean isImage,
                           HttpServletRequest request) throws Exception {

        DataOutputStream out = null;
        InputStream is = null;// 附件输入流

        try {
            String dateStr = DateTimeHelper.dateToString("YYYYMMDD");

            File destinationDir = new File(filePath + dateStr);

            if (!destinationDir.exists()) {
                destinationDir.mkdirs();
            }

            HashMap result = new HashMap();

            if (orginalFile == null || orginalFile.isEmpty()) {// 空文件
                return result;
            }

            String fileName = FileUtils.extractFileName(orginalFile
                    .getFileItem().getName());

            String newFileName = System.currentTimeMillis() + "_" + fileName;

            File newFile = new File(destinationDir + File.separator
                    + newFileName);

            if (!newFile.exists()) {
                newFile.createNewFile();
            }

            out = new DataOutputStream(new FileOutputStream(newFile));// 存放文件的绝对路径

            is = orginalFile.getInputStream();
            byte[] b = new byte[is.available()];
            is.read(b);
            out.write(b);

            if(isImage) {
                // 如果是图片，生成缩略图
                Thumbnails.of(destinationDir.getAbsolutePath() + File.separator + newFileName)
                        .size(160, 160)
                        .toFile(destinationDir.getAbsolutePath() + File.separator + "s_" + newFileName);

                result.put("thumbFileName", "s_" + newFileName);
            }

            result.put("newFileName", newFileName);
            result.put("filePath", "/uploads/" + dateStr + "/");

            return result;
        } catch (IOException ie) {
            throw ie;
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ie1) {
                    ie1.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException ie2) {
                    ie2.printStackTrace();
                }
            }
        }
    }

}