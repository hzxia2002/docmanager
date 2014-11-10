package com.comet.cms.controller;

import com.comet.cms.daoservice.CmsArticleService;
import com.comet.cms.domain.CmsArticle;
import com.comet.cms.domain.DocAttachments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Date: 14-11-8
 * Time: 上午11:31
 */
@Controller
public class FileDownloadController {
    @Autowired
    private CmsArticleService cmsArticleService;

    @Value("${filePath}")
    private String filePath;

    @RequestMapping
    public void downloadAttachment(HttpServletResponse response,HttpServletRequest request, Long articleId,Long index) throws Exception {
        OutputStream outp = null;
        FileInputStream in = null;
        CmsArticle cmsArticle = cmsArticleService.get(articleId);

        try {
            String attachPath ="";
            if(index==1){
                attachPath   = cmsArticle.getAttachPath();
            }else if(index==2){
                attachPath   = cmsArticle.getAttachPath2();
            }else if(index==3){
                attachPath   = cmsArticle.getAttachPath3();
            }
            in = new FileInputStream(filePath + attachPath.substring(attachPath.indexOf("/",1)));
            String fileName = attachPath.substring(attachPath.indexOf("_") + 1);
            response.reset();
            outp = response.getOutputStream();
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-Disposition", "attachment;filename=" +new String(fileName.getBytes("GB2312"),"ISO-8859-1").replaceAll(",",""));
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
