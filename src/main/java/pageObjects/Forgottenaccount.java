package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Forgottenaccount {

	public WebDriver driver;
	By findAccount = By.id("identify_email");
	By cancel = By.xpath("//span[@class='uiButtonText']");
	By search = By.cssSelector("input[type='submit']");
	
	public Forgottenaccount(WebDriver driver2) {
		// TODO Auto-generated constructor stub
		//this.driver is a variable created inside this class and the argument is passed form Home page through driver2.
		this.driver=driver2;
	}

	public WebElement findUserName() {
		return driver.findElement(findAccount);
	}
	
	public WebElement clickCancel() {
		return driver.findElement(cancel);
	}
	
	public WebElement clickSearch() {
		return driver.findElement(search);
	}
}
