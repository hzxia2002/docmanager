package com.comet.system.sms;

import org.apache.xpath.operations.Bool;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: dell
 * Date: 12-12-2
 * Time: 下午9:38
 * To change this template use File | Settings | File Templates.
 */
public class SmsResult implements Serializable {
    public static Map resultMap = new HashMap();

    static {
        resultMap.put("1000", "成功");
        resultMap.put("3001", "请求格式错误");
        resultMap.put("3002", "用户名、密码");
        resultMap.put("3003", "模板id不存在");
        resultMap.put("3004", "变量数据与模板不匹配");
        resultMap.put("3005", "超过50个变量标签");
        resultMap.put("3006", "扩展端口错误");
        resultMap.put("3007", "号码超过了1000个数量限制");
        resultMap.put("3008", "无合法手机号");
        resultMap.put("3009", "令牌错误");
        resultMap.put("9999", "其他错误");
    }

    private String retCode;

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    /**
     * 获取发送返回消息
     *
     * @return
     */
    public String getRetMsg() {
        if(resultMap.keySet().contains(retCode)) {
            return (String)resultMap.get(retCode);
        }

        return "未定义错误!";
    }

    /**
     * 是否成功发送
     *
     * @return
     */
    public Boolean isSuccess() {
        if(retCode.equals("1000")) {
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }
}
