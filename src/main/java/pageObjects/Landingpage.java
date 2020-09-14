 package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Landingpage {

	public WebDriver driver;
	By userName = By.id("email");
	By password = By.id("pass");
	By title = By.xpath("//a[@title = 'Go to Facebook home']");
	By forgottenAccount = By.cssSelector("a[href*='initiate']");
	
	
	public Landingpage(WebDriver driver2) {
		// TODO Auto-generated constructor stub
		//this.driver is a variable created inside this class and the argument is passed form Home page through driver2.
		this.driver=driver2;
	}

	public WebElement getUserName() {
		return driver.findElement(userName);
	}
	
	public WebElement getPassword() {
		return driver.findElement(password);
	}
	
	public WebElement getTitle() {
		return driver.findElement(title);
	}
	
	public WebElement getForgottenAccount() {
		return driver.findElement(forgottenAccount);
	}
}
