package com.tiempodev.modules;

import org.openqa.selenium.WebDriver;

import com.tiempodev.pages.YourAccountPage;
import com.tiempodev.utils.BaseClass;
import com.tiempodev.utils.HandledException;

public class LogOutToolsQA extends BaseClass{
	YourAccountPage yourAcc;
	
	public LogOutToolsQA(WebDriver driver){
		this.driver = driver;
	}
	
	public boolean LogoOutApplication(){
		try{
			yourAcc = new YourAccountPage(driver);
			yourAcc.getUserNameText();
			yourAcc.clickLogoutButton();
		}catch(Exception e){
			new HandledException(e.getMessage());
			return false;
		}
		info("Logging out successfully!");
		return true;
	}
}
