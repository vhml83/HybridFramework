package com.tiempodev.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.tiempodev.modules.LogOutToolsQA;
import com.tiempodev.modules.LoginToolsQA;
import com.tiempodev.pages.LoginPage;

public class BaseClass extends Log{

	String chromePath = System.getProperty("user.dir")+"\\drivers\\chromedriver.exe";
	public static String SSDate;
	public static String SSDateTime;
	public static String file;
	public WebDriver driver;
	public LoginToolsQA loginQA;
	public LogOutToolsQA logout;
	public LoginPage loginPage;
	String baseUrl = "http://www.store.demoqa.com";

	public WebDriver getDriver(){
		if(driver==null){
			System.setProperty("webdriver.chrome.driver", chromePath);
			driver = new ChromeDriver();
		}
		return driver;
	}

	public static String takeSnapShot(WebDriver webdriver, String snapshotError) throws HandledException{
		SSDate = new SimpleDateFormat("yyyyMMdd_HH").format(Calendar.getInstance().getTime()).toString();
		SSDateTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()).toString();
		file = System.getProperty("user.dir") + "\\src\\com\\tiempodev\\results\\SSDate\\"+snapshotError+SSDateTime+".png";
		//Convert web driver object to TakeScreenshot
		TakesScreenshot scrShot =((TakesScreenshot)webdriver);
		try{ //Call getScreenshotAs method to create image file
			File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
			//Move image file to new destination
			File DestFile=new File(file);
			//Copy file at destination
			FileUtils.copyFile(SrcFile, DestFile);
		} catch (Exception e) {
			throw new HandledException("Class BaseClass | Method takeSnapShot | Exception desc: " + e.getMessage());
		}
		return file;
	}

	public static void fnHighlightMe(WebDriver driver,WebElement element) throws HandledException{
		//Creating JavaScriptExecuter Interface
		JavascriptExecutor js = (JavascriptExecutor)driver;
		for (int iCnt = 0; iCnt < 3; iCnt++) {
			//Execute javascript
			try {
				js.executeScript("arguments[0].setAttribute('style','background: yellow')", element);
				Thread.sleep(300);
				js.executeScript("arguments[0].setAttribute('style','background:')", element);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new HandledException("Class BaseClass | Method fnHighlightMe | Exception desc: Interrupted Exception", e);
			}
		}
	}
	
	@BeforeClass
	public void initializeComponent(){
		DOMConfigurator.configure("log4j.xml");
	}
	
	@BeforeTest
	public void setUp(){
		driver = getDriver();
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		loginQA = new LoginToolsQA(driver);
		logout = new LogOutToolsQA(driver);
		loginPage = new LoginPage(driver);
	}
	
	@AfterTest
	public void tearDown(){
		driver.quit();
		endTestCase();
	}
	
	@BeforeMethod
	public void logSetUp(){
		startTestCase("Tools QA Login");
		info("Opening Chrome browser");
		info("Navigating to: "+ baseUrl);
	}
}
