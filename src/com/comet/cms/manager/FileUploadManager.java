package com.comet.cms.manager;

import com.comet.cms.daoservice.DocAttachmentsService;
import com.comet.cms.daoservice.DocDocumentService;
import com.comet.cms.domain.DocAttachments;
import com.comet.cms.domain.DocDocument;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * User: tcg
 * Date: 14-7-21
 * Time: 下午9:56
 */
@Service
public class FileUploadManager {
    @Autowired
    private DocDocumentService docDocumentService;

    @Autowired
    private DocAttachmentsService docAttachmentsService;

    @Value("${filePath}")
    private String filePath;

    public DocDocument saveOrUpdateDocDocument(HttpServletRequest request, Long docId, ArrayList arrayList, MultipartHttpServletRequest multipartRequest, String wholePath, String dateStr) throws IOException {
        DocDocument docDocument ;
        if(docId!=null){
            docDocument = docDocumentService.get(docId);
        }else{
            docDocument = new DocDocument();
        }
        docDocument.setPath(wholePath);
        docDocumentService.save(docDocument);
        Iterator fileNames = multipartRequest.getFileNames();

        while (fileNames.hasNext()) {
            DocAttachments docAttachments = new DocAttachments();
            docAttachments.setDoc(docDocument);

            HashMap map = new HashMap();
            String name = (String) fileNames.next();

            CommonsMultipartFile multipartFile = (CommonsMultipartFile) multipartRequest.getFile(name);
            String originalFileName = multipartFile.getOriginalFilename();
            String fileName = originalFileName;
            if(StringUtils.isNotEmpty(fileName)){
                String extName = "";
                int index = originalFileName.lastIndexOf(".");
                if(index>0){
                    extName = originalFileName.substring(index+1, originalFileName.length());
                }
                String chgName =  Math.round(Math.random()*10000000L) + "." + extName;
                File dest = new File(wholePath +File.separator +dateStr );
                if(!dest.exists()){
                    dest.mkdirs();
                }

                String relativePath = File.separator + dateStr + File.separator + chgName;
                String pathName = filePath + relativePath;
                multipartFile.transferTo(new File(pathName));

                docAttachments.setOrginName(originalFileName);
                docAttachments.setFilePath( relativePath);
                docAttachments.setName(chgName);
                String uploadDate = new Timestamp(System.currentTimeMillis()).toString().substring(0,19);
                docAttachments.setUploadDate(uploadDate);
                docAttachmentsService.save(docAttachments);

                map.put("original", originalFileName);
                map.put("attachmentId",docAttachments.getId());

                map.put("title", request.getParameter("pictitle"));
                map.put("state","SUCCESS");
                map.put("fileType","."+extName);
                arrayList.add(map);
            }

        }
        return docDocument;
    }
}
