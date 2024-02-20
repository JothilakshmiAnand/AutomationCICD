package NewTest;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import NewTest.TestComponent.BaseTest;
import NewTest.pageObject.ProductCatalogue;
import NewTest.pageObject.cartPage;

public class ErrorValidation extends BaseTest{

	@Test(groups = {"ErrorHandling"})
		public void LoginValidation() throws IOException
		{
		landingPage.loginpage("jothi.lakshmi@gmail.com", "Athvika@12");
		String errorMess = landingPage.getErrorMsg();
		System.out.println(errorMess);
		Assert.assertEquals("Incorrect email or password.", errorMess);

	}
	
	@Test
	public void ProductValidation() throws InterruptedException
	{
		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginpage("jothi.lakshmi@gmail.com", "Athvika@123");
		productCatalogue.addToCart(productName);
		cartPage CartPage = productCatalogue.clickCart();
		Boolean match = CartPage.cartMatch("ZARA COAT 33");
		Assert.assertFalse(match);
		
	}
}
