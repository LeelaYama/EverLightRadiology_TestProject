package com.computerproject.testcases;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.computerproject.utilities.ReadConfig;

public class TC03_JS_Prompt {
	ReadConfig rc=new ReadConfig();
	public String javascriptURL=rc.getJavascriptApplicationURL();
	public static WebDriver driver;
    public static Logger logger;
    
   	@BeforeClass
	public void setUp()
	{
		System.setProperty("webdriver.chrome.driver",rc.getChromePath());
		driver=new ChromeDriver();
		driver.get(javascriptURL);
		driver.manage().window().maximize();
		logger=Logger.getLogger("javascriptproject");
		PropertyConfigurator.configure("Log4j.properties");
	}
	
	
	@Test
	@Parameters({"computerName","companyName"})
	public void test_JSPrompt() throws InterruptedException
	{
		logger.info("URL is opened");
		logger.info("Title of the Page : " + driver.getTitle());
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		logger.info(driver.switchTo().alert().getText());
		Thread.sleep(3000);
		driver.switchTo().alert().sendKeys("I am a JS prompt");
		Thread.sleep(3000);
		driver.switchTo().alert().accept();
		Thread.sleep(3000);
		String Result =driver.findElement(By.xpath("//p[@id='result']")).getText(); 
		logger.info(Result);
		logger.info("**********************************************************************");				
	}
	
	@AfterClass
	public void tesrDown()
	{
		driver.quit();
	}
	
}
