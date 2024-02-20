package NewTest.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductCatalogue extends baseClass {
	
	 WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		// TODO Auto-generated constructor stub
		//initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//List <WebElement> product = driver.findElements(By.cssSelector(".col-md-6"));
	
	@FindBy(css = ".col-md-6")
	List<WebElement> product;
	
	By productsBy = By.cssSelector(".col-md-6");
	
	By addCart = By.xpath("//div/button[contains(.,'Add To Cart')]");
	
	By Toast = By.id("toast-container");
	
	By disAppear = By.cssSelector("html > head:nth-child(1) > style:nth-child(12)");
	
	public List<WebElement> getProductLists()
	{
		Waitexplicit(productsBy);
		return product;
	}
	//WebElement prod = product.stream().filter(s->s.findElement(By.xpath("//div/h5/b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);
	
	public WebElement getProductName(String productName)
	{
		WebElement prod = product.stream().filter(s->s.findElement(By.xpath("//div/h5/b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	//prod.findElement(By.xpath("//div/button[contains(.,'Add To Cart')]")).click();
	
	public void addToCart(String productName)
	{
		WebElement prod = getProductName(productName);
		prod.findElement(addCart).click();
		Waitexplicit(Toast);
		WaitToDisappear(disAppear);
	}
	
	@FindBy(css = "[routerlink*='cart']" )
	WebElement cart;
	
	//driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
	
	public cartPage clickCart() throws InterruptedException
	{
		Waitexplicit(Toast);
		Thread.sleep(5000);
		cart.click();
		cartPage CartPage = new cartPage(driver);
		return CartPage;
	}
}
