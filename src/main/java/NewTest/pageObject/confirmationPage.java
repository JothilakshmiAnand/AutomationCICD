package NewTest.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class confirmationPage {
	
	 WebDriver driver;

	public confirmationPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	

	//	Assert.assertTrue(ConfirmMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	
	@FindBy(css = ".hero-primary")
	WebElement ConfirmMsg;
	
	
	public String ValidatingConfirmMsg()
	{
		String Message = ConfirmMsg.getText();
		System.out.println(Message);
		return Message;
		
	}

}
