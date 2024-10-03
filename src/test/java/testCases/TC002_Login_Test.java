package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.homePage;
import pageObjects.loginPage;
import pageObjects.myAccountPage;
import testBase.BaseClass;

public class TC002_Login_Test extends BaseClass{
	
	@Test(groups = {"Regression", "Master"})
	public void Verify_Login() throws Throwable {
		
		logger.info("**********Validation has been started for TC002_Login_Test**************");
		try
		{
		//HOMEPAGE
		homePage hp = new homePage(driver);
		logger.info("Clicking on the My Account drop down");
		hp.clickMyAccount();
		logger.info("Clicking on the login option");
		hp.clickLogin();
		//LOGINPAGE
		loginPage lp = new loginPage(driver);
		lp.setMail(P.getProperty("email"));
		lp.setPassword(P.getProperty("password"));
		lp.clkLogin();
		//MYACCOUNTPAGE
		myAccountPage macc = new myAccountPage(driver);
		boolean targetPage = macc.isMyAccountPageExists();
		Assert.assertEquals(targetPage, true, "Login Failed");
		}
		catch (Exception e)
		{
			Assert.fail();
		}
		logger.info("**********Finished TC002_Login_Test**************");
		
		
		
	}

}
