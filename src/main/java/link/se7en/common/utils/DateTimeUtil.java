package link.se7en.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Link on 2015/9/1.
 * 时间日期工具类
 */
public class DateTimeUtil {

    final public static String dateFormatStr = "yyyy-MM-dd", dateFormatRegex = "[0-9]{4}-[0-9]{2}-[0-9]{2}" , formatStr_Mysql = "%Y-%m-%d",
                        dateTimeFormatStr = "yyyy-MM-dd HH:mm:ss";

    final static DateFormat dateFormat = new SimpleDateFormat(dateFormatStr),
                            dateTimeFormat = new SimpleDateFormat(dateTimeFormatStr);

    /**
     * 获取CurrDate后Offset日的所有日期
     * @param currDate 起始日期
     * @param offset 日期偏移
     * @return 日期数组
     */
    public static String [] getOffsetDays(String currDate, int offset){
        if (currDate == null) return null;
        if(currDate.isEmpty() || offset == 0) return null;
        if(!currDate.matches(dateFormatRegex)) return null;

        Calendar calendar = Calendar.getInstance();

        try {
            calendar.setTime(dateFormat.parse(currDate));
        } catch (ParseException e){
            return null;
        }

        String res[] = new String[Math.abs(offset)];
        int Dis = (offset > 0 ? 1 : -1);
        for(int i = 0;i< Math.abs(offset);i++){
            calendar.add(Calendar.DATE,Dis);
            res[i] = dateFormat.format(calendar.getTime());
        }
        return res;
    }

    /**
     * 获取CurrDate后Offset日的日期
     * @param currDate 起始日期
     * @param offset 日期偏移
     * @return 日期
     */
    public static String getOffsetDay(String currDate, int offset){
        if (currDate == null) return null;
        if(currDate.isEmpty()) return null;
        if(!currDate.matches(dateFormatRegex)) return null;

        Calendar calendar = Calendar.getInstance();

        try {
            calendar.setTime(dateFormat.parse(currDate));
        } catch (ParseException e){
            return null;
        }

        calendar.add(Calendar.DATE,offset);

        return dateFormat.format(calendar.getTime());
    }

    /**
     * 获取TarDate相对于OriDate的偏移日数
     * @param oriDate 原始日期
     * @param tarDate 偏移日期
     * @return 偏移日数
     */
    public static int getDayOffset(String oriDate, String tarDate){
        if(!oriDate.matches(dateFormatRegex)||!tarDate.matches(dateFormatRegex)) return 0;
        return (int)(getDateTimestamp(tarDate) - getDateTimestamp(oriDate)) / (3600 * 24);
    }

    /**
     * 获取今日日期
     * @return 今日日期
     */
    public static String getToday(){
        return dateFormat.format(new Date());
    }

    /**
     * 获取当前时间戳（SQL标准，精确到秒）
     * @return 时间戳
     */
    public static Long getCurrTimestamp(){
        return System.currentTimeMillis() / 1000;
    }

    /**
     * 获取指定日期的时间戳（SQL标准，精确到秒）
     * @param date 日期
     * @return 时间戳
     */
    public static Long getDateTimestamp(String date) {
        if(!date.matches(dateFormatRegex)) return 0L;
        Calendar calendar = Calendar.getInstance();

        try {
            calendar.setTime(dateFormat.parse(date));
            return calendar.getTime().getTime() / 1000L ;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0L;
    }

    /**
     * 毫秒转YYYY-mm-DD HH:MM:ss
     * @param timeMillis 毫秒
     * @return 格式化时间
     */
    public static String timeMillisToDateTime(long timeMillis) {
        return dateTimeFormat.format(new Date(timeMillis));
    }

    /**
     * 获得当前时间 YYYY-mm-DD HH:MM:ss
     * @return YYYY-mm-DD HH:MM:ss
     */
    public static String getNow() {
        return timeMillisToDateTime(System.currentTimeMillis());
    }
}
