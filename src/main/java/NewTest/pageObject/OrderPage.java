package NewTest.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderPage extends baseClass {
	WebDriver driver;
	
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> orderProduct;
	
	//Boolean match = cartProduct.stream().anyMatch(cartProducts->cartProducts.getText().equalsIgnoreCase("ZARA COAT 3"));
	//Assert.assertTrue(match);//
	
	public Boolean OrderMatch(String productName) 
	{
		Boolean match = orderProduct.stream().anyMatch(cartProducts->cartProducts.getText().equalsIgnoreCase(productName));
		return match;
	}
	//driver.findElement(By.cssSelector(".totalRow button")).click();
	
	
}
