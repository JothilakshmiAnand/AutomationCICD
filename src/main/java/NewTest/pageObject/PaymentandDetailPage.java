package NewTest.pageObject;

import java.util.List;
import java.util.stream.Collector;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class PaymentandDetailPage extends baseClass {
	
	WebDriver driver;
	public PaymentandDetailPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//@FindBy(xpath = "(//input[@class = \"input txt text-validated\"])[1]")
	//WebElement CCN;
	@FindBy(xpath = "(//select[@class='input ddl'])[1]")
	WebElement month;
	@FindBy(xpath = "(//select[@class='input ddl'])[2]")
	WebElement date;
	@FindBy(xpath = "(//input[@class='input txt'])[1]")
	WebElement CVV;
	@FindBy(xpath = "(//input[@class='input txt'])[2]")
	WebElement name;
	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement CountryName;
	By dropdn = By.cssSelector(".ta-item");
	@FindBy(xpath = "(//button[contains(.,'India')])[2]")
	WebElement Countrydetail;
	@FindBy(xpath = "(//button[contains(.,'China')])[1]")
	WebElement CountryChina;
	
	
	
	public void paymentDetails(String CardDetail, String CVVDetail, String nameDetail,String Country)
	{
		//CCN.sendKeys(CardDetail);
		month.click();
		Select dropdnmonth = new Select(month);
		dropdnmonth.selectByVisibleText("10");
		date.click();
		Select dropdndate = new Select(date);
		dropdndate.selectByVisibleText("22");
		CVV.sendKeys(CVVDetail);
		name.sendKeys(nameDetail);
		Actions a = new Actions(driver);
		a.sendKeys(CountryName, Country).build().perform();
		Waitexplicit(dropdn);
				Countrydetail.click();
	}
	
	//driver.findElement(By.cssSelector(".btnn.action__submit.ng-star-inserted")).click();
	
	@FindBy(css = ".btnn.action__submit.ng-star-inserted")
	WebElement placeOrderButton;
	
	public confirmationPage PlaceOrder() {
		placeOrderButton.click();
		confirmationPage ConfirmationPage =  new confirmationPage(driver);
		return ConfirmationPage;
	}

}
