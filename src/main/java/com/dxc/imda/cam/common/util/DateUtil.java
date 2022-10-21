package com.dxc.imda.cam.common.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	public String convertDateToUTC(Date date) {
    	return date.toInstant().toString();    	
    }
    
    public String convertTimestampToUTC(Timestamp timestamp) {
    	return timestamp.toInstant().toString();    	
    }
    
    public static Date formatDate(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date newDate = null;
		try {
			newDate = sdf.parse(date+"");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return newDate;    	
    }	
}
