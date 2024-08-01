package SeleniumFramework.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFramework.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	WebDriver driver;
	public LandingPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		//driver here does not know about what type of browser is this hence we use constructor to initialize the driver	
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	//WebElement userEmail = driver.findElement(By.id("userEmail"));   this is using normal way
	
	//Lets try using Page Factory method
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(id="login")
	WebElement login;
	
	@FindBy(css="div[aria-label='Incorrect email or password.']")
	WebElement alert;
	
	public ProductCatelogue logingIn(String email, String pass)
	{
		userEmail.sendKeys(email);
		password.sendKeys(pass);
		login.click();
		ProductCatelogue pc = new ProductCatelogue(driver);
		return pc;
	}

	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getAlertValue()
	{
		String alertvalue = alert.getText();
		return alertvalue;
	}
}
