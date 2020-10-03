package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthPage extends BasicPage {

	public AuthPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
		super(driver, wait, js);
	}

	public WebElement getProfileName() {
		return this.driver.findElement(By.xpath("//*[@id='header']/div[2]/div/div[2]/div[2]/ul/li/a"));
	}

	public WebElement getLogoutBtn() {
		return this.driver.findElement(By.xpath("//*[@id='header']/div[2]/div/div[2]/div[2]/ul/li/div/ul/li[2]/a"));
	}

	// METODS

	public void logoutAcc() {
		this.getProfileName().click();
		this.getLogoutBtn().click();
	}

}
