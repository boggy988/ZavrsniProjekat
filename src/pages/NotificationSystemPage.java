package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NotificationSystemPage extends BasicPage {

	public NotificationSystemPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
		super(driver, wait, js);

	}

	public WebElement getSuccOrDanger() {
		return this.driver.findElement(By.xpath("//*[contains(@class, 'alert--success') "
				+ "or contains(@class, 'alert--danger')][contains(@style,'display: block')]"));
	}

	// METODS

	public String verifMsg() {
		String msg = getSuccOrDanger().getText();
		return msg;
	}

	public void dissLoginMsg() {
		wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//*[contains(@class, 'system_message')][contains(@style, 'display: none;')]")));
	}

}
