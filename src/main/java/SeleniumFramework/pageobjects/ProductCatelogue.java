package SeleniumFramework.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFramework.AbstractComponents.AbstractComponent;

public class ProductCatelogue extends AbstractComponent{
	
	WebDriver driver;
	public ProductCatelogue(WebDriver driver) {
		// TODO Auto-generated constructor stub
		//driver here does not know about what type of browser is this hence we use constructor to initialize the driver	
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	//WebElement userEmail = driver.findElement(By.id("userEmail"));   this is using normal way
	
	//Lets try using Page Factory method
	
	@FindBy(xpath = "//div[contains(@class,'col-lg-4')]")
	List<WebElement> allproducts1;
	
	@FindBy(css= "button[routerlink*='cart']")
	WebElement cartIcon;
	
	@FindBy(css="button[routerlink*='myorders']")
	WebElement orderIcon;
	
	By productList = By.cssSelector("div[class='left mt-1']");
	By addToCart = By.cssSelector(".card-body .btn:last-of-type");
	By toast = By.id("toast-container");
	By cart = By.cssSelector("button[routerlink*='cart']");
	
	public List<WebElement> getProductList()
	{
		waitforpresenceOfElement(productList);
		elementToBeClickable(cart);
		return allproducts1;
	}

	public WebElement getProductByName(String productName )
	{
		WebElement selectedElement = getProductList().stream()
				.filter(s -> s.findElement(By.tagName("b")).getText().equals(productName)).findFirst().orElse(null);
		return selectedElement;
	}
	
	public void addProductToCart(String productname)
	{
		waitforpresenceOfElement(productList);
		getProductByName(productname).findElement(addToCart).click();
		visibilityofElementLocated(toast);
		elementToBeClickable(cart);
	}
	

}
