package first;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.Forgottenaccount;
import pageObjects.Landingpage;

//Extends keyword inherits the relevant class properties. All the methods can be inherited here.
//The other way is creating the object for the class.
public class Homepage extends Base {
	private static Logger log = LogManager.getLogger(Homepage.class.getName());
	
	@BeforeTest
	public void initialize() throws IOException {
		
		//initializeDriver -> Calling this method from the Base class.
		driver = initializeDriver();
		log.info("Driver is initialized");
		//Getting the URL from the data.properties file.
		driver.get(property.getProperty("URL"));
		log.info("URL is invoked");
	}
	
	@Test(dataProvider = "getCredentials")
	public void basePage(String userName, String password) throws IOException {
		
		//Sending the driver as constructor to the Landing page through as an argument.
		Landingpage lPage = new Landingpage(driver);
		
		//getUserName is the method used in the Landing page class (Page Objects).
		//This is same as driver.findelement way.
		lPage.getUserName().sendKeys(userName);
		lPage.getPassword().sendKeys(password);
		
		//Validating the title of the page through assert method.
		Assert.assertEquals(lPage.getTitle().getText(), "Facebook");
		
		//Navigating to forgotten account page.
		lPage.getForgottenAccount().click();
		
		log.info("Credentials iteration is completed successfully");
		}
	
	@Test(dataProvider = "getPhoneNumber")
	public void forgottenAccountPage(String phoneNumber) {
		
		Forgottenaccount faPage = new Forgottenaccount(driver);
		faPage.findUserName().sendKeys(phoneNumber);
		faPage.clickCancel().click();
		
		log.info("Mobile number is passed successfully");
	}
	
	@AfterTest
	public void driverClose() {
		driver.close();
	}

	//Data Parameterization
	@DataProvider
	public Object[][] getCredentials() {
		//Each row represents one set of data.
		//Row & Column starts with 0th Index - like 1st Cell represent as [0][0].
		//In the presentation it will be like how many rows and columns, this is not based on Index, its size.
		Object[][] inputValue = new Object[3][2];
		
		//0th Row
		inputValue[0][0] = "mailid01@gmail.com";
		inputValue[0][1] = "password01";
		
		//1st Row
		inputValue[1][0] = "mailid02@gmail.com";
		inputValue[1][1] = "password02";
		
		//2nd Row
		inputValue[2][0] = "mailid03@gmail.com";
		inputValue[2][1] = "password03";
		
		//Returning that to the test cases (wherever it is required).
		return inputValue;
	}
	
	@DataProvider
	public Object[][] getPhoneNumber() {
		Object[][] phoneNumber = new Object[1][1];
		phoneNumber[0][0] = "9176264227";
		return phoneNumber;
	}
	
}
