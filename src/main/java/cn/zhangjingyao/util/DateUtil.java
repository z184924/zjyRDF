package cn.zhangjingyao.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author
 */
public class DateUtil {

	/**
	* @Title: compareDate
	* @Description: TODO(日期比较，如果s>=e 返回true 否则返回false)
	* @param s
	* @param e
	* @return boolean  
	* @throws
	* @author luguosui
	 */
	public static boolean compareDate(String s, String e) {
		if(fomatDate(s)==null||fomatDate(e)==null){
			return false;
		}
		return fomatDate(s).getTime() >=fomatDate(e).getTime();
	}

	/**
	 * 格式化日期
	 * 
	 * @return
	 */
	public static Date fomatDate(String date) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return fmt.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 校验日期是否合法
	 * 
	 * @return
	 */
	public static boolean isValidDate(String s) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			fmt.parse(s);
			return true;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return false;
		}
	}
	public static int getDiffYear(String startTime, String endTime) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			long aa=0;
			int years=(int) (((fmt.parse(endTime).getTime()-fmt.parse(startTime).getTime())/ (1000 * 60 * 60 * 24))/365);
			return years;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return 0;
		}
	}
	  /**
     * <li>功能描述：时间相减得到天数
     * @param beginDateStr
     * @param endDateStr
     * @return
     * long 
     * @author Administrator
     */
    public static long getDaySub(String beginDateStr, String endDateStr){
        long day=0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = null;
        Date endDate = null;

            try {
				beginDate = format.parse(beginDateStr);
				endDate= format.parse(endDateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
            day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);
            //System.out.println("相隔的天数="+day);

        return day;
    }

    /**
     * 得到n天之后的日期
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
	 * 两个日期相隔的天数
	 * @param beginDateStr
	 * @param endDateStr
	 * @return
	 */
	public static double getDaySubHms(String beginDateStr, String endDateStr){
		double day=0d;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date beginDate = null;
		Date endDate = null;

		try {
			beginDate = format.parse(beginDateStr);
			endDate= format.parse(endDateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		day=(endDate.getTime()-beginDate.getTime())/(24d*60d*60d*1000d);
		//System.out.println("相隔的天数="+day);

		return Math.round(day * 100d)/100d;
	}

	/**
	 * 两个日期相隔的小时数
	 * @param beginDateStr
	 * @param endDateStr
	 * @return
	 */
	public static double getHourSubHms(String beginDateStr, String endDateStr){
		double hour=0d;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date beginDate = null;
		Date endDate = null;
		try {
			beginDate = format.parse(beginDateStr);
			endDate= format.parse(endDateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		hour=(endDate.getTime()-beginDate.getTime())/(60d*60d*1000d);
		return (double) Math.round(hour*100)/100;
	}
    
	public static boolean isBetween(String date, String startDate, String endDate) {
		Date startTime = fomatDate(startDate);
		Date endTime = fomatDate(endDate);
		Date dateTime = fomatDate(date);
		if(startTime==null || dateTime==null ||  endTime==null){
			return false;
		}
		return startTime.getTime() <= dateTime.getTime() && dateTime.getTime()<= endTime.getTime();
	}

	public static void main(String[] args) {
    	System.out.println(getDaySubHms("2016-11-15 11:00:00", "2016-11-15 23:00:00"));
    }

}
