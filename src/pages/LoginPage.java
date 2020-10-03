package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasicPage {

	public LoginPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
		super(driver, wait, js);
	}

	public WebElement getEmail() {
		return this.driver.findElement(By.name("username"));
	}

	public WebElement getPassword() {
		return this.driver.findElement(By.name("password"));
	}

	// METODS

	public void login(String email, String password) {
		this.getEmail().clear();
		this.getEmail().sendKeys(email);
		this.getPassword().clear();
		this.getPassword().sendKeys(password);
		this.getPassword().sendKeys(Keys.ENTER);
	}
}
