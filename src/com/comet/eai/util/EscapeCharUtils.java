package com.comet.eai.util;

/**
 * Created with IntelliJ IDEA.
 * User: dell
 * Date: 12-10-22
 * Time: 下午6:36
 */
public class EscapeCharUtils {
    public static final String[] ESCAPE_CHAR = {"&", "<", ">", "'", "\""};

    public static final String[] ESCAPE_CHAR_REPLACE = {"&amp;", "&lt;", "&gt;", "&apos;", "&quot;"};

    /**
     * 判断字符串是否包含转义字符
     *
     * @param str
     * @return
     */
    public static boolean isContainEscapeChar(String str) {
        for(String tmp : ESCAPE_CHAR) {
            if(str.indexOf(tmp) > -1) {
                return true;
            }
        }

        return false;
    }

    /**
     * 替换转义字符
     *
     * @param str
     * @return
     */
    public static String replaceEscapeChar(String str) {
        for(int i=0; i<ESCAPE_CHAR.length; i++) {
            if(str.indexOf(ESCAPE_CHAR[i]) > -1) {
                str = str.replace(ESCAPE_CHAR[i], ESCAPE_CHAR_REPLACE[i]);
            }
        }

        return str;
    }
}
