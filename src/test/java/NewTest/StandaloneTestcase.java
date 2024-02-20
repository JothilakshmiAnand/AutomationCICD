package NewTest;

import java.time.Duration;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandaloneTestcase {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		// LandingPage login = new LandingPage(driver);

		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.id("userEmail")).sendKeys("jothi.lakshmi@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Athvika@123");
		driver.findElement(By.id("login")).click();

		List<WebElement> product = driver.findElements(By.cssSelector(".col-md-6"));

		Thread.sleep(5000);
		WebElement prod = product.stream()
				.filter(s -> s.findElement(By.xpath("//div/h5/b")).getText().equals("ZARA COAT 3")).findFirst()
				.orElse(null);

		prod.findElement(By.xpath("//div/button[contains(.,'Add To Cart')]")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		wait.until(ExpectedConditions
				.invisibilityOfElementLocated(By.cssSelector("html > head:nth-child(1) > style:nth-child(12)")));

		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

		List<WebElement> cartProduct = driver.findElements(By.cssSelector(".cartSection h3"));

		Boolean match = cartProduct.stream()
				.anyMatch(cartProducts -> cartProducts.getText().equalsIgnoreCase("ZARA COAT 3"));
		Assert.assertTrue(match);

		driver.findElement(By.cssSelector(".totalRow button")).click();

		driver.findElement(By.xpath("(//input[@class='input txt text-validated'])[1]")).sendKeys("4542 9931 9292 2293");

		WebElement month = driver.findElement(By.xpath("(//select[@class='input ddl'])[1]"));
		month.click();
		Select dropdnmonth = new Select(month);
		dropdnmonth.selectByVisibleText("10");
		WebElement date = driver.findElement(By.xpath("(//select[@class='input ddl'])[2]"));
		date.click();

		Select dropdndate = new Select(date);
		dropdndate.selectByVisibleText("22");

		driver.findElement(By.xpath("(//input[@class='input txt'])[1]")).sendKeys("234");
		driver.findElement(By.xpath("(//input[@class='input txt'])[2]")).sendKeys("Jothi");

		// give input in the select field

		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("India");

		// select the full dropdown
		WebElement dropdown = driver.findElement(By.cssSelector(".ta-item"));

		// get the list of country name from the drop down

		List<WebElement> options = driver.findElements(By.cssSelector(".ng-star-inserted"));

		// Use Java Streamto filter India and click on it

		options.stream().filter(option -> option.getText().equals("India")).findFirst().ifPresent(option -> {
			WebElement relocated = dropdown.findElement(By.xpath("(//button[contains(.,'India')])[2]"));
			relocated.click();
		});

		driver.findElement(By.cssSelector(".btnn.action__submit.ng-star-inserted")).click();

		String ConfirmMsg = driver.findElement(By.cssSelector(".hero-primary")).getText();

		Assert.assertTrue(ConfirmMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

		// option.findElement(By.xpath("(//button[contains(.,'India')])[2]")).click();}

		/*
		 * Actions a = new Actions(driver);
		 * 
		 * a.sendKeys(driver.findElement(By.
		 * xpath("//input[@placeholder='Select Country']")), "India").build().perform();
		 * 
		 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
		 * ".ta-item")));
		 * 
		 * driver.findElement(By.xpath("(//button[contains(.,'India')])[2]")).click();
		 */

	}
}
