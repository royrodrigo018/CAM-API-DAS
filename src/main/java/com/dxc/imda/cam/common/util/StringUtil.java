package com.dxc.imda.cam.common.util;


public class StringUtil {
	
	/**Function to verify if string value is null or empty/whitespace only
	 * @param string
	 * @return <code>true</code> if string null/empty, otherwise <code>false</code>
	 */
	public static boolean isBlank(String string){
		return (string == null || string.trim().isEmpty());		
	}
	
	public static boolean isNull(Object obj){
		return (obj == null);		
	}
	
	public static boolean isNumeric(String string){		
		try {			
			Integer.parseInt(string);
			return true;			
		} catch (Exception e) {			
			try {
				Integer.parseInt(string);
				return true;
			} catch (Exception ex) {	
				return false;
			}			
		}		
	}
	
	public static String[] splitString(String str, String delimeter) {			
		String[] stringArray = str.split(delimeter);		
		return stringArray;	    
	}
		
	public static String[] filterString(String str, String delimeter) {			
		String[] arrOfStr = str.split(delimeter);		
		return arrOfStr;	    
	}
	
}
