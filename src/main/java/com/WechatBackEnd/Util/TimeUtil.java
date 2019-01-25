package com.WechatBackEnd.Util;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
	private static long TIME1 = 1000 * 60 * 60 * 24;

	public static String getCurrentTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		return df.format(new Date());
	}

	/**
	 * 用于比较datetime1是否在datetime2之前，若是则返回true
	 * 
	 * @param datetime1
	 * @param datetime2
	 * @return
	 */
	public static boolean isEarlierThan(String datetime1, String datetime2) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date1 = df.parse(datetime1);
			Date date2 = df.parse(datetime2);
			if (date1.compareTo(date2) < 0) {
				return true;
			} else {
				return false;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public static boolean isLessThanMinTime(String datetime1, String datetime2) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date1 = df.parse(datetime1);
			Date date2 = df.parse(datetime2);
			long s1 = date1.getTime();
			long s2 = date2.getTime();
			if (s2 - s1 < TIME1) {
				return false;
			} else {
				return true;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
