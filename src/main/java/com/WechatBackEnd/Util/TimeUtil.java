package com.WechatBackEnd.Util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
	public static String getCurrentTime() {
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
       return df.format(new Date());
	}
}
