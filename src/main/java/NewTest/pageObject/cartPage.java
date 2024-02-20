package NewTest.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class cartPage {
	WebDriver driver;
	
	public cartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".cartSection h3")
	List<WebElement> cartProduct;
	
	//Boolean match = cartProduct.stream().anyMatch(cartProducts->cartProducts.getText().equalsIgnoreCase("ZARA COAT 3"));
	//Assert.assertTrue(match);//
	
	public Boolean cartMatch(String productName) 
	{
		Boolean match = cartProduct.stream().anyMatch(cartProducts->cartProducts.getText().equalsIgnoreCase(productName));
		return match;
	}
	//driver.findElement(By.cssSelector(".totalRow button")).click();
	
	@FindBy(css = ".totalRow button" )
	WebElement button;
	public PaymentandDetailPage clickButton()
	{
		button.click();
		PaymentandDetailPage PaymentDetailPage = new PaymentandDetailPage(driver);
		return PaymentDetailPage;
	}
	
}
