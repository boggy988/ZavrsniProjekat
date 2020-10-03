package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MealPage extends BasicPage{

	public MealPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
		super(driver, wait, js);
	}
	
	public WebElement getFavoriteBtn() {
		return this.driver.findElement(By.xpath("//*[contains(@class, 'itemfav link')]"));
	}
	
	public WebElement getQuantInput() {
		return this.driver.findElement(By.xpath("//*[@id='body']/section[1]/div/div/div[2]/div/div[3]/div[1]/ul/li[3]/input"));
	}
	
	public WebElement getAddToCart() {
		return this.driver.findElement(By.xpath("//*[contains(@class, 'js-proceedtoAddInCart')]"));
	}
	
	//METODS
	
	public void addMealToCart(int quant) {
		String quantity = String.valueOf(quant);
		
		this.getQuantInput().sendKeys(Keys.chord(Keys.CONTROL, "a"));
		this.getQuantInput().sendKeys(quantity);
		this.getAddToCart().click();
	}
	
	public void addMealFavorite() {
		this.getFavoriteBtn().click();
	}

}
