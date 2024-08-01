package SeleniumFramework.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import SeleniumFramework.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="div[class='cartSection'] h3") 
	List<WebElement> checkoutElements;
	
	@FindBy(css="li[class='totalRow'] button")
	WebElement checkoutButton;
	
	
	By checkoutElementcheck = By.cssSelector("div[class='cartSection'] h3");

	public PaymentPage checkoutProducts(String producttoCheck)
	{
		visibilityofElementLocated(checkoutElementcheck);
		Boolean match = checkoutElements.stream().anyMatch(checkout -> checkout.getText().equalsIgnoreCase(producttoCheck));
		Assert.assertTrue(match);
		checkoutButton.click();	
		PaymentPage pp = new PaymentPage(driver);
		return pp;
	}
	
	public Boolean verifyProductmatch(String producttoCheck)
	{
		visibilityofElementLocated(checkoutElementcheck);
		Boolean match = checkoutElements.stream().anyMatch(checkout -> checkout.getText().equalsIgnoreCase(producttoCheck));
		return match;
	}


}
