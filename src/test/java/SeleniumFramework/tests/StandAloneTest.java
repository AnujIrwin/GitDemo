package SeleniumFramework.tests;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import SeleniumFramework.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		String username = "rockstarirwin@gmail.com";
		String password = "Abc@1234";
		String elementToAdd = "ADIDAS ORIGINAL";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		LandingPage lp = new LandingPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys(username);
		driver.findElement(By.id("userPassword")).sendKeys(password);
		driver.findElement(By.id("login")).click();
		w.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[class='left mt-1']")));

		List<WebElement> allproducts = driver.findElements(By.xpath("//div[contains(@class,'col-lg-4')]"));
//		for (int i = 0;i<allproducts.size();i++)
//		{
//			WebElement currentElement = allproducts.get(i);
//			String productName = currentElement.findElement(By.tagName("b")).getText();
//			if (productName.equalsIgnoreCase("ADIDAS ORIGINAL"))
//			{
//				currentElement.findElements(By.tagName("button")).get(1).click();
//				break;
//			}
//		}
		// Trying above with streams concept
		WebElement selectedElement = allproducts.stream()
				.filter(s -> s.findElement(By.tagName("b")).getText().equals(elementToAdd)).findFirst().orElse(null);
		selectedElement.findElement(By.cssSelector(".card-body .btn:last-of-type")).click();

		WebElement productaddconfirmation = driver.findElement(By.id("toast-container"));
		w.until(ExpectedConditions.visibilityOf(productaddconfirmation));
		w.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[routerlink*='cart']")));
		Thread.sleep(2500);
		driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='cartSection'] h3")));
		List<WebElement> checkoutelements = driver.findElements(By.cssSelector("div[class='cartSection'] h3"));
		Boolean match = checkoutelements.stream().anyMatch(checkout -> checkout.getText().equals(elementToAdd));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector("li[class='totalRow'] button")).click();

		w.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@class='input ddl'][1]")));

		List<WebElement> dropdowns = driver.findElements(By.xpath("//select[@class='input ddl']"));
		Select dropdown = new Select(dropdowns.get(0));
		dropdown.selectByVisibleText("05");
		Select dropdown1 = new Select(dropdowns.get(1));
		dropdown1.selectByVisibleText("28");

		driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("Ind");
		w.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ta-results")));
		List<WebElement> autosuggest = driver.findElements(By.cssSelector(".ta-item"));
		List<WebElement> newWebelement = autosuggest.stream()
				.filter(s -> s.findElement(By.tagName("span")).getText().equalsIgnoreCase("India"))
				.collect(Collectors.toList());
		newWebelement.get(0).click();

		Boolean b = driver.findElement(By.cssSelector(".action__submit")).isEnabled();
		Assert.assertTrue(b);
		driver.findElement(By.cssSelector(".action__submit")).click();
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hero-primary")));
		String wl = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(wl.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.quit();
		
		///This whole above will now be handled with the Framework
		
		
	}
}
