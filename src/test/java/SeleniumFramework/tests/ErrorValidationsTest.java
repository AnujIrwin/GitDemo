package SeleniumFramework.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import SeleniumFramework.TestComponents.BaseTest;
import SeleniumFramework.pageobjects.CheckoutPage;
import SeleniumFramework.pageobjects.FinalPage;
import SeleniumFramework.pageobjects.PaymentPage;
import SeleniumFramework.pageobjects.ProductCatelogue;

public class ErrorValidationsTest extends BaseTest {
	String username = "rockstarirwin@gmail.com";
	String password = "Abc@1234";
	String elementToAdd = "ADIDAS ORIGINAL";
	@Test(groups= {"ErrorHandling"})
	public void loginErrorValidation() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String username1 = "wrongEmail@gmail.com";
		String password1 = "WrongPassword@1234";
		lp.logingIn(username1, password1);
		String value = lp.getAlertValue();
		Assert.assertEquals(value, "Incorrect email or password.");  //This will fail to pass remove 57
	}
	
	@Test
	public void verifyProductErrorValidation() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		ProductCatelogue pc = lp.logingIn(username, password);
		pc.addProductToCart(elementToAdd);
		Thread.sleep(2000);	
		CheckoutPage cp = pc.clickOnCart();
		Thread.sleep(2000);
		Boolean match = cp.verifyProductmatch(elementToAdd+"extraName");
		Assert.assertFalse(match);
	}
}
