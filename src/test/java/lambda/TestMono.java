package lambda;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class TestMono {

	private static final Logger logger = Logger.getLogger(TestMono.class.getName());

	private static String username = "s.k.rath370";
	private static String accessKey = "LT_PJu4i7Gnp48MYaod5g9OIfFHTNM7TkClECX2CZJWiOkYMwl";
	
	
	private static String LAMBDA_TEST_URL = "https://hub.lambdatest.com/wd/hub";

	private static String TEST_SITE_URL = "https://www.lambdatest.com/";

	@Test(timeOut = 20000)
	public void loginTest() throws MalformedURLException {
		ChromeOptions browserOptions = new ChromeOptions();
		browserOptions.setPlatformName("Windows 10");
		browserOptions.setBrowserVersion("119.0");
		HashMap<String, Object> ltOptions = new HashMap<String, Object>();
		ltOptions.put("username", username);
		ltOptions.put("accessKey", accessKey);
		ltOptions.put("resolution", "1920x1080");
		ltOptions.put("build", "selnmBuild");
		ltOptions.put("project", "selTest");
		ltOptions.put("selenium_version", "4.0.0");
		ltOptions.put("w3c", true);
		browserOptions.setCapability("LT:Options", ltOptions);

		t1(browserOptions);

	}

	public void t1(ChromeOptions browserOptions) throws MalformedURLException {

		try {
			WebDriver driver = new RemoteWebDriver(new URL(LAMBDA_TEST_URL), browserOptions);

			driver.get(TEST_SITE_URL);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));

			if (element.isDisplayed()) {
				logger.info("Body element is visible.");
			} else {
				logger.warning("Body element is not visible.");
			}

			driver.findElement(By.xpath("//*[@id=\"__next\"]/div[1]/section[9]/div/p/a")).click();
			WebElement scndCase = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"codeless_row\"]/div/div[4]/a")));
			
			
			scndCase.click();
			logger.info("2nd ok = " + driver.getTitle());


			//			WebElement thirdCase = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='social_button']//a")));
			
			WebElement thirdCase = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Ask the Community")));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", thirdCase);
			thirdCase.click();
			
			logger.info("3rd ok = " + driver.getTitle());

			driver.quit();

		} catch (Exception e) {
			logger.severe("Error occurred: " + e.getMessage());
		}

	}

}
