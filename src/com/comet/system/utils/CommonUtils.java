package com.comet.system.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by dell on 2014/6/28.
 */
public class CommonUtils {
    private static Log log = LogFactory.getLog(CommonUtils.class);

    /**
     * 取得公司类型名称
     *
     * @param type
     * @return
     */
    public static String getDeptTypeName(Long type) {
        String[] statusArray = Constants.COMPANY_TYPE;
        String[] statusNameArray = Constants.COMPANY_TYPE_NAME;

        for(int i=0; i<statusArray.length; i++) {
            if(statusArray[i].equals(String.valueOf(type))) {
                return statusNameArray[i];
            }
        }

        return statusNameArray[0];
    }

    /**
     * 取得会员卡号
     *
     * @return
     */
    public static String getUserId(Long id) {
        String ret = "1";
        String ids = String.valueOf(id);

        for(int i=ids.length(); i<9; i++) {
            ret += "0";
        }

        ret += ids;

        return ret;
    }
}
