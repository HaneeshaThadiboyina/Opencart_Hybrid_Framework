package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.homePage;
import pageObjects.loginPage;
import pageObjects.myAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_Login_DataDrivenTest extends BaseClass {

	@Test(dataProvider = "LoginDataFromRow", dataProviderClass = DataProviders.class) // getting data provider data from
																				// different class
	public void Verify_LoginDDT(String email, String pwd, String exp) throws Throwable {

		logger.info("**********Validation has been started for TC003_Login_DataDrivenTest**************");
		try {
			// HOMEPAGE
			homePage hp = new homePage(driver);
			logger.info("Clicking on the My Account drop down");
			hp.clickMyAccount();
			logger.info("Clicking on the login option");
			hp.clickLogin();

			// LOGINPAGE
			loginPage lp = new loginPage(driver);
			lp.setMail(email);
			lp.setPassword(pwd);
			lp.clkLogin();

			// MYACCOUNTPAGE
			myAccountPage macc = new myAccountPage(driver);
			boolean targetPage = macc.isMyAccountPageExists();

			// valid-true-passed-logout
			// valid-false-failed
			if (exp.equalsIgnoreCase("Valid"))
			{
				if(targetPage==true)
				{
					macc.ClickLogOut();
					Assert.assertTrue(true);
				}
				else 
				{
				Assert.assertTrue(false);
				}
			}

			// invalid-false-passed
			// invalid-true-failed-logout
			if (exp.equalsIgnoreCase("Invalid")) 
			{
				if(targetPage==true)
				{
					macc.ClickLogOut();
					Assert.assertTrue(false);
				}
				else 
				{
				Assert.assertTrue(true);
				}
		}
		}catch (Exception e)
		{
			Assert.fail();
		}

		logger.info("**********Finished TC003_Login_DataDrivenTest**************");
	}
}
