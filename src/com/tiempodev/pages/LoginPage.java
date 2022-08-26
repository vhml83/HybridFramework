package com.tiempodev.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tiempodev.utils.BaseClass;
import com.tiempodev.utils.HandledException;
import com.tiempodev.utils.Log;

public class LoginPage extends BaseClass{

	@FindBy(xpath = ".//*[@id='account']/a")
	WebElement btnMyAccount;
	
	@FindBy(id = "log")
	WebElement txtUserName;
	
	@FindBy(name = "pwd")
	WebElement txtPassword;
	
	@FindBy(id = "login")
	WebElement btnLogin;
	
	@FindBy(xpath = ".//*[@id='ajax_loginform']/p[1]")
	WebElement lblError;
	
	public LoginPage(WebDriver driver){
		this.driver = driver;
		
		PageFactory.initElements(driver,  this);
	}
	
	public void clickMyAccount() throws HandledException{
		fnHighlightMe(driver,btnMyAccount);
		info("Clicking on MyAccount button");
		btnMyAccount.click();
	}
	
	public void setUserName(String strUserName) throws HandledException{
		info("Setting text: "+ strUserName);
		txtUserName.sendKeys(strUserName);
	}
	
	public void clearUserNameField() throws HandledException{
		fnHighlightMe(driver,txtUserName);
		info("Cleaning User Name field");
		txtUserName.clear();
	}
	
	public void setPassword(String strPassword) throws HandledException{
		info("Setting text: "+ strPassword);
		txtPassword.sendKeys(strPassword);
	}
	
	public void clearPasswordField() throws HandledException{
		fnHighlightMe(driver,txtPassword);
		Log.info("Cleaning Password field");
		txtPassword.clear();
	}
	
	public void clickLogin() throws HandledException{
		fnHighlightMe(driver,btnLogin);
		info("Click on Login button");
		btnLogin.click();
	}
	
	public String getErrorLoginLabel() throws HandledException{
		fnHighlightMe(driver,lblError);
		info("Getting the text: " + lblError.getText());
		return lblError.getText();
	}
}
