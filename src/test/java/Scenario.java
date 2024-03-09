import java.time.Duration;
import java.util.Iterator;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;

public class Scenario {
    //script can run on multiple browsers
	public static WebDriver driver = null;
	public static String browser = "chrome";

	@BeforeMethod
	public void setup() throws Exception {
		if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("ie")) {
			driver = new InternetExplorerDriver();
		} else {
			throw new Exception("Browser is not correct");
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@Test
	public void flipkart() throws InterruptedException {
		// Open Flipkart Website:
		// Open the ShopNow website:
		driver.get("https://www.flipkart.com");
		// Verify that the homepage loads successfully.
		((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");

		// Search and Add to Cart:
		// In the search bar, type "laptop" and press Enter.
		driver.findElement(By.cssSelector("input.Pke_EE")).sendKeys("laptops");
		driver.findElement(By.cssSelector("input.Pke_EE")).sendKeys(Keys.ENTER);
		driver.findElement(By.xpath("//div[text()='Newest First']")).click();
		// Click on one of the search results to view the product details.
		driver.findElement(By.xpath("//div[text()='Apple MacBook Air Apple M3 - (8 GB/512 GB SSD/macOS Sonoma) MRYV3HN/A']")).click();
		// Add the selected laptop to the shopping cart.
		Set<String> winids = driver.getWindowHandles();
		Iterator<String> itr = winids.iterator();
		itr.next();
		String winidTwo = itr.next();
		driver.switchTo().window(winidTwo);
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.cssSelector("ul.row>li>button"))).click();

		// Proceed to Checkout:
		// Navigate to the shopping cart.
		// Verify that the correct item is in the cart.
		driver.findElement(By.xpath("//a[text()='Apple MacBook Air Apple M3 - (8 GB/512 GB SSD/macOS Sonoma) MRYV3HN/A']")).isDisplayed();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
