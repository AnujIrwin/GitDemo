package SeleniumFramework.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import SeleniumFramework.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

		public WebDriver driver;   //These public variables can be accessed from the child classes 
		public LandingPage lp;
	public WebDriver intitializeDriver() throws IOException {
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//SeleniumFramework//resources//GlobalData.properties");
		prop.load(fis);
		String browserName = prop.getProperty("Browser");
		if (browserName.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if (browserName.equalsIgnoreCase("Firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else if (browserName.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		driver.manage().window().maximize();
		return driver;
	}
	
	public List<HashMap<String,String>> getJsonDataToMap(String filePath) throws IOException
	{
		String jsoncontent = FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data =mapper.readValue(jsoncontent, new TypeReference<List<HashMap<String,String>>>(){			
		});
		return data;
	}
	
	public String getScreenshot(String TestCaseName) throws IOException
	{
		TakesScreenshot ts =(TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File distination = new File(System.getProperty("user.dir"+"//Reports//"+TestCaseName+".png"));
		FileUtils.copyFile(source, distination);
		return System.getProperty("user.dir")+"//Reports//"+TestCaseName+".png";
	}
	
	@BeforeMethod
	public LandingPage launchApplication() throws IOException
	{
		driver = intitializeDriver();
		lp = new LandingPage(driver);
		lp.goTo();
		return lp;
	}
	
	@AfterMethod
	public void teardown()
	{
		driver.quit();
	}
}
