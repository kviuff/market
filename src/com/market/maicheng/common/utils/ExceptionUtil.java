package com.market.maicheng.common.utils;

import java.io.PrintWriter;
import java.io.StringWriter;


public class ExceptionUtil {
	public static String getExceptionMessage(Exception ex){
		String exceptionMessage = "";
		if (ex != null) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			try {
				ex.printStackTrace(pw);
				exceptionMessage = sw.toString();
			} finally {
				try {
					sw.close();
					pw.close();	
				} catch (Exception e) {
					// ignore
				}
			}
		}
		return exceptionMessage;
	}
}
