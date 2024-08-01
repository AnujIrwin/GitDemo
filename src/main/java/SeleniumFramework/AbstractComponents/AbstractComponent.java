package SeleniumFramework.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import SeleniumFramework.pageobjects.CheckoutPage;
import SeleniumFramework.pageobjects.OrderPage;

public class AbstractComponent {
	WebDriver driver;
	public AbstractComponent(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css= "button[routerlink*='cart']")
	WebElement cartIcon;
	
	@FindBy(css="button[routerlink*='myorders']")
	WebElement orderIcon;
	
	By cart = By.cssSelector("button[routerlink*='cart']");
	public void waitforpresenceOfElement(By locator)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	public void visibilityofElementLocated(By locator)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	public void elementToBeClickable(By locator)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	public void webelementToBeClickable(WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void visibilityofWebElementLocated(WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	public void clickonElement(WebElement element)
	{
		webelementToBeClickable(element);
		element.click();
	}
	
	public CheckoutPage clickOnCart()
	{
		elementToBeClickable(cart);
		cartIcon.click();
		CheckoutPage cp = new CheckoutPage(driver);
		return cp;
	}
	
	public OrderPage clickonOrder()
	{
		clickonElement(orderIcon);
		OrderPage op = new OrderPage(driver);
		return op;
		
	}
}

