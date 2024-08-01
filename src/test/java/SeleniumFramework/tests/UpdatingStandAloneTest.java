package SeleniumFramework.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import SeleniumFramework.TestComponents.BaseTest;
import SeleniumFramework.pageobjects.CheckoutPage;
import SeleniumFramework.pageobjects.FinalPage;
import SeleniumFramework.pageobjects.OrderPage;
import SeleniumFramework.pageobjects.PaymentPage;
import SeleniumFramework.pageobjects.ProductCatelogue;

public class UpdatingStandAloneTest extends BaseTest {
	String username = "rockstarirwin@gmail.com";
	String password = "Abc@1234";
	String elementToAdd = "ADIDAS ORIGINAL";
	@Test
	public void submitOrder() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		ProductCatelogue pc = lp.logingIn(username, password);
		pc.addProductToCart(elementToAdd);
		Thread.sleep(2000);	
		CheckoutPage cp = pc.clickOnCart();
		PaymentPage pp = cp.checkoutProducts(elementToAdd);
		pp.checkoutPagecardExpiry("07", "25", false);
		FinalPage fp = pp.fillautosuggestcountry("ind", "India", true);	
		String extractedtext = fp.successresulttext();
		Assert.assertTrue(extractedtext.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	
	//TO verify if ADIDAS ORIGINAL is showing in the order page
	@Test(dependsOnMethods={"submitOrder"} )    //this means that before executing the test case we need to execute the submitorderTestcase
	
	public void verifyProductonOrderpage() throws InterruptedException
	{
		lp.logingIn(username, password);
		FinalPage fp = new FinalPage(driver);
		OrderPage op = fp.clickonOrder();
		Boolean value = op.verifyProductpresentinCart(elementToAdd);
		Assert.assertTrue(value);
	}
	
}
