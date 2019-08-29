package com.project.utils;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* @Description:    字符串处理工具类
* @Author:         YYF
* @CreateDate:     2019/8/28 18:09
* @Version:        1.0
*/
public class StringUtils {

    private static Pattern linePattern = Pattern.compile("_(\\w)");
    private static Pattern humpPattern = Pattern.compile("[A-Z]");

    /**
     * 生成唯一的key用于表主键
     *
     * @return
     */
    public static synchronized String genUUID() {
        //获取UUID
        String uuid = UUID.randomUUID() + "";
        //进行MD5加密
        return MD5.encrypt16(uuid).toUpperCase();
    }




    /**
     * 下划线转驼峰
     *
     * @param str
     * @return
     */
    public static String lineToHump(String str) {
        if (null == str || "".equals(str)) {
            return str;
        }
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);

        str = sb.toString();
        str = str.substring(0, 1).toUpperCase() + str.substring(1);

        return str;
    }


    /**
     * 驼峰转下划线,效率比上面高
     *
     * @param str
     * @return
     */
    public static String humpToLine(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }




    /**
     * 首字母转小写
     *
     * @param s
     * @return
     */
    public static String toLowerCaseFirstOne(String s) {
        if (StringUtils.isNull(s)) {
            return s;
        }
        if (Character.isLowerCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }

    /**
     * 首字母转大写
     *
     * @param s
     * @return
     */
    public static String toUpperCaseFirstOne(String s) {
        if (StringUtils.isNull(s)) {
            return s;
        }
        if (Character.isUpperCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuffer()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }






    /**
     * <b> 功能 : 从一组值中得到不重复的值数组，一般用于如对于ID数组的过滤，滤掉重复值 </b>
     */
    public static String[] getDistinctArray(String[] saOrgValue) {
        if (saOrgValue == null) {
            return null;
        }

        HashMap mapRet = new HashMap();
        int iLen = saOrgValue.length;
        for (int i = 0; i < iLen; i++) {
            if (saOrgValue[i] != null) {
                mapRet.put(saOrgValue[i], "");
            }
        }
        if (mapRet.size() == 0) {
            return null;
        }
        return (String[]) mapRet.keySet().toArray(new String[mapRet.size()]);
    }

    /**
     * 判断字符串是否为空
     * @param value
     * @return
     */
    public static boolean isNull(String value) {
        if (value == null || value.trim().length() == 0 || "null".equals(value)|| "".equals(value)) {
            //为空
            return true;
        }
        //不为空
        return false;
    }

    /**
     * <b> 功能 : 判断两个字符串是否相等，均为NULL　或　TRIM＝NULL　认为相等 </b>
     *
     * @param sValue1 串1
     * @param sValue2 串2
     * @return 是否相等
     * @作者 张帅
     * @创建日期 2011-9-8
     */
    public static boolean isEqual(String sValue1, String sValue2) {

        String sTemp1 = !isNull(sValue1)  ? ""
                : sValue1;
        String sTemp2 = !isNull(sValue2)  ? ""
                : sValue2;

        return sTemp1.equals(sTemp2);
    }


    /**
     * 特殊字符转义，实现对一些特殊字符的转义操作，防止处理的时候出错
     *
     * @param text
     * @return
     */
    public static String transfString(String text) {

        if (null != text) {
            text = text.replace(">", "&gt;");
            text = text.replace("<", "&lt;");
            text = text.replace(" ", "&nbsp;");
            text = text.replace("\"", "&quot;");
            text = text.replace("\'", "&#39;");
            text = text.replace("\\", "\\\\");
            text = text.replace("\n", "\\n");
            text = text.replace("\r", "\\r");
        }

        return text;
    }


    /**
     * 转义还原，在RSA解密的时候转义的字符需要还原，否则会解密出错
     *
     * @param text
     * @return
     */
    public static String transfBack(String text) {

        if (null != text) {
            text = text.replace("&gt;", ">");
            text = text.replace("&lt;", "<");
            text = text.replace("&nbsp;", " ");
            text = text.replace("&quot;", "\"");
            text = text.replace("&#39;", "\'");
            text = text.replace("\\\\", "\\");
            text = text.replace("\\n", "\n");
            text = text.replace("\\r", "\r");
        }

        return text;
    }

}
