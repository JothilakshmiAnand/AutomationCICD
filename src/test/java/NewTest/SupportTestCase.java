package NewTest;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import NewTest.TestComponent.BaseTest;
import NewTest.pageObject.LandingPage;
import NewTest.pageObject.OrderPage;
import NewTest.pageObject.PaymentandDetailPage;
import NewTest.pageObject.ProductCatalogue;
import NewTest.pageObject.cartPage;
import NewTest.pageObject.confirmationPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SupportTestCase extends BaseTest {

	//String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups= {"Purchase"})
	public void SubmitOrder(HashMap<String,String> input ) throws IOException, InterruptedException {
		ProductCatalogue productCatalogue = landingPage.loginpage(input.get("email"),input.get("password"));
		productCatalogue.addToCart(input.get("productName"));
		cartPage CartPage = productCatalogue.clickCart();
		Boolean match = CartPage.cartMatch(input.get("productName"));
		Assert.assertTrue(match);
		PaymentandDetailPage PaymentDetailPage = CartPage.clickButton();
		PaymentDetailPage.paymentDetails(input.get("CardNo"), input.get("CVV"), input.get("Name"), input.get("CountryName"));
		confirmationPage ConfirmationPage = PaymentDetailPage.PlaceOrder();
		String ConfMsg = ConfirmationPage.ValidatingConfirmMsg();
		Assert.assertTrue(ConfMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}

	@Test(dependsOnMethods = "SubmitOrder", dataProvider = "getData")
	public void ProductValidationOnOrderPage(String productName) {
		ProductCatalogue productCatalogue = landingPage.loginpage("jothi.lakshmi@gmail.com", "Athvika@123");
		OrderPage orderspage = productCatalogue.OrderPage();
		orderspage.OrderMatch(productName);
	}
	
	

	@DataProvider
	public Object[][] getData() throws IOException {
		
		
		
		List<HashMap<String,String>> data =	getJsonData(System.getProperty("user.dir")+"//src//test//java//NewTest//dataReader//data.json");
		return new Object[][] {{data.get(0)}, {data.get(1)}};
		
		/*return new Object[][] {
				{ "jothi.lakshmi@gmail.com", "Athvika@123", "ZARA COAT 3", "1534 9931 9292 2293", "234", "Jothi",
						"India" },
				{ "anu.akarsha@gmail.com", "Anu@123456789", "ZARA COAT 3", "5708 9941 9090 1234", "178", "Anu",
						"India" } };
		
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("email", "jothi.lakshmi@gmail.com");
		map.put("password", "Athvika@123");
		map.put("productName", "ZARA COAT 3");
		map.put("CardNo", "1534 9931 9292 2293");
		map.put("CVV", "234");
		map.put("Name", "Jothi");
		map.put("CountryName", "India");
		
		HashMap<String,String> map1 = new HashMap<String,String>();
		map1.put("email", "anu.akarsha@gmail.com");
		map1.put("password", "Anu@123456789");
		map1.put("productName", "ZARA COAT 3");
		map1.put("CardNo", "5708 9941 9090 1234");
		map1.put("CVV", "178");
		map1.put("Name", "Anu");
		map1.put("CountryName", "India");
		
		return new Object[][] {{map},{map1}};
		*/
	}
}
