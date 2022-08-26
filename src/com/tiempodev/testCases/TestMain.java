package com.tiempodev.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.tiempodev.utils.BaseClass;
import com.tiempodev.utils.DataproviderClass;
import com.tiempodev.utils.HandledException;

public class TestMain extends BaseClass{
	
	@Test(dataProvider="LoginCredentials", dataProviderClass=DataproviderClass.class, priority=0, description="Test Case using valid credentials")
	public void validCredentials(String strUserName, String strPassword) throws InterruptedException{
		Assert.assertTrue(loginQA.Login(strUserName, strPassword));
		Assert.assertTrue(logout.LogoOutApplication());
	}
	
	@Test(dataProvider="LoginCredentials", dataProviderClass=DataproviderClass.class, priority=1, description="Test Case using invalid credentials")
	public void invalidCredentials(String strUserName, String strPassword, String strExpectedResult) throws InterruptedException, HandledException{
		Assert.assertTrue(loginQA.Login(strUserName, strPassword));
		Assert.assertEquals(loginPage.getErrorLoginLabel(), strExpectedResult);
	}
}