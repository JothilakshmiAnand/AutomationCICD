package NewTest.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends baseClass {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		// initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id = "userPassword")
	WebElement password;

	@FindBy(id = "login")
	WebElement lgnbutton;
	
	////div[@aria-label='Incorrect email or password.']
	//.ng-tns-c4-3.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error
	
	//div[aria-label='Incorrect email or password.']
	//div[@class='ng-tns-c4-15 ng-star-inserted ng-trigger ng-trigger-flyInOut ngx-toastr toast-error']
			
	
	@FindBy(css = ".ng-trigger-flyInOut ")
	WebElement errorMesg;

	// action method for URL and window maximize

	// action method for login

	public ProductCatalogue loginpage(String email, String pwd) {
		// TODO Auto-generated method stub
		userEmail.sendKeys(email);
		password.sendKeys(pwd);
		lgnbutton.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
	}
	
	public String getErrorMsg()
	{
		WaitexplicitWebEle(errorMesg);
		return errorMesg.getText();
	}

	public void goTo() {
		// TODO Auto-generated method stub
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
	}

}
