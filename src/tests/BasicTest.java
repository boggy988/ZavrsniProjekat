package tests;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hpsf.Date;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

public abstract class BasicTest {

	protected WebDriver driver;
	protected WebDriverWait wait;
	protected String baseURL = "http://demo.yo-meals.com";
	protected String demoEmail = "customer@dummyid.com";
	protected String demoPass = "12345678a";

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "driver-lib\\chromedriver.exe");
		this.driver = new ChromeDriver();

		this.driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		this.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		this.driver.manage().window().maximize();

	}

	@AfterMethod
	public void afterTest(ITestResult result) throws Exception {
		String testTime = new SimpleDateFormat("yyyyMMddHHmmss'.txt'").format(new Date());
		if (result.getStatus() == ITestResult.FAILURE) {
			TakesScreenshot ts = (TakesScreenshot) driver;
			File ss = ts.getScreenshotAs(OutputType.FILE);
			FileUtils.copyDirectory(ss, new File("./screenshots/" + testTime + ".png"));
		}
		this.driver.manage().deleteAllCookies();

	}

	@AfterClass
	public void afterClass() {
		this.driver.quit();
	}
}