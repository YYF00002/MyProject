package com.project.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
public class DateUtil {
	/**
     * 获取YYYY格式
     *
     * @return
     */
    public static String getYear() {
        return formatDate(new Date(), "yyyy");
    }

    /**
     * 获取YYYY格式
     *
     * @return
     */
    public static String getYear(Date date) {
        return formatDate(date, "yyyy");
    }
    /**
     * 获取yyyyMM格式
     * @param date
     * @return
     */
    public static String getYearAndMonth() {
        return formatDate(new Date(), "yyyyMM");
    }

    /**
     * 获取YYYY-MM-DD格式
     *
     * @return
     */
    public static String getDay() {
        return formatDate(new Date(), "yyyy-MM-dd");
    }

    /**
     * 获取YYYY-MM-DD格式
     *
     * @return
     */
    public static String getDay(Date date) {
        return formatDate(date, "yyyy-MM-dd");
    }

    /**
     * 获取YYYYMMDD格式
     *
     * @return
     */
    public static String getDays() {
        return formatDate(new Date(), "yyyyMMdd");
    }

    /**
     * 获取YYYYMMDD格式
     *
     * @return
     */
    public static String getDays(Date date) {
        return formatDate(date, "yyyyMMdd");
    }

    /**
     * 获取YYYY-MM-DD HH:mm:ss格式
     *
     * @return
     */
    public static String getTime() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 获取YYYY-MM-DD HH:mm:ss.SSS格式
     *
     * @return
     */
    public static String getMsTime() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss.SSS");
    }

    /**
     * 获取YYYYMMDDHHmmss格式
     *
     * @return
     */
    public static String getAllTime() {
        return formatDate(new Date(), "yyyyMMddHHmmss");
    }

    /**
     * 获取YYYY-MM-DD HH:mm:ss格式
     *
     * @return
     */
    public static String getTime(Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }
    public static String getTimeMM(Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm");
    }
    public static String formatDate(Date date, String pattern) {
        String formatDate = null;
        if (StringUtils.isNotBlank(pattern)) {
            formatDate = DateFormatUtils.format(date, pattern);
        } else {
            formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
        }
        return formatDate;
    }

    /**
     * @Title: compareDate
     * @Description:(日期比较，如果s>=e 返回true 否则返回false)
     * @param s
     * @param e
     * @return boolean
     * @throws
     */
    public static boolean compareDate(String s, String e) {
        if (parseDate(s) == null || parseDate(e) == null) {
            return false;
        }
        return parseDate(s).getTime() >= parseDate(e).getTime();
    }
    public static boolean compareDate(Date s, Date e) {
        if (s == null || s == null) {
            return false;
        }
        return s.getTime() >= s.getTime();
    }
    public static boolean compareDateNow(Date s) {
        if (s == null || s == null) {
            return false;
        }
        return s.getTime() >= System.currentTimeMillis();
    }

    /**
     * 格式化日期
     *
     * @return
     */
    public static Date parseDate(String date) {
        return parse(date,"yyyy-MM-dd");
    }

    /**
     * 格式化日期
     *
     * @return
     */
    public static Date parseTime(String date) {
        return parse(date,"yyyy-MM-dd HH:mm:ss");
    }


    /**
     * 格式化日期
     *
     * @return
     */
    public static Date parse(String date, String pattern) {
        try {
            return DateUtils.parseDate(date,pattern);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 把日期转换为Timestamp
     *
     * @param date
     * @return
     */
    public static Timestamp format(Date date) {
        return new Timestamp(date.getTime());
    }
    
    /**
     * 增加固定的天数
     * @param date
     * @param type
     * @param value
     * @return
     */
    public static Date addDate(Date date,int type , int value){
        Calendar canlendar = Calendar.getInstance(); // java.util包
        if (date!=null) {
            canlendar.setTime(date);
        }
        canlendar.add(type, value); // 日期减 如果不够减会将月变动
        return canlendar.getTime();
    }

    /**
     * 校验日期是否合法
     *
     * @return
     */
    public static boolean isValidDate(String s) {
        return parse(s, "yyyy-MM-dd HH:mm:ss") != null;
    }

    /**
     * 校验日期是否合法
     *
     * @return
     */
    public static boolean isValidDate(String s, String pattern) {
        return parse(s, pattern) != null;
    }

    public static int getDiffYear(String startTime, String endTime) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            int years = (int) (((fmt.parse(endTime).getTime() - fmt.parse(
                    startTime).getTime()) / (1000 * 60 * 60 * 24)) / 365);
            return years;
        } catch (Exception e) {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            return 0;
        }
    }

    /**
     * <li>功能描述：时间相减得到天数
     *
     * @param beginDateStr
     * @param endDateStr
     * @return long
     * @author Administrator
     */
    public static long getDaySub(String beginDateStr, String endDateStr) {
        long day = 0;
        SimpleDateFormat format = new SimpleDateFormat(
                "yyyy-MM-dd");
        Date beginDate = null;
        Date endDate = null;

        try {
            beginDate = format.parse(beginDateStr);
            endDate = format.parse(endDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
        // System.out.println("相隔的天数="+day);

        return day;
    }
    /**
     * <li>功能描述：时间相减得到小时
     *
     * @param beginDateStr
     * @param endDateStr
     * @return long
     * @author Administrator
     */
    public static long getHourSub(String beginDateStr, String endDateStr) {
        long day = 0;
        SimpleDateFormat format = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm");
        Date beginDate = null;
        Date endDate = null;

        try {
            beginDate = format.parse(beginDateStr);
            endDate = format.parse(endDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        day = (endDate.getTime() - beginDate.getTime()) / ( 60 * 60 * 1000);
        // System.out.println("相隔的天数="+day);

        return day;
    }
    /**
     * <li>功能描述：时间相减得到分钟
     *
     * @param beginDateStr
     * @param endDateStr
     * @return long
     * @author Administrator
     */
    public static long getMinSub(String beginDateStr, String endDateStr) {
        long min = 0;
        SimpleDateFormat format = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm");
        Date beginDate = null;
        Date endDate = null;

        try {
            beginDate = format.parse(beginDateStr);
            endDate = format.parse(endDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        min = (endDate.getTime() - beginDate.getTime()) / ( 60 * 1000);

        return min;
    }

    /**
     * 时间相减的 分钟数
     */
    public static long getMinSub(Date begin,Date end){
        long min = 0;
        min = (end.getTime() - begin.getTime()) / ( 60 * 1000);
        return min;
    }

    /**
     * 得到n天之后的日期
     *
     * @param days
     * @return
     */
    public static String getAfterDayDate(String days) {
        int daysInt = Integer.parseInt(days);

        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();

        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdfd.format(date);

        return dateStr;
    }

    /**
     * 得到n天之后是周几
     *
     * @param days
     * @return
     */
    public static String getAfterDayWeek(String days) {
        int daysInt = Integer.parseInt(days);

        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("E");
        String dateStr = sdf.format(date);

        return dateStr;
    }
    
    /**
     * 获取一个月的第一天
     * @param date
     * @return
     */
    public static String getFirstDayMonth(Date date) {
        Calendar canlendar = Calendar.getInstance(); // java.util包
        if (date!=null) {
            canlendar.setTime(date);
        }
        canlendar.set(Calendar.DATE, 1);
        date = canlendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(date);
        
        return dateStr;
    }
    
    /**
     * 获取一个月的最后一天
     * @param date
     * @return
     */
    public static String getLastDayMonth(Date date) {
        Calendar canlendar = Calendar.getInstance(); // java.util包
        if (date!=null) {
            canlendar.setTime(date);
        }
        canlendar.add(Calendar.MONTH, 1);
        canlendar.set(Calendar.DATE, 1);
        canlendar.add(Calendar.DATE, -1);
        date = canlendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(date);
        
        return dateStr;
    }
    /**
     * 获取今天的时间范围
     * @return 返回长度为2的字符串集合，如：[2017-11-03 00:00:00, 2017-11-03 24:00:00]
     */
    public static List<String> getTodayRange() {
        List<String> dataList = new ArrayList<String>(2);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.DATE, 0);
        String today = dateFormat.format(calendar.getTime());
        dataList.add(today + " 00:00:00");
        dataList.add(today + " 24:00:00");
        return dataList;
    }
    /**
     * 获取昨天的时间范围
     * @return 返回长度为2的字符串集合，如：[2017-11-02 00:00:00, 2017-11-02 24:00:00]
     */
    public static List<String> getYesterdayRange() {
        List<String> dataList = new ArrayList<String>(2);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.DATE, -1);
        String yesterday = dateFormat.format(calendar.getTime());
        dataList.add(yesterday + " 00:00:00");
        dataList.add(yesterday + " 24:00:00");
        return dataList;
    }
    /**
     * 获取本周的时间范围
     * @return 返回长度为2的字符串集合，如：[2017-10-30 00:00:00, 2017-11-05 24:00:00]
     */
    public static List<String> getCurrentWeekRange() {
        List<String> dataList = new ArrayList<String>(2);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);//设置周一为一周之内的第一天
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        String monday = dateFormat.format(calendar.getTime()) + " 00:00:00";
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        String sunday = dateFormat.format(calendar.getTime()) + " 24:00:00";
        dataList.add(monday);
        dataList.add(sunday);
        return dataList;
    }
    /**
     * 获取本周的时间范围(不带时分秒)
     * @return 返回长度为2的字符串集合，如：[2017-10-30, 2017-11-05]
     */
    public static List<String> getCurrentWeekRangeNoTime() {
        List<String> dataList = new ArrayList<String>(2);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);//设置周一为一周之内的第一天
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        String monday = dateFormat.format(calendar.getTime());
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        String sunday = dateFormat.format(calendar.getTime());
        dataList.add(monday);
        dataList.add(sunday);
        return dataList;
    }
    /**
     * 获取本月的时间范围
     * @return 返回长度为2的字符串集合，如：[2017-11-01 00:00:00, 2017-11-30 24:00:00]
     */
    public static List<String> getCurrentMonthRange() {
        List<String> dataList = new ArrayList<String>(2);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        String firstDayOfMonth = dateFormat.format(calendar.getTime()) + " 00:00:00";
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        String lastDayOfMonth = dateFormat.format(calendar.getTime()) + " 24:00:00";
        dataList.add(firstDayOfMonth);
        dataList.add(lastDayOfMonth);
        return dataList;
    }
    /**
     * 获取本年的时间范围
     * @return 返回长度为2的字符串集合，如：[2017-01-01 00:00:00, 2017-12-31 24:00:00]
     */
    public static List<String> getCurrentYearRange() {
        List<String> dataList = new ArrayList<String>(2);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.YEAR, 0);
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        String firstDayOfYear = dateFormat.format(calendar.getTime()) + " 00:00:00";
        calendar.add(Calendar.YEAR, 1);
        calendar.set(Calendar.DAY_OF_YEAR, 0);
        String lastDayOfYear = dateFormat.format(calendar.getTime()) + " 24:00:00";
        dataList.add(firstDayOfYear);
        dataList.add(lastDayOfYear);
        return dataList;
    }
    /**
     * 获取最近几天的时间范围
     * @param lastFewDays 最近多少天
     * @return 返回长度为2的字符串集合，如：[2017-12-25 17:15:33, 2017-12-26 17:15:33]
     */
    public static List<String> getLastFewDaysRange(int lastFewDays) {
        List<String> dataList = new ArrayList<String>(2);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        String endTime = dateFormat.format(calendar.getTime());
        calendar.add(Calendar.DATE, -lastFewDays);
        String startTime = dateFormat.format(calendar.getTime());
        dataList.add(startTime);
        dataList.add(endTime);
        return dataList;
    }
    /**
     * 获取当前时间
     * @param pattern 指定返回当前时间的格式，例："yyyy-MM-dd HH:mm:ss"
     * @return 返回指定格式的当前时间，如："2018-01-25 10:14:30"
     */
    public static String getCurrentTime(String pattern) {
        String currentTime;
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        currentTime = dateFormat.format(calendar.getTime());
        return currentTime;
    }
    /***
     * 将指定时间偏移几小时
     * @param time 指定时间，精确到分，例："2018-01-25 10:48"
     * @param offset 偏移量：负数代表减几个小时，正数代表加几个小时，例：1
     * @return 返回偏移后的时间，如："2018-01-25 11：48"
     * @throws ParseException
     */
    public static String offsetHours(String time, int offset) throws ParseException {
        String offsetHours = null;
        if (org.springframework.util.StringUtils.hasText(time)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateFormat.parse(time));
            calendar.add(Calendar.MINUTE, offset);
            offsetHours = dateFormat.format(calendar.getTime());
        }
        return offsetHours;
    }
    /**
     * 将指定月份偏移几个月
     * @param month 指定月份
     * @param offset 偏移量：负数代表上几个月，正数代表下几个月
     * @return 返回偏移后的月份，如：2018-01
     * @throws ParseException
     */
    public static String offsetMonths(String month, int offset) throws ParseException {
        String offsetMonth = null;
        if (org.springframework.util.StringUtils.hasText(month)) {
           SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
           Calendar calendar = Calendar.getInstance();
           calendar.setTime(dateFormat.parse(month));
           calendar.add(Calendar.MONTH, offset);
           offsetMonth = dateFormat.format(calendar.getTime());
        }
        return offsetMonth;
    }
    /**
     * 获取指定日期是星期几（设置星期一为一周的开始）
     * @param day 指定日期
     * @return 返回星期几，如：1
     * @throws ParseException
     */
    public static int getDayOfWeek(String day) throws ParseException {
        int dayOfWeek = 0;
        if (org.springframework.util.StringUtils.hasText(day)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateFormat.parse(day));
            calendar.add(Calendar.DATE, -1); //在指定日期的基础上减一天，满足中国人的习惯
            dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        }
        return dayOfWeek;
    }
    /**
     * 获取指定月份有多少天
     * @param month 指定月份
     * @return 返回天数，如：31
     * @throws ParseException
     */
    public static int getDaysInMonth(String month) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateFormat.parse(month));
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }
    /** 
     * 获取指定年份日历
     * @param year 
     * @return 
     */  
    private static Calendar getCalendarFormYear(int year){  
        Calendar cal = Calendar.getInstance();  
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);        
        cal.set(Calendar.YEAR, year);  
        return cal;  
    }
    /** 
     * 获取指定年指定周的开始日期
     * @param year 
     * @param weekNo 
     * @return 
     */  
    public static String getStartDayOfWeekNo(int year,int weekNo){  
        Calendar cal = getCalendarFormYear(year);  
        cal.set(Calendar.WEEK_OF_YEAR, weekNo);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        String monthStr = null;
        String dayStr = null;
        StringBuilder monthBuilder = new StringBuilder();
        StringBuilder dayBuilder = new StringBuilder();
        if(month < 10){
        monthStr = monthBuilder.append("0").append(month).toString();
        } else {
        monthStr = monthBuilder.append(month).toString();
        }
        if(day < 10) {
        dayStr = dayBuilder.append("0").append(day).toString();
        } else {
        dayStr = dayBuilder.append(day).toString();
        }
        return cal.get(Calendar.YEAR) + "-" + monthStr + "-" + dayStr;     
          
    }  
    /** 
     * 获取指定年指定周的结束日期
     * @param year 
     * @param weekNo 
     * @return 
     */  
    public static String getEndDayOfWeekNo(int year,int weekNo){  
        Calendar cal = getCalendarFormYear(year);  
        cal.set(Calendar.WEEK_OF_YEAR, weekNo);  
        cal.add(Calendar.DAY_OF_WEEK, 6); 
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        String monthStr = null;
        String dayStr = null;
        StringBuilder monthBuilder = new StringBuilder();
        StringBuilder dayBuilder = new StringBuilder();
        if(month < 10){
        monthStr = monthBuilder.append("0").append(month).toString();
        } else {
        monthStr = monthBuilder.append(month).toString();
        }
        if(day < 10) {
        dayStr = dayBuilder.append("0").append(day).toString();
        } else {
        dayStr = dayBuilder.append(day).toString();
        }
        return cal.get(Calendar.YEAR) + "-" + monthStr + "-" +  dayStr;      
    }  
    //获取 某日是某年的第几周
    public static int getWeekByDay(String day) throws ParseException {
         SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
         Calendar calendar = Calendar.getInstance();
         calendar.setFirstDayOfWeek(Calendar.SUNDAY);
         calendar.setTime(format.parse(day));
        return calendar.get(Calendar.WEEK_OF_YEAR);
  }
    
    //获取 指定开始日期和结束日期，包含日期列表。
   public static List<String> getDays(String beginDate, String endDate) throws ParseException {
  List<String> result = new ArrayList<String>();
  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
       Calendar calendar = Calendar.getInstance();  
       calendar.setTime(format.parse(beginDate));  
 
       for (long  time= calendar.getTimeInMillis(); time <= format.parse(endDate).getTime(); time = getNextDay(calendar)) {  
      result.add(format.format(time));  
       }  
  return result;
   }
   
   public static long getNextDay(Calendar calendar) {  
  calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1);  
       return calendar.getTimeInMillis();  
   } 
   public static void main(String args[]) {
	
   }
}
