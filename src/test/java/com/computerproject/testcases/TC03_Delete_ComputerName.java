package com.computerproject.testcases;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.computerproject.utilities.ReadConfig;

public class TC03_Delete_ComputerName {
	ReadConfig rc=new ReadConfig();
	public String baseURL=rc.getApplicationURL();
	public static WebDriver driver;
    public static Logger logger;
    
   	@BeforeClass
	public void setUp()
	{
		System.setProperty("webdriver.chrome.driver",rc.getChromePath());
		driver=new ChromeDriver();
		driver.get(baseURL);
		driver.manage().window().maximize();
		logger=Logger.getLogger("computerproject");
		PropertyConfigurator.configure("Log4j.properties");
	}
	
	
	@Test
	@Parameters({"addComputerName"})
	public void DeleteComputerDataBase(String computerName) throws InterruptedException
	{
		logger.info("URL is opened");	
		logger.info("Title of the Page : " + driver.getTitle());
		driver.findElement(By.xpath("//input[@id='searchbox']")).sendKeys(computerName, Keys.ENTER);
		driver.findElement(By.xpath("//a[text()='" + computerName + "']")).click();
		logger.info("Edit computername link clicked");
		driver.findElement(By.xpath("//input[@value='Delete this computer']")).click();
		logger.info("Delete this computer button clicked");
		Thread.sleep(3000);
		logger.info(driver.findElement(By.xpath("//div[@class='alert-message warning']")).getText());
		String ActualsuccessMsg=driver.findElement(By.xpath("//div[@class='alert-message warning']")).getText();
		logger.info("ActualsuccessMsg is " + ActualsuccessMsg);
		String ExpectedSuccessMsg="Done ! Computer "+ computerName +" has been deleted";
		logger.info("ExpectedSuccessMsg is " + ExpectedSuccessMsg);
		Assert.assertEquals(ActualsuccessMsg,ExpectedSuccessMsg);
		logger.info("Success Message" + ExpectedSuccessMsg);
		logger.info("**********************************************************************");
			
	}
	
	@AfterClass
	public void tesrDown()
	{
		driver.quit();
	}
	
}
