package com.computerproject.testcases;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.computerproject.utilities.ReadConfig;

public class TC01_Add_ComputerName {
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
	@Parameters({"addComputerName","addCompanyName"})
	public void AddComputerDataBase(String computerName, String companyName) throws InterruptedException
	{
		logger.info("URL is opened");	
		logger.info("Title of the Page : " + driver.getTitle());
		driver.findElement(By.xpath("//a[text()='Add a new computer']")).click();
		logger.info("Add a new computer button clicked");
		driver.findElement(By.xpath("//input[@id='name']")).sendKeys(computerName);
		Thread.sleep(3000);
		logger.info("Computer name field value entered in the TextBox");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String introducedDate= dateFormat.format(date);
		driver.findElement(By.xpath("//input[@id='introduced']")).sendKeys(introducedDate);
		Thread.sleep(3000);
		logger.info("Introduced field value entered in the TextBox");
		Select company = new Select(driver.findElement(By.id("company")));
		company.selectByVisibleText(companyName);
		Thread.sleep(3000);
		logger.info("Company name field value choose in the Dropdown list");
		driver.findElement(By.xpath("//input[@value='Create this computer']")).click();
		logger.info("Created this computer button clicked");
		Thread.sleep(3000);
		logger.info(driver.findElement(By.xpath("//div[@class='alert-message warning']")).getText());
		String ActualsuccessMsg=driver.findElement(By.xpath("//div[@class='alert-message warning']")).getText();
		logger.info("ActualsuccessMsg is " + ActualsuccessMsg);
		String ExpectedSuccessMsg="Done ! Computer "+ computerName +" has been created";
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
