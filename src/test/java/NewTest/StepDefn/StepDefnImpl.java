package NewTest.StepDefn;

import java.io.IOException;

import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import NewTest.TestComponent.BaseTest;
import NewTest.pageObject.LandingPage;
import NewTest.pageObject.PaymentandDetailPage;
import NewTest.pageObject.ProductCatalogue;
import NewTest.pageObject.cartPage;
import NewTest.pageObject.confirmationPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefnImpl extends BaseTest {
	
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public cartPage CartPage;
	public PaymentandDetailPage PaymentDetailPage;
	public confirmationPage ConfirmationPage;
	
	public ExtentTest test;
	public static ExtentReports extent;

	//use this extent report in Base class
    @Before
    public void beforeScenario(Scenario scenario) {
    	String path = "C:\\Users\\Dell\\eclipse-workspace2\\SeleniumFrameworkDesign\\reports\\Cucumberindex.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		// set the report name in the html
		reporter.config().setReportName("Web Automation Results");
		// set the title of the html
		reporter.config().setDocumentTitle("Sprint1 Test Result");
		
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		//set ur name as Tester
		extent.setSystemInfo("Tester", "Jothi");
        test = extent.createTest(scenario.getName());
    }
	
	@Given("I landed on ecommerce Page")
	public void I_landed_on_ecommerce_Page() throws IOException
	{
		landingPage = launchapp();
	}

	@Given("^Login with username (.+) and password (.+)$") // using the ^,$ and Regx (.+) to pass the parameter from feature file
	public void logged_with_userName_password(String UserName, String passWord)
	{
		productCatalogue = landingPage.loginpage(UserName,passWord);
		 test.log(Status.INFO, "Given step executed");
	}
	
	@When("^add product (.+) to cart$")
	public void add_product_to_cart(String productName)
	{
		productCatalogue.addToCart(productName);

	}
	//    And Check product <productName> in cart

	@And("^Check product (.+) in cart$")
	public void check_product_in_cart(String productName) throws InterruptedException {
		CartPage = productCatalogue.clickCart();
		Boolean match = CartPage.cartMatch(productName);
		Assert.assertTrue(match);
	    
	}
	//    And fill details in Payment detail page with CardNo <CardNo>,CVV <CVV>,Name <Name>,CountryName <CountryName>
	
	@When("^fill details in Payment detail page with CardNo (.*),CVV (.*),Name(.*),CountryName (.*)$")
	public void fill_details_in_payment_detail_page_with_CardNo_CVV_Name_CountryName(String CardNo, String CVV, String Name, String CountryName) {
	    PaymentDetailPage = CartPage.clickButton();
	    PaymentDetailPage.paymentDetails(CardNo, CVV, Name, CountryName);
	    	}

	@Then("Confirmation Message is displayed in Confirmation page")
	public void Confirmation_Message_is_displayed_in_confirmation_page()
	{
		ConfirmationPage = PaymentDetailPage.PlaceOrder();
		String ConfMsg = ConfirmationPage.ValidatingConfirmMsg();
		Assert.assertTrue(ConfMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	
	// Then "Incorrect email password." is displayed
	@Then("Error message is displayed")
	public void Error_message_is_displayed() throws InterruptedException 
	{
		String errorMess = landingPage.getErrorMsg();
		System.out.println(errorMess);
		Assert.assertEquals("Incorrect email or password.", errorMess);
		 test.log(Status.INFO, "Given step executed");

	}
	
	 @After
	    public void tearDown() {
	        extent.flush();
	    }
}
