package SeleniumFramework.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import SeleniumFramework.AbstractComponents.AbstractComponent;

public class FinalPage extends AbstractComponent {
	WebDriver driver;
	public FinalPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver =driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".hero-primary")
	WebElement textdisplayed;
	
	By finalpage = By.cssSelector(".hero-primary");
	
	public String successresulttext()
	{
		visibilityofElementLocated(finalpage);
		return textdisplayed.getText();
		
	}
	
}
