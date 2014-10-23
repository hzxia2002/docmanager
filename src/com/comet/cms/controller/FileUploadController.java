package com.comet.cms.controller;

import com.comet.cms.daoservice.DocAttachmentsService;
import com.comet.cms.daoservice.DocDocumentService;
import com.comet.cms.domain.DocAttachments;
import com.comet.cms.domain.DocDocument;
import com.comet.cms.manager.FileUploadManager;
import com.comet.core.controller.BaseCRUDActionController;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * User: tcg
 * Date: 14-7-20
 * Time: 下午6:57
 */
@Controller
public class FileUploadController extends BaseCRUDActionController {
    private static final Logger log = LoggerFactory
            .getLogger(FileUploadController.class);
    private static final String DOC_METHOD_NAME = "setDocId";
    @Autowired
    private DocDocumentService docDocumentService;

    @Autowired
    private DocAttachmentsService docAttachmentsService;

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private FileUploadManager fileUploadManager;

    @Value("${filePath}")
    private String filePath;

    @RequestMapping
    public String uploadFiles(HttpServletRequest request,Long id,Long docId,String bizId,String juiId,String domain,String methodName,Model model) {
        try {
            ArrayList arrayList = new ArrayList();
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)(request);

            String wholePath = filePath;
            DateFormat format = new SimpleDateFormat("yyyy" + File.separator + "M" + File.separator+ "dd");
            String dateStr = format.format(new Date());

            //附件 Document
            DocDocument docDocument = fileUploadManager.saveOrUpdateDocDocument(request, docId, arrayList, multipartRequest, wholePath, dateStr);
            if(StringUtils.isNotEmpty(bizId)){
                StringBuffer hqlSb = new StringBuffer();
                hqlSb.append("from " + domain + " where id='" + bizId + "'");
                Session currentSession = sessionFactory.getCurrentSession();
                Serializable obj = (Serializable) currentSession.createQuery(hqlSb.toString()).uniqueResult();
                if(StringUtils.isBlank(methodName)){
                    methodName =  DOC_METHOD_NAME ;
                }
                Method setDocId = obj.getClass().getMethod(methodName, Long.class);
                setDocId.invoke(obj,docDocument.getId());
                currentSession.saveOrUpdate(obj);
                currentSession.flush();
            }
            model.addAttribute("msg","上传成功");
            model.addAttribute("docId",docDocument.getId());
            model.addAttribute("bizId",bizId);
            model.addAttribute("juiId",juiId);
            model.addAttribute("domain",domain);
            model.addAttribute("ret", JSONArray.fromObject(arrayList).toString());
        } catch (Exception e) {
            log.error(e.getMessage());
            model.addAttribute("msg", "程序异常请联系管理员");
        }
        return "view/common/uploadResult";
    }



    @RequestMapping
    public String fileUploadInit(Model model,String id,String bizId,String juiId,Long docId,String domain,String methodName){
        model.addAttribute("id",id);
        model.addAttribute("bizId",bizId);
        model.addAttribute("juiId",juiId);
        model.addAttribute("docId",docId);
        model.addAttribute("domain",domain);
        model.addAttribute("methodName",methodName);
        return "view/common/fileUpload";
    }

    @RequestMapping
    @ResponseBody
    public List<Map> loadData(HttpServletResponse response,Long docId){
        ArrayList<Map> maps = new ArrayList<Map>();
        if(docId!=null){
            List<DocAttachments> docAttachmentses = docAttachmentsService.find("from DocAttachments where doc.id = " + docId);
            for (DocAttachments docAttachmentse : docAttachmentses) {
                HashMap hashMap = new HashMap();
                hashMap.put("original", docAttachmentse.getOrginName());
                hashMap.put("attachmentId",docAttachmentse.getId());
                maps.add(hashMap);
            }
        }
        return maps;
    }

    @RequestMapping
    public void deleteAttachment(HttpServletResponse response, Long attachmentId) throws Exception {
        docAttachmentsService.delete(attachmentId);
        sendSuccessJSON(response, "删除成功");
    }

    @RequestMapping
    public void downloadAttachment(HttpServletResponse response,HttpServletRequest request, Long attachmentId) throws Exception {
        OutputStream outp = null;
        FileInputStream in = null;
        DocAttachments docAttachments = docAttachmentsService.get(attachmentId);

        try {
            in = new FileInputStream(filePath + docAttachments.getFilePath());
            response.reset();
            outp = response.getOutputStream();
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-Disposition", "attachment;filename=" +new String(docAttachments.getOrginName().getBytes("GB2312"),"ISO-8859-1").replaceAll(",",""));
            byte[] b = new byte[1024];
            int i;
            while ((i = in.read(b)) > 0) {
                outp.write(b, 0, i);
            }
        } catch (IOException e) {
            throw new Exception("下载文件在服务器中不存在");
        } finally {
            outp.flush();
            outp.close();
        }
    }
}
