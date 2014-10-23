package com.comet.system.manager;

import com.comet.system.daoservice.SysCodeDetailService;
import com.comet.system.daoservice.SysCodeService;
import com.comet.system.domain.SysCode;
import com.comet.system.domain.SysCodeDetail;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * Project:bcscmis
 *
 * <p>
 * 系统代码逻辑处理类
 * </p>
 *
 * Create On 2009-12-31 下午07:16:08
 *
 * @author <a href="mailto:hzxia2002@gmail.com">XiaHongzhong</a>
 * @version 1.0
 */
@Service
public class SysCodeManager {
	@Autowired
	private SysCodeDetailService sysCodeDetailService;

    @Autowired
    private SysCodeService sysCodeService;

	/**
	 * 根据代码类型和代码值取得详细代码
	 *
	 * @param mainCode
	 *            代码类型code
	 * @param detailCode
	 *            代码值
	 * @return 代码值
	 */
	public SysCodeDetail getCodeListByCode(String mainCode, String detailCode) {
		String hql = "from SysCodeDetail t where t.sysCode.code = '" + mainCode
				+ "' and t.code = '" + detailCode + "'";

		List<SysCodeDetail> list = sysCodeDetailService.find(hql);

		if (list != null && list.size() > 0) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * 根据代码取得代码列表值
	 *
	 * @param code
	 *            代码类型Code
	 * @return SysCodeDetail列表
	 */
	public List<SysCodeDetail> getCodeDetailListByCode(String code) {
		return getCodeDetailListByCode(code, null, null);
	}

    /**
     * 根据代码取得代码列表值
     *
     * @param code
     * @param parentId
     * @return
     */
    public List<SysCodeDetail> getCodeDetailListByCode(String code, Long parentId) {
        return getCodeDetailListByCode(code, parentId, null);
    }

    /**
     * 根据代码取得代码列表值
     * @param code 代码类型code
     * @param orderBy 排序条件
     * @return
     */
    public List<SysCodeDetail> getCodeDetailListByCode(String code, Long parentId, String orderBy) {
        String hql = "from SysCodeDetail t where lower(t.sysCode.code) = '" + StringUtils.lowerCase(code) + "' ";

        if(parentId == null) {
            hql += " and t.parent.id is null ";
        } else if(parentId != 0L) {
            hql += " and t.parent.id = " + parentId;
        }

        if(StringUtils.isNotEmpty(orderBy)) {
            hql += orderBy;
        } else {
            hql += " order by t.treeId";
        }

        return sysCodeDetailService.find(hql);
    }

    /**
     * 取系统代码明细
     * @param id
     * @return
     */
    public SysCodeDetail getCodeDetail(Long id) {
        return sysCodeDetailService.get(id);
    }

    /**
     * 取系统代码集及其子集
     * @param id
     * @return
     */
    public List<SysCode> findChildAndSelf(String id){
        SysCode unique = sysCodeService.findUnique("from SysCode where id=" + id);
        return sysCodeService.find("from SysCode where treeId like '"+unique.getTreeId()+"%'");
    }

    public List<SysCode> findChildren(String code){
        SysCode unique = sysCodeService.findUnique("from SysCode where code='" + code +"'");
        return sysCodeService.find("from SysCode where parent.id = "+unique.getId());
    }
}
