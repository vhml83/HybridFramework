package com.tiempodev.utils;

import org.openqa.selenium.WebDriver;

public class HandledException extends Exception{
	
	public static final long serialVersionUID=700L;
	public static String file;
	
	public HandledException() {	}
	
	public HandledException(String message) {
		super(message);
		Log.error(message);
	}
	
	public HandledException(String message, Exception ex) {
		this(message);
		Log.error(ex.getMessage());
	}
	
	public HandledException(WebDriver driver, String snapshotError, String message) throws HandledException {
		this(message);
		file = BaseClass.takeSnapShot(driver, snapshotError);
		Log.error("Screenshot created in: " + file);
	}
	
	public HandledException(WebDriver driver, String snapshotError, String message, Exception ex) throws HandledException {
		this(driver, snapshotError, message);
		Log.error(ex.getMessage());
	}
}
