package com.tfs.irp.util;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



public class LogTimeUtil {
	
	
	/**
	 * 通过当前的时间获得本周开始的时间
	 * @return
	 */
	public Date getWeek(){
		  Calendar c = Calendar.getInstance();
		  int weekday = c.get(7)-2;
		  c.add(5,-weekday);
		 Date date=c.getTime();
		 
		 date.setHours(0);
		 date.setMinutes(0);
		 date.setSeconds(0);
		 return date;
	}
	/**
	 * 通过当前的时间获得上周开始的时间
	 * @return
	 */
	public static Date getLastFirstWeek(){
		  Calendar c = Calendar.getInstance();
		  int weekday = c.get(7)-2;
		  c.add(5,-weekday);
		 
		 Date date=c.getTime();
		 date.setDate(date.getDate()-7);
		 date.setHours(0);
		 date.setMinutes(0);
		 date.setSeconds(0);
		 return date;
	}
	
	
	/**
	 * 获得本周结束时间
	 * @return
	 */
	public Date getLastWeek(){
		Calendar c = Calendar.getInstance();
		  int weekday = c.get(7)-2;
		  c.add(5,-weekday);
		  c.add(5,6);
		  Date date=c.getTime();
		  
			 date.setHours(23);
			 date.setMinutes(59);
			 date.setSeconds(59);
			 return date;
	}
	/**
	 * 通过当前时间获得上周结束时间
	 * @return
	 */
	public static Date getLastEndWeek(){
		Calendar c = Calendar.getInstance();
		  int weekday = c.get(7)-2;
		  c.add(5,-weekday);
		  c.add(5,6);
		  Date date=c.getTime();
		  date.setDate(date.getDate()-7);
			 date.setHours(23);
			 date.setMinutes(59);
			 date.setSeconds(59);
			 return date;
	}
	
	
	/**
	 * 通过当前时间获得本月开始时间
	 * @return
	 */
	public Date getMonth(){
		Date date=new Date();
		 date.setDate(1);
		 date.setHours(0);
		 date.setMinutes(0);
		 date.setSeconds(0);
		return date;
	}
	/**
	 * 获得本月结束时间
	 * @return
	 */
	public Date getLastMonth(){
		Calendar c = Calendar.getInstance();
       Date date=c.getTime();
       int months = date.getMonth()+1;
       if(months>=13){
    	   months = 1;
       }
     date.setDate(getMonthDatas(months));
  	 date.setHours(23);
	 date.setMinutes(59);
	 date.setSeconds(59);
	 return date;
	}
	/**
	 * 通过当前时间获得本季度的开始时间
	 * @return
	 */
	public Date getQuarter(){
		   Calendar c = Calendar.getInstance();
	        Date date=c.getTime();
	        int months = date.getMonth()+1;
	        if(months>=13){
	        	months = 1;
	        }
	        if(months>=1 && months<=3){
	        	date.setMonth(0);
	        }else if(months>=4 && months<=6){
	        	date.setMonth(3);
	        }else if(months>=7 && months<=9){
	        	date.setMonth(6);
	        }else{
	        	date.setMonth(9);
	        }
	        date.setDate(1);
	        date.setHours(0);
	        date.setMinutes(0);
	        date.setSeconds(0);
			 return date;
	}
	/**
	 * 获得本季度最后时间
	 * @return
	 */
	public Date getLastQuarter(){
		Calendar c = Calendar.getInstance();
        Date date=c.getTime();
        int months = date.getMonth()+1;
        if(months>=13){
        	months = 1;
        }
        if(months>=1 && months<=3){
        	date.setMonth(2);
        }else if(months>=4 && months<=6){
        	date.setMonth(5);
        }else if(months>=7 && months<=9){
        	date.setMonth(8);
        }else{
        	date.setMonth(11);
        }
        date.setDate(getMonthDatas(months));
        date.setHours(23);
        date.setMinutes(59);
        date.setSeconds(59);
		 return date;
	}
	/**
	 * 转换年份
	 * @param _year
	 * @return
	 */
	public int getYearOfYear(String _year){
		String _yearone=_year.substring(1);
		char[] _yeartwo=_yearone.toCharArray();
		int _yearnumone=Integer.parseInt(_yeartwo[0]+"")+1;
		String _yearthree=_yearnumone+""+_yeartwo[1]+_yeartwo[2];
		return Integer.parseInt(_yearthree);
		
	}
	
	/**
	 * 转化月份
	 * @param args
	 */
	private static int getQuarterInMonth(int month, boolean isQuarterStart) {  
        int months[] = { 1, 4, 7, 10 };  
        if (!isQuarterStart) {  
            months = new int[] { 3, 6, 9, 12 };  
        }  
        if (month >= 2 && month <= 4)  
            return months[0];  
        else if (month >= 5 && month <= 7)  
            return months[1];  
        else if (month >= 8 && month <= 10)  
            return months[2];  
        else  
            return months[3];  
    }  
	/**
	* 得到指定月的天数
	* @return
	*/
	public  int getMonthDatas(int month)
	{
	Calendar a = Calendar.getInstance();
	a.set(Calendar.MONTH, month - 1);
	a.set(Calendar.DATE, 1);//把日期设置为当月第一天
	a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
	int maxDate = a.get(Calendar.DATE);
	return maxDate;
	}
}
