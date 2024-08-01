package SeleniumFramework.pageobjects;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import SeleniumFramework.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {
	WebDriver driver;
	public OrderPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver =driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="body > app-root:nth-child(1) > app-myorders:nth-child(2) > div:nth-child(2) > table:nth-child(2) > tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(3)")
	WebElement LastOrderObject;
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> productNames;
	
	public Boolean verifyProductpresentinCart(String productName)			
	{
		Boolean match = productNames.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));
		return match;
	}
	

}
