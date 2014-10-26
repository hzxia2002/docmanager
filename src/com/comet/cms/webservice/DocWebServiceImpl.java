package com.comet.cms.webservice;

import com.comet.core.utils.CryptUtil;
import com.comet.core.utils.SpringUtils;
import com.comet.cms.model.DataResults;
import com.comet.system.domain.SysDept;
import com.comet.system.domain.SysUser;
import com.comet.system.manager.SysUserManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2014/10/24.
 */
@WebService
public class DocWebServiceImpl implements DocWebService {
    private static SysUserManager sysUserManager;

    static {
        sysUserManager = (SysUserManager)SpringUtils.getBean("sysUserManager");
    }

    /**
     * 将DataResults转换为JSON字符串
     *
     * @param dataResults
     * @return
     */
    private String dataResultsToJsonStr(DataResults dataResults) {
        JSONObject jsonObj = JSONObject.fromObject(dataResults);
        if (jsonObj != null && !jsonObj.isNullObject() && !jsonObj.isEmpty()) {
            String jsonArrayStr = jsonObj.toString();
            System.out.println(jsonArrayStr);
            return jsonArrayStr;
        }
        return null;
    }

    /**
     * 将List转换为JSON字符串
     *
     * @param obj
     * @return
     */
    private String listToJsonStr(List obj) {
        JSONArray jsonObj = JSONArray.fromObject(obj);
        if (jsonObj != null && !jsonObj.isEmpty()) {
            String jsonArrayStr = jsonObj.toString();
            return jsonArrayStr;
        }
        return null;
    }

    @Override
    public String login(String userid, String password) throws Exception {
        DataResults dataResults = new DataResults();
        List list = new ArrayList();
        Map map=new HashMap();

        SysUser user = sysUserManager.getByUsername(userid);

        if(user == null) {
            map.put("result", "当前用户不存在");
            map.put("resulttype", "0");
        } else {
            if(!CryptUtil.decrypt(user.getPassword()).equals(password)) {
                map.put("result", "您输入的口令不正确");
                map.put("resulttype", "0");
            } else {
                map.put("resulttype", "1");
                map.put("userid", userid);
                map.put("username", user.getDisplayName());

                // 取得用户所在的部门
                SysDept dept = sysUserManager.getUserDept(user.getId());
                if(dept != null) {
                    map.put("userdeptName", dept.getName());
                } else {
                    map.put("userdeptName", "");
                }
            }
        }

        list.add(map);
        dataResults.setResultDataDesc("查询成功");
        dataResults.setResultList(list);
        return dataResultsToJsonStr(dataResults);
    }


}
