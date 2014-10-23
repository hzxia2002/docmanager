package com.comet.doc.webservice;

import com.comet.doc.model.DataResults;
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

    @Override
    public String login(String userid, String password) {
        DataResults dataResults = new DataResults();
        List list = new ArrayList();
        Map map=new HashMap();

        String  userdeptName= "";
        String username = "夏红忠";
        map.put("result", "登陆成功");
        map.put("resulttype", "1");
        map.put("userid", userid);
        map.put("username", username);
        map.put("userdeptName", userdeptName);

        list.add(map);
        dataResults.setResultDataDesc("查询成功");
        dataResults.setResultList(list);
        return dataResultsToJsonStr(dataResults);
    }

    private String dataResultsToJsonStr(DataResults dataResults) {
        JSONObject jsonObj = JSONObject.fromObject(dataResults);
        if (jsonObj != null && !jsonObj.isNullObject() && !jsonObj.isEmpty()) {
            String jsonArrayStr = jsonObj.toString();
            System.out.println(jsonArrayStr);
            return jsonArrayStr;
        }
        return null;
    }
}
