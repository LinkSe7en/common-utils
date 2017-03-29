package link.se7en.common.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Link on 2016/11/3.
 * 正则工具类
 */
public class RegexUtil {
    /**
     * 获取所有匹配的子字符串
     * @param str 要匹配的父字符串
     * @param regex 正则表达式
     * @return 子字符串
     */
    public static String[] getSubStrings(String str, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        String ret[] = null;
        List<String> hits = new ArrayList<>();
        while ( matcher.find() ) {
            hits.add(matcher.group());
        }
        if(hits.size() > 0)
            ret = hits.toArray(new String[0]);
        return ret;
    }

    /**
     * 获取第一个匹配的子字符串
     * @param str 要匹配的父字符串
     * @param regex 正则表达式
     * @return 子字符串
     */
    public static String getFirstMatch(String str,String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        String ret = null;
        if(matcher.find()) {
            ret = matcher.group();
        }
        return ret;
    }

    /**
     * 是否有匹配到的子字符串
     * @param str 要匹配的父字符串
     * @param regex 正则表达式
     * @return 是否有匹配
     */
    public static boolean matchPattern(String str,String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }
}
