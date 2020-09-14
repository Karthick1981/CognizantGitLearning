package first;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Base {
	
	//Initializing the WebDriver & Properties at the global object inside the base case.
	public WebDriver driver;
	
	//Data Driven Framework - Global Parameters - Data Driven Parameterization using Property File.
	Properties property = new Properties();

	
	public WebDriver initializeDriver() throws IOException {
		
		//To access the global data file.
		//System.getProperty("user.dir") -> user.dir: always gives the current project path (irrespective of the system).
		FileInputStream inputFile = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\Data.properties");
				
		//Property file is responsible to pull the value from the data file and for that we need to map it using load.
		property.load(inputFile);
		
		//Parameterizing browser using Maven command
		//mvn test -Dbrowser=chrome
		String browserName = System.getProperty("browser");
		
		//Fetching the value of a data (browser) and storing it in the string.
		//String browserName = property.getProperty("browser");
		
		if(browserName.contains("chrome"))
		{
			System.setProperty("webdriver.chromer.driver", System.getProperty("user.dir")+"\\src\\main\\java\\resources\\chromedriver.exe");
			
			//ChromeOptions is a class which will help you to give many options and it can be injected inside the chrome driver and the headless can be triggered.
			ChromeOptions options = new ChromeOptions();
						
			if(browserName.contains("headless")) 
			{
				options.addArguments("headless");	
			}
			driver = new ChromeDriver(options);
			driver.manage().window().maximize();
		}
		else if(browserName.equals("IE")) 
		{
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\src\\main\\java\\resources\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			driver.manage().window().maximize();
		}
		
		//Setting up the implicit wait for the minimum wait time.
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		return driver;
	}

	//This is to pass on the screenshot to listeners if there is any failure.
		public String getScreenshotPath(String testCaseName, WebDriver driver) throws IOException {
			
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			
			//Below steps Selenium takes screenshot but it will be in the virtual place.
			File source = screenshot.getScreenshotAs(OutputType.FILE);
			
			//user.dir will get the current project path.
			//testcasename will get it from the listener.
			String destinationFile = System.getProperty("user.dir")+"\\Reports"+testCaseName+".png";
			
			//FileUtils will copy the screenshot from virtual place i.e., from Source to the destination place.
			//Download apache commons IO dependency from maven repository.
			FileUtils.copyFile(source, new File(destinationFile));
			return destinationFile;
		}
}
