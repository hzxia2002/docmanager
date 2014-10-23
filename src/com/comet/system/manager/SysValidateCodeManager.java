package com.comet.system.manager;

import com.comet.system.daoservice.SysValidateCodeService;
import com.comet.system.domain.SysValidateCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dell
 * Date: 13-5-2
 * Time: 下午3:06
 * To change this template use File | Settings | File Templates.
 */
@Service
public class SysValidateCodeManager {
    @Autowired
    private SysValidateCodeService sysValidateCodeService;

    /**
     * 取得最新验证码
     *
     * @param bean
     * @return
     */
    public SysValidateCode get(SysValidateCode bean) {
        String hql = "from SysValidateCode t where t.validateCode = '" + bean.getValidateCode() + "'";
        hql += " and t.validateCodeType = '" + bean.getValidateCodeType() + "'";

        if(StringUtils.isNotEmpty(bean.getNickName())) {
            hql += " and t.nickName = '" + bean.getNickName() + "'";
        }

        if(StringUtils.isNotEmpty(bean.getEmail())) {
            hql += " and t.email = '" + bean.getEmail() + "'";
        }

        if(StringUtils.isNotEmpty(bean.getMobile())) {
            hql += " and t.mobile = '" + bean.getMobile() + "'";
        }

        if(StringUtils.isNotEmpty(bean.getSessionId())) {
            hql += " and t.sessionId = '" + bean.getSessionId() + "'";
        }

        hql += " and t.valid = '1'";
        hql += " order by t.createTime desc";

        List<SysValidateCode> list = sysValidateCodeService.find(hql);

        if(list != null && list.size() > 0) {
            return list.get(0);
        }

        return null;
    }
}
