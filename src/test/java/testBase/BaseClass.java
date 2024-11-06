package testBase;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;//Log4j
import org.apache.logging.log4j.Logger;//Log4j
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public static WebDriver driver; // keep static (to stop the driver conflict) because whenever we create an object of the class then it creates mutiple drivers and it will create conflict.
	public Logger logger;//Log4j
	public Properties P;

	@BeforeClass(groups = {"Sanity", "Regression", "Master"})
	@Parameters({"os","browser"})
	public void setUp(String os, String br) throws IOException 
	{
		//loading cofig.properties file
		FileInputStream propfile = new FileInputStream("./src//test//resources//config.properties");
		//FileReader file = new FileReader("Opencart_Hybrid_Framework\\src\\test\\resources\\config.properties");
		P = new Properties();
		P.load(propfile);
				
		//calling logger method here
		logger=LogManager.getLogger(this.getClass());
		
		//WebDriverManager.chromedriver().setup();
		switch (br.toLowerCase()) {
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "edge":
			driver = new EdgeDriver();
			break;
		case "fireFox":
			driver = new FirefoxDriver();
			break;
		default:
			System.out.println("Invalid Browser");
			return;
		}
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(P.getProperty("appURL"));//reading the data from properties file
		driver.manage().window().maximize();
	}
	
	
	@AfterClass(groups = {"Sanity", "Regression", "Master"})
	public void tearDown()
	{
		driver.quit();
	}
	
	
	public String randomeString()
	{
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}

	public String randomeNumber()
	{
		String generatedNumeric = RandomStringUtils.randomNumeric(10);
		return generatedNumeric;
	}
	
	public String randomeAlphaNumeric()
	{
		String generatedString = RandomStringUtils.randomAlphabetic(4);
		String generatedNumeric = RandomStringUtils.randomNumeric(4);
		return (generatedString+generatedNumeric);
	}


	public String captureScreen(String tname) {
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takesScreenshot = (TakesScreenshot)driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String targetFilePath = System.getProperty("user.dir")+"\\screenshots\\"+tname+"_"+timeStamp+".PNG";
		File targetFile = new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
		return targetFilePath;
	}
	
	

}
