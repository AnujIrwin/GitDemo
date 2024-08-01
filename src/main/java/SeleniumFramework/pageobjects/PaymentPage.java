package SeleniumFramework.pageobjects;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import SeleniumFramework.AbstractComponents.AbstractComponent;

public class PaymentPage extends AbstractComponent{
	WebDriver driver;
	public PaymentPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//select[@class='input ddl']")
	List<WebElement> cardExpireDetail;
	
	@FindBy(css="input[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(css=".ta-item")
	List<WebElement> listautosuggest;
	
	@FindBy(css=".action__submit")
	WebElement submit;
	
	By checkoutPageCheck = By.xpath("//select[@class='input ddl'][1]");
	By autosuggestResult = By.cssSelector(".ta-results");
	
	public FinalPage checkoutPagecardExpiry(String expireMonth, String expireYear,Boolean value)
	{
		elementToBeClickable(checkoutPageCheck);
		Select dropdown = new Select(cardExpireDetail.get(0));
		dropdown.selectByVisibleText(expireMonth);
		Select dropdown1 = new Select(cardExpireDetail.get(1));
		dropdown1.selectByVisibleText(expireYear);
		
		if(value.TRUE)
		{
			Boolean enable = submit.isEnabled();
			Assert.assertTrue(enable);							
			clickonElement(submit);
			FinalPage fp = new FinalPage(driver);
			return fp;
		}
		else
		{
		return null;
		}
	}
	
	public FinalPage fillautosuggestcountry(String countryNameinitials, String exactCountryName,Boolean value )
	{
		country.sendKeys(countryNameinitials);
		elementToBeClickable(autosuggestResult);
		List<WebElement> exactElement = listautosuggest.stream()
		.filter(s -> s.findElement(By.tagName("span")).getText().equalsIgnoreCase(exactCountryName))
		.collect(Collectors.toList());
		exactElement.get(0).click();	
		if (value.TRUE)
		{
			Boolean enable = submit.isEnabled();
			Assert.assertTrue(enable);							
			clickonElement(submit);
			FinalPage fp = new FinalPage(driver);
			return fp;
		}
		else 
		{
			return null;
		}
		
	}
	
}
