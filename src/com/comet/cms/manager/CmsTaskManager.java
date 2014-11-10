package com.comet.cms.manager;

import com.comet.cms.daoservice.CmsTaskService;
import com.comet.cms.domain.CmsTask;
import com.comet.core.orm.hibernate.Page;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dell
 * Date: 13-6-5
 * Time: 下午10:37
 * To change this template use File | Settings | File Templates.
 */
@Service
public class CmsTaskManager {
    private static final Logger logger = LoggerFactory
            .getLogger(CmsTaskManager.class);

    @Autowired
    private CmsTaskService cmsTaskService;

    /**
     * 查询任务
     *
     * @param status
     * @return
     */
    public Page<CmsTask> queryTasks(Page page, String status,String loginName) {
        String hql = "from CmsTask t left join fetch t.article where 1=1 and t.user.loginName='"+loginName+"'";

        if(StringUtils.equals(status, "1")) {
            hql += " and t.handlingResult <> null";
        } else {
            hql += " and t.handlingResult = null";
        }

        return cmsTaskService.findByPage(page, hql);
    }

    /**
     * 文件处理
     * @param taskId
     * @param commentContent
     */
    public void save(String taskId, String commentContent){
        CmsTask task = cmsTaskService.get(Long.valueOf(taskId));
        task.setHandlingSuggestion(commentContent);
        task.setHandlingResult("01");
        task.setIsRead(1L);
        task.setReadTime(new Timestamp(System.currentTimeMillis()));
        cmsTaskService.save(task);
    }
}
