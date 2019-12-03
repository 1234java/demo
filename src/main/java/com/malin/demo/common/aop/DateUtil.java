package com.malin.demo.common.aop;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.util.StringUtils;


/**
 * 日期格式工具
 *
 * @author ninglei
 */
public class DateUtil {

    /**
     * 获取当前时间 yyyy-MM-dd HH:mm:ss
     *
     * @return String字符串形态
     */
    public static String getCurrentTime() {
        Date date = new Date();
        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String datastr = simpleDateFormat.format(date);
        return datastr;
    }

   
    /**
     * 获取当前时间 yyyy-MM-dd HH:mm:ss
     *
     * @return String字符串形态
     */
    public static String getCurrentTime(String pattern) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String datastr = simpleDateFormat.format(date);
        return datastr;
    }

    public static final DateFormat getFormat() {
        return new SimpleDateFormat("yyyyMMdd HH:mm");
    }

    public static final DateFormat getMysqlFormat() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    public static String getCurrentDateStr()throws Exception{
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmss");
		return sdf.format(date);
	}
    public static final String simpleDateFormat(Date date) {
        SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formatDate = time.format(date);
        return formatDate;
    }

    public static final String simpleDateUserFormat(Date date, String formater) {
        SimpleDateFormat time = new SimpleDateFormat(formater);
        String formatDate = time.format(date);
        return formatDate;
    }

    public static final String formatUIDate(Date date) {
        SimpleDateFormat time = new SimpleDateFormat("yyyyMMdd");
        String formatDate = time.format(date);
        return formatDate;
    }

    public static final String formatUIDate(Date date, String pattern) {
        SimpleDateFormat time = new SimpleDateFormat(pattern);
        String formatDate = time.format(date);
        return formatDate;
    }

    public static final String formatDate(String dateString) {
        SimpleDateFormat time = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        Date date = null;
        try {
            date = time.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formatDate = time.format(date);
        return formatDate;
    }


    /**
     * <p>
     * Title: 周计算类，星期一为一周的开始，星期日为一周的结束
     * </p>
     * <p>
     * Description: 在两年的交接地带还有疑问。
     * </p>
     * <p>
     * 比如2006-12-29到2009-01-04，属于2008年的最后一周，
     * </p>
     * <p>
     * 2009-01-05位2009年第一周的开始。
     * </p>
     * <p>
     * db2种的week_iso也是这样计算的
     * </p>
     * <p>
     * Copyright: Copyright (c) 2006
     * </p>
     * <p>
     * DateTime: 2006-4-11 23:36:39
     * </p>
     *
     */
    /**
     * 取得当前日期是多少周
     *
     * @param date
     * @return
     */
    public static int getWeekOfYear(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setMinimalDaysInFirstWeek(7);
        c.setTime(date);

        return c.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 得到某一年周的总数
     *
     * @param year
     * @return
     */
    public static int getMaxWeekNumOfYear(int year) {
        Calendar c = new GregorianCalendar();
        c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);

        return getWeekOfYear(c.getTime());
    }

    /**
     * 得到某年某周的第一天
     *
     * @param year
     * @param week
     * @return
     */
    public static Date getFirstDayOfWeek(int year, int week) {
        Calendar c = new GregorianCalendar();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DATE, 1);

        Calendar cal = (GregorianCalendar) c.clone();
        cal.add(Calendar.DATE, week * 7);

        return getFirstDayOfWeek(cal.getTime());
    }

    /**
     * 得到某年某周的最后一天
     *
     * @param year
     * @param week
     * @return
     */
    public static Date getLastDayOfWeek(int year, int week) {
        Calendar c = new GregorianCalendar();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DATE, 1);

        Calendar cal = (GregorianCalendar) c.clone();
        cal.add(Calendar.DATE, week * 7);

        return getLastDayOfWeek(cal.getTime());
    }

    /**
     * 取得当前日期所在周的第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfWeek(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
        return c.getTime();
    }

    /**
     * 取得当前日期所在周的最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfWeek(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
        return c.getTime();
    }

    /**
     * 取得今天是本周第几天 以周日为第一天
     *
     * @date 查询时间
     * @int
     */
    public static int getNumofWeek(Date date) {
        // 取得当前系统时间
        Calendar c = Calendar.getInstance();
        if (date != null) c.setTime(date);
        // // -2天
        // c.add(Calendar.DAY_OF_MONTH, -2);
        // 取得当前是这周的第几天
        int temp = c.get(Calendar.DAY_OF_WEEK);
        // 由于java计算是以周日为第一天
        return temp;
        // if (temp == 1) {
        // // 当是第一天时返回周日
        // return 7;
        // } else {
        // // 其他情况直接返回数字-1
        // return temp;
        // }
    }

    /**
     * 取得今天是本周第几天 以周日为第一天 方法重载，默认为系统当前时间
     *
     * @int
     */
    public static int getNumofWeek() {
        return getNumofWeek(null);
    }

    /**
     * 以字符串形式得到今日为周几
     *
     * @param date
     * @return
     */
    public static String getNumOfWeekStr(Date date) {
        int num = getNumofWeek(date);
        String weekNum = "";
        switch (num) {
            case 1:
                weekNum = "日";
                break;
            case 2:
                weekNum = "一";
                break;
            case 3:
                weekNum = "二";
                break;
            case 4:
                weekNum = "三";
                break;
            case 5:
                weekNum = "四";
                break;
            case 6:
                weekNum = "五";
                break;
            case 7:
                weekNum = "六";
                break;
        }
        return weekNum;
    }

    /**
     * 以字符串形式得到今日为周几 默认系统时间，方法重载
     *
     * @return
     */
    public static String getNumOfWeekStr() {
        return getNumOfWeekStr(null);
    }

    /**
     * 得到几天前的时间
     *
     * @param d
     * @param day
     * @return
     */
    public static Date getDateBefore(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
        return now.getTime();
    }

    /**
     * 得到几天后的时间
     *
     * @param d
     * @param day
     * @return
     */
    public static Date getDateAfter(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        return now.getTime();
    }

    /**
     * 得到某年1月1日
     *
     * @return
     */
    public static Date getFirstDayOfThisYear() {
        Calendar c = new GregorianCalendar();
        Calendar now = Calendar.getInstance();
        c.set(Calendar.YEAR, now.get(Calendar.YEAR));
        c.set(Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DATE, 1);
        Calendar cal = (GregorianCalendar) c.clone();
        return getFirstDayOfWeek(cal.getTime());
    }

    /**
     * 取得当前月份的第一天
     *
     * @return
     */
    public static Date getFirstDayOfMonth(int Month) {
        Calendar c = new GregorianCalendar();
        Calendar now = Calendar.getInstance();
        c.set(Calendar.YEAR, now.get(Calendar.YEAR));
        c.set(Calendar.MONDAY, Month - 1);
        c.set(Calendar.DAY_OF_MONTH, 1);
        return c.getTime();
    }

    /**
     * 取得当前月份的最后一天
     *
     * @return
     */
    public static Date getLatDayOfMonth(int Month) {
        Calendar c = new GregorianCalendar();
        Calendar now = Calendar.getInstance();
        c.set(Calendar.YEAR, now.get(Calendar.YEAR));
        c.set(Calendar.MONTH, Month);
        c.set(Calendar.DAY_OF_MONTH, 1);
        return getDateBefore(c.getTime(), 1);
    }

    /**
     * 取得当前月份
     *
     * @return
     */
    public static int getNowMonth() {
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());
        return now.get(Calendar.MONTH);
    }

    public static int getMonth(Date date) {
        Calendar canCalendar = Calendar.getInstance();
        canCalendar.setTime(date);
        return canCalendar.get(Calendar.MONTH);
    }

    /**
     * 取得当前月份的第一天
     *
     * @return
     */
    public static Date getMonth() {
        Calendar c = new GregorianCalendar();
        Calendar now = Calendar.getInstance();
        c.set(Calendar.YEAR, now.get(Calendar.YEAR));
        c.set(Calendar.MONTH, now.get(Calendar.MONTH));
        c.set(Calendar.DAY_OF_MONTH, 1);
        return c.getTime();
    }

    public static Date getMonth(int i) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.MONTH, i - 1);
        return c.getTime();
    }

    public static boolean CompareDate(String date1, String date2) throws ParseException {
        Date Time1 = getMysqlFormat().parse(date1);
        Date Time2 = getMysqlFormat().parse(date2);
        if (Time1.before(Time2)) return true;
        return false;

    }

    public static final String getTwresulttime() {
        Date date = new Date();
        SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formatDate = time.format(date);
        return formatDate;
    }

    public static String getcurrentDatetime(String style) {
        SimpleDateFormat format = new SimpleDateFormat(style);
        Date currentTime = new Date();
        String currentDatetime = format.format(currentTime);
        return currentDatetime;
    }

    public static Date String2Date(String dateString, String style) {
        if (StringUtils.isEmpty(dateString)) return null;
        Date date = new Date();
        SimpleDateFormat strToDate = new SimpleDateFormat(style);
        // parse format String to date
        try {
            date = strToDate.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Timestamp String2Timestamp(String dateString, String style) {
        SimpleDateFormat df = new SimpleDateFormat(style);
        String time = df.format(String2Date(dateString, style));
        Timestamp ts = Timestamp.valueOf(time);
        return ts;
    }

    public static String DateToCronExpression(Date date) {
        SimpleDateFormat time = new SimpleDateFormat("ss mm HH dd MM ?");
        String formatDate = time.format(date);
        return formatDate;
    }

    /**
     * 获取当天属本月的天数
     *
     * @return
     */
    public static int getDay() {
        Calendar now = Calendar.getInstance();
        return now.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 得到天数值
     *
     * @param d
     * @param size
     * @return
     */
    public static Integer[] getBeforeDays(Date d, int size) {
        Integer[] days = new Integer[size];
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        for (int i = 0; i < size; i++) {
            now.set(Calendar.DATE, now.get(Calendar.DATE) - i);
            days[i] = now.get(Calendar.DAY_OF_MONTH);
        }
        return days;
    }

    /**
     * 得到天数值
     *
     * @param size
     * @return
     */
    public static String getBeforeDay(int size) {
        StringBuilder days = new StringBuilder();
        Calendar now = new GregorianCalendar();
        int day = now.get(Calendar.DAY_OF_MONTH);
        day = day - size;
        int month = now.get(Calendar.MONTH);
        if (day > 0) {
            month++;
        }
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                days.append(",");
            }
            if (day == 0) {
                month++;
            }
            now.set(2013, month, ++day);
            days.append("'").append(month).append("月").append(now.get(Calendar.DAY_OF_MONTH)).append("日'");
        }
        return days.toString();
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws java.text.ParseException
     */
    public static int daysBetween(Date smdate, Date bdate) {
        long between_days = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            smdate = sdf.parse(sdf.format(smdate));
            bdate = sdf.parse(sdf.format(bdate));
            Calendar cal = Calendar.getInstance();
            cal.setTime(smdate);
            long time1 = cal.getTimeInMillis();
            cal.setTime(bdate);
            long time2 = cal.getTimeInMillis();
            between_days = (time2 - time1) / (1000 * 3600 * 24);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 字符串的日期格式的计算
     */
    public static int daysBetween(String smdate, String bdate) {
        long between_days = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(smdate));
            long time1 = cal.getTimeInMillis();
            cal.setTime(sdf.parse(bdate));
            long time2 = cal.getTimeInMillis();
            between_days = (time2 - time1) / (1000 * 3600 * 24);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 获取前一个月的第一天
     */
    public static String getMonthFirstDay() {
        // 取得系统当前时间
        Calendar cal = Calendar.getInstance();
        // 取得系统当前时间所在月第一天时间对象
        cal.set(Calendar.DAY_OF_MONTH, 1);
        // 日期减一,取得上月最后一天时间对象
        cal.add(Calendar.DAY_OF_MONTH, -1);
        // 输出上月最后一天日期
        return simpleDateUserFormat(cal.getTime(), "yyyy-MM-dd");
    }

    /**
     * 获取上一个月的最后一天
     */
    public static String getMonthLastDay() {
        Calendar cal_1 = Calendar.getInstance();// 获取当前日期
        cal_1.add(Calendar.MONTH, -1);
        cal_1.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
        return simpleDateUserFormat(cal_1.getTime(), "yyyy-MM-dd");
    }


    /**
     * date前minute 分钟
     *
     * @param date   时间
     * @param minute 分钟差
     * @return
     */
    public static Date getBeforeMinute(Date date, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) - minute);
        return calendar.getTime();
    }

    public static Date getDaysBefore(Date date,int days){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - days);
        return calendar.getTime();
    }

	/*
	 * public static void main(String[] args) {
	 * //System.out.println(simpleDateFormat(getLatDayOfMonth(2))); //
	 * System.out.println(simpleDateFormat(getBeforeMinute(new Date(), 30)));
	 * 
	 * // System.out.println(DateUtil.formatUIDate(getDaysBefore(new Date(),1)));
	 * System.out.println(DateUtil.getCurrentTime());
	 * 
	 * }
	 */
}
