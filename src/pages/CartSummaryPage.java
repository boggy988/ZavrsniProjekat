package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartSummaryPage extends BasicPage{

	public CartSummaryPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
		super(driver, wait, js);
	}
	
	public WebElement getClearAllBtn() {
		return this.driver.findElement(By.xpath("//*[contains(@class, 'btn--third')]"));
	}
	
	//METODS
	
	public void clearAll() {
		this.getClearAllBtn().click();
	}
		
}
