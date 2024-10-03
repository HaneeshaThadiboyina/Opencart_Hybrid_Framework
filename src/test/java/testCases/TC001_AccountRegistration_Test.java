package testCases;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.homePage;
import pageObjects.regPage;
import testBase.BaseClass;
import utilities.Utilclass;

public class TC001_AccountRegistration_Test extends BaseClass {
	
	@Test(groups = {"Sanity", "Master"})
	public void verify_accountRegistration() throws Throwable
	{
		logger.info("**********Validation has been started for TC001_AccountRegistration_Test**************");
		try
		{
		homePage hp = new homePage(driver);
		logger.info("Clicking on the My Account drop down");
		hp.clickMyAccount();
		logger.info("Clicking on the Register option");
		hp.clickRegister();
		
		regPage rp = new regPage(driver);
		logger.info("Entering the details of the User");
		rp.setFirstName(randomeString().toUpperCase());
		rp.setLastName(randomeString().toUpperCase());
		rp.setEmail(randomeString()+"@gmail.com");
		rp.setMobileNumber(randomeNumber());
		String Password = randomeNumber();
		rp.setPassword(Password);
		rp.setCfmPassword(Password);
		rp.setPrivacyPolicy();
		rp.clickContinue();
		
		logger.info("Validating the expected message");
		String mss = rp.getConfirmation();
		if(mss.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			Assert.assertTrue(false);
			logger.error("Test Failed");
			logger.debug("Debug logs...");
		}
		//Assert.assertEquals(mss, "Your Account Has Been Created!");
		}
		
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("***********Finished TC001_AccountRegistration_Test*************");
		
	}
	
	
}
