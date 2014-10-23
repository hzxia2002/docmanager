package com.comet.system.manager;

import com.comet.system.daoservice.SysMessageInfoService;
import com.comet.system.domain.SysMessageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Project:comet.web
 * <p/>
 * <p>
 * 消息定义逻辑处理类
 * </p>
 * <p/>
 * Create On 2009-12-19 下午04:24:35
 *
 * @author <a href="mailto:hzxia2002@gmail.com">XiaHongzhong</a>
 * @version 1.0
 */
@Service
public class SysMessageInfoManager {
    @Autowired
    private SysMessageInfoService sysMessageInfoService;

    /**
     * 通过消息代码获取消息定义信息
     *
     * @param code
     * @return
     */
    public SysMessageInfo getByCode(String code) {
        String hql = "from SysMessageInfo t where t.fdMessageCode = '" + code + "'";

        List<SysMessageInfo> list = sysMessageInfoService.find(hql);

        if(list != null && list.size() > 0) {
            return list.get(0);
        }

        return null;
    }
}
