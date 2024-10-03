package utilities;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.basePage;

public class Utilclass extends basePage {
	
	public Utilclass(WebDriver driver)
	{
		super(driver);
	}
	
	
	public void waitForElementToBeVisible(WebDriver driver,WebElement element,int time)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(time));
		 wait.until(ExpectedConditions.visibilityOf(element));
		 
	}

	public void DoubleClick(WebDriver driver,WebElement element)
	{
	Actions actions = new Actions(driver);	
	actions.doubleClick(element);
	}
}
