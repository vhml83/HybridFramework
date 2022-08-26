package com.tiempodev.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tiempodev.utils.BaseClass;
import com.tiempodev.utils.HandledException;

public class YourAccountPage extends BaseClass{

	@FindBy(xpath = ".//*[@id='wp-admin-bar-my-account']/a")
	WebElement lblUserName;

	@FindBy(xpath = ".//*[@id='account_logout']/a")
	WebElement btnLogOut;

	public YourAccountPage(WebDriver driver){
		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	public String getUserNameText() throws HandledException{
		fnHighlightMe(driver,lblUserName);
		info("Getting the text: " + lblUserName.getText());
		return lblUserName.getText();
	}

	public void clickLogoutButton() throws HandledException{
		fnHighlightMe(driver,btnLogOut);
		info("Clicking on LogOut button");
		btnLogOut.click();
	}
}
