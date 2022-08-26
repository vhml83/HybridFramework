package com.tiempodev.utils;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

public class DataproviderClass {
	
	static Object[][] testObjArray;
	static String testCaseWorkBook = System.getProperty("user.dir") + "\\data\\ToolsQAAuthentication.xlsx";
	static String validLoginSheet = "ValidCredentials";
	static String invalidLoginSheet = "InvalidCrdentials";
	
	@DataProvider(name = "LoginCredentials")
	public static Object[][] Authentication(Method TestMethod) throws Exception {
		if(TestMethod.getName().equalsIgnoreCase("validCredentials")){
			testObjArray = ExcelUtils.getTableArray(testCaseWorkBook,validLoginSheet);
		}else if(TestMethod.getName().equalsIgnoreCase("invalidCredentials")){
			testObjArray = ExcelUtils.getTableArray(testCaseWorkBook,invalidLoginSheet);
		}
		return (testObjArray);
	}
}
