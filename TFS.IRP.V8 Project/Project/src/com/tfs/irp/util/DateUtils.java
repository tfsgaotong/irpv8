package com.tfs.irp.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class DateUtils {
    /**
     * 取时间年的格式代码
     */
    public static String YEAR = "yyyy";

    /**
     * 取时间月的格式代码
     */
    public static String MONTH = "MM";

    /**
     * 取时间日的格式代码
     */
    public static String DAY = "dd";

    /**
     * 取时间时的格式代码
     */
    public static String HOUR = "hh";

    /**
     * 取时间24小时制的格式代码
     */
    public static String HOUR_24 = "HH";

    /**
     * 取时间分的格式代码
     */
    public static String MIMUTE = "mm";

    /**
     * 取时间秒的格式代码
     */
    public static String SECOND = "ss";

    /**
     * 取时间毫秒的格式代码
     */
    public static String MILLISECOND = "SS";

    /**
     * 格式为yyyy-MM-dd的时间
     */
    public static String YMD_FORMAT = YEAR + "-" + MONTH + "-" + DAY;

    /**
     * 格式为yyyy-MM-dd HH:mm:ss的时间
     */
    public static String YMDHMS_FORMAT = YEAR + "-" + MONTH + "-" + DAY + " " + HOUR_24 + ":" + MIMUTE + ":" + SECOND;

    /**
     * 格式为yyyy-MM-dd HH:mm:ss:SS的时间
     */
    public static String UTILTIME_FORMAT = YEAR + "-" + MONTH + "-" + DAY + " " + HOUR_24 + ":" + MIMUTE + ":" + SECOND
            + ":" + MILLISECOND;

    /**
     * 格式为yyyyMMddHHmmssSS的时间
     */
    public static String CRITERIONTIME_FORMAT = YEAR + MONTH + DAY + HOUR_24 + MIMUTE + SECOND + MILLISECOND;

    public static String getYearMonthDay() {
        return getDateByFormat(YEAR + "-" + MONTH + "-" + DAY);
    }

    /**
     * @see 返回格式为yyyy-MM-dd HH:mm:ss:SS的时间字符串
     * @return String date
     */
    public static String getNOWTime_0() {
        return getDateByFormat(UTILTIME_FORMAT);
    }

    /**
     * @see 返回格式为yyyyMMddHHmmssSS的时间字符串
     * @return String date
     */
    public static String getNOWTime_1() {
        return getDateByFormat(CRITERIONTIME_FORMAT);
    }

    /**
     * @see 获得指定时间格式
     * @param String
     *            format 时间格式
     * @return String dateStr 返回获得相应格式时间的字符串
     */
    public static String getDateByFormat(String format) {
        String dateStr = "";
        try {
            if (format != null) {
                Date date = new Date(System.currentTimeMillis());
                SimpleDateFormat simFormat = new SimpleDateFormat(format, Locale.CHINA);
                dateStr = simFormat.format(date);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateStr;
    }

    /**
     * @see 获得指定时间格式
     * @param Date
     *            date 时间
     * @param String
     *            format 时间格式
     * @return String dateStr 返回获得相应格式时间的字符串
     */
    public static String getDateByFormatYMD(Date date) {
        String dateStr = "";
        try {
            SimpleDateFormat simFormat = new SimpleDateFormat(YMD_FORMAT, Locale.CHINA);
            dateStr = simFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateStr;
    }

    /**
     * @see 获得指定时间格式
     * @param Date
     *            date 时间
     * @param String
     *            format 时间格式
     * @return String dateStr 返回获得相应格式时间的字符串
     */
    public static String getDateByFormat(Date date, String format) {
        String dateStr = "";
        try {
            if (format != null) {
                SimpleDateFormat simFormat = new SimpleDateFormat(format, Locale.CHINA);
                dateStr = simFormat.format(date);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateStr;
    }

    /**
     * @see 获得当前时间
     * @return Date date
     */
    public static Date getNOWTime() {
        return new Date(System.currentTimeMillis());
    }

    /**
     * @see 把字符串类型的时间转换为yyyy-MM-dd的时间格式
     */
    public static Date getDateByStrToYMD(String str) {
        Date date = null;
        if (str != null && str.trim().length() > 0) {
            DateFormat dFormat = new SimpleDateFormat(YMD_FORMAT);
            try {
                date = dFormat.parse(str);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return date;
    }

    /**
     * @see 把字符串类型的时间转换为自定义格式的时间
     */
    public static Date getDateByStrToFormat(String format, String str) {
        DateFormat dFormat = new SimpleDateFormat(format);
        Date date = null;
        try {
            if (str != null) {
                date = dFormat.parse(str);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 功能：判断输入年份是否为闰年<br>
     * 
     * @param year
     * @return 是：true 否：false
     * @author pure
     */
    public static boolean leapYear(int year) {
        boolean leap;
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                if (year % 400 == 0) {
                    leap = true;
                } else {
                    leap = false;
                }
            } else {
                leap = true;
            }
        } else
            leap = false;
        return leap;
    }

    /**
     * 功能：得到指定月份的月底 格式为：xxxx-yy-zz (eg: 2007-12-31)<br>
     * 
     * @param String
     * @return String
     */
    public static String getEndOfMonth(String str) {
        int tyear = Integer.parseInt(getDateByFormat(getDateByStrToYMD(str), YEAR));
        int tmonth = Integer.parseInt(getDateByFormat(getDateByStrToYMD(str), MONTH));
        String strtmonth = null;
        String strZ = null;
        if (tmonth == 1 || tmonth == 3 || tmonth == 5 || tmonth == 7 || tmonth == 8 || tmonth == 10 || tmonth == 12) {
            strZ = "31";
        }
        if (tmonth == 4 || tmonth == 6 || tmonth == 9 || tmonth == 11) {
            strZ = "30";
        }
        if (tmonth == 2) {
            if (leapYear(tyear)) {
                strZ = "29";
            } else {
                strZ = "28";
            }
        }
        strtmonth = tmonth >= 10 ? String.valueOf(tmonth) : ("0" + tmonth);
        return tyear + "-" + strtmonth + "-" + strZ;
    }

    public static String getEndOfMonth(int tyear, int tmonth) {
        String strtmonth = null;
        String strZ = null;
        if (tmonth == 1 || tmonth == 3 || tmonth == 5 || tmonth == 7 || tmonth == 8 || tmonth == 10 || tmonth == 12) {
            strZ = "31";
        }
        if (tmonth == 4 || tmonth == 6 || tmonth == 9 || tmonth == 11) {
            strZ = "30";
        }
        if (tmonth == 2) {
            if (leapYear(tyear)) {
                strZ = "29";
            } else {
                strZ = "28";
            }
        }
        strtmonth = tmonth >= 10 ? String.valueOf(tmonth) : ("0" + tmonth);
        return tyear + "-" + strtmonth + "-" + strZ;
    }

    /**
     * 功能：得到指定月份的月初 格式为：xxxx-yy-zz (eg: 2007-12-01)<br>
     * 
     * @param String
     * @return String
     */
    public static String getStartOfMonth(int tyear, int tmonth) {
        String strtmonth = tmonth >= 10 ? String.valueOf(tmonth) : ("0" + tmonth);
        return tyear + "-" + strtmonth + "-" + "01";
    }

    public static String getStartOfMonth(String str) {
        int tyear = Integer.parseInt(getDateByFormat(getDateByStrToYMD(str), YEAR));
        int tmonth = Integer.parseInt(getDateByFormat(getDateByStrToYMD(str), MONTH));
        String strtmonth = tmonth >= 10 ? String.valueOf(tmonth) : ("0" + tmonth);
        return tyear + "-" + strtmonth + "-" + "01";
    }

    /**
     * 功能：得到指定月份的月初 格式为：xxxx-yy-zz (eg: 2007-12-01)<br>
     * 
     * @param String
     * @return String
     */
    public static int getMonthCountBySQU(String start, String end) {
        int syear = Integer.parseInt(getDateByFormat(getDateByStrToYMD(start), YEAR));
        int smonth = Integer.parseInt(getDateByFormat(getDateByStrToYMD(start), MONTH));
        int eyear = Integer.parseInt(getDateByFormat(getDateByStrToYMD(start), YEAR));
        int emonth = Integer.parseInt(getDateByFormat(getDateByStrToYMD(start), MONTH));
        return (eyear - syear) * 12 + (emonth - smonth) + 1;
    }

    /**
     * 获得时间序列 EG:2008-01-01~2008-01-31,2008-02-01~2008-02-29
     */
    public static List getMonthSqu(String fromDate, String toDate) {
        List list = new ArrayList();
        int count = getMonthCountBySQU(fromDate, toDate);
        int syear = Integer.parseInt(getDateByFormat(getDateByStrToYMD(fromDate), YEAR));
        int smonth = Integer.parseInt(getDateByFormat(getDateByStrToYMD(fromDate), MONTH));
        int eyear = Integer.parseInt(getDateByFormat(getDateByStrToYMD(toDate), YEAR));
        String startDate = fromDate;
        String endDate = "";
        for (int i = 1; i <= count; i++) {
            if (syear <= eyear) {
                startDate = getStartOfMonth(syear, smonth);
                endDate = getEndOfMonth(syear, smonth);
                list.add(startDate + "~" + endDate);
                if (smonth == 13) {
                    smonth = 1;
                    syear++;
                }
                smonth++;
            }
        }
        return list;
    }

    /**
     * 通过传入的时间来获得所属周内的时间
     * 
     * @param start
     * @param num
     * @return
     */
    public static String getDateOFWeekByDate(String start, int num) {
        Date dd = getDateByStrToYMD(start);
        Calendar c = Calendar.getInstance();
        c.setTime(dd);
        if (num == 1) // 返回星期一所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        else if (num == 2) // 返回星期二所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
        else if (num == 3) // 返回星期三所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
        else if (num == 4) // 返回星期四所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
        else if (num == 5) // 返回星期五所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        else if (num == 6) // 返回星期六所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        else if (num == 0) // 返回星期日所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return getDateByFormatYMD(c.getTime());
    }

    /***************************************************************************
     * 根据时间随即返回STRING
     * 
     * @return
     */
    public static String getrandomname() {
        StringBuffer buffer = new StringBuffer("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
        StringBuffer sb = new StringBuffer();
        Random r = new Random();
        int range = buffer.length();
        for (int i = 0; i < 10; i++) {
            sb.append(buffer.charAt(r.nextInt(range)));
        }

        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddhhmmss");
        String strCreateTime = fmt.format(new Date().getTime()); // 文件创建日期
        return strCreateTime + sb.toString();
    }

    /**
     * 将秒转换为天时分秒格式字符串(如是0天0时0分就显示秒)
     * 
     * @param seconds
     *            秒数
     * @return 返回天时分秒字符串
     */
    public static String getStrOfSeconds(final long seconds) {
        if (seconds < 0) {
            return "秒数必须大于0";
        }
        long one_day = 60 * 60 * 24;
        long one_hour = 60 * 60;
        long one_minute = 60;
        long day, hour, minute, second = 0L;
        ;

        day = seconds / one_day;
        hour = seconds % one_day / one_hour;
        minute = seconds % one_day % one_hour / one_minute;
        second = seconds % one_day % one_hour % one_minute;

        if (seconds < one_minute) {
            return seconds + "秒";
        } else if (seconds >= one_minute && seconds < one_hour) {
            return minute + "分" + second + "秒";
        } else if (seconds >= one_hour && seconds < one_day) {
            return hour + "时" + minute + "分" + second + "秒";
        } else {
            return day + "天" + hour + "时" + minute + "分" + second + "秒";
        }
    }

    /**
     * 时间加减指定的数量
     * @param _dateTime
     * @param _field
     * @param _amount
     * @return
     */
    public static Date dateTimeAdd(Date _dateTime, int _field, int _amount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(_dateTime);
        cal.add(_field, _amount);
        return cal.getTime();
    }

    /**
     * 获得指定范围内的Date集合 包含开始和结束时间
     * @param beginDate
     * @param endDate
     * @return
     */
    public static List<Date> getDatesBetweenTwoDate(Date beginDate, Date endDate) {
        List<Date> lDate = new ArrayList<Date>();
        lDate.add(beginDate);// 把开始时间加入集合  
        Calendar cal = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间  
        cal.setTime(beginDate);
        boolean bContinue = true;
        while (bContinue) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量  
            cal.add(Calendar.DAY_OF_MONTH, 1);
            // 测试此日期是否在指定日期之后  
            if (endDate.after(cal.getTime())) {
                lDate.add(cal.getTime());
            } else {
                break;
            }
        }
        lDate.add(endDate);// 把结束时间加入集合  
        return lDate;
    }

    /**
     * 将日期格式化为00hh:00mm:00ss,且可以设置本月第一天
     * @param _dateTime
     * @param flag
     * @return
     */
    public static Date dateFormat(Date _dateTime, int flag) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(_dateTime);
        if (flag == 1) {
            calendar.set(Calendar.DATE, 1);
        }
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date getDateByYMDHMS(String s) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return (Date) sdf.parseObject(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date getDateByYMDHM(String s) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            return (Date) sdf.parseObject(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date getDateByYMD(String s) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return (Date) sdf.parseObject(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 
     * friendly_time:日期友好显示. <br/> 
     * 
     * @author Administrator 
     * @param date 传入的日期字符串
     * @return 友好日期字符串
     * @throws Exception 
     * @since JDK 1.8
     */
    public static String friendlyTime(String date) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time = df.parse(date);
        if (time == null) {
            return "Unknown";
        }
        String ftime = "";
        Calendar cal = Calendar.getInstance();

        //判断是否是同一天  
        String curDate = df.format(cal.getTime());
        String paramDate = df.format(time);
        if (curDate.equals(paramDate)) {
            int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
            if (hour == 0)
                ftime = Math.max((cal.getTimeInMillis() - time.getTime()) / 60000, 1) + "分钟前";
            else
                ftime = hour + "小时前";
            return ftime;
        }

        long lt = time.getTime() / 86400000;
        long ct = cal.getTimeInMillis() / 86400000;
        int days = (int) (ct - lt);
        if (days == 0) {
            int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
            if (hour == 0)
                ftime = Math.max((cal.getTimeInMillis() - time.getTime()) / 60000, 1) + "分钟前";
            else
                ftime = hour + "小时前";
        } else if (days == 1) {
            ftime = "昨天";
        } else if (days == 2) {
            ftime = "前天";
        } else if (days > 2 && days <= 10) {
            ftime = days + "天前";
        } else if (days > 10) {
            ftime = df.format(time);
        }
        return ftime;
    }
}
