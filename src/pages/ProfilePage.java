package pages;

import java.io.File;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage extends BasicPage {

	public ProfilePage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
		super(driver, wait, js);
	}

	public WebElement getFirstName() {
		return this.driver.findElement(By.name("user_first_name"));
	}

	public WebElement getLastName() {
		return this.driver.findElement(By.name("user_last_name"));
	}

	public WebElement getAddress() {
		return this.driver.findElement(By.name("user_address"));
	}

	public WebElement getPhone() {
		return this.driver.findElement(By.name("user_phone"));
	}

	public WebElement getZipCode() {
		return this.driver.findElement(By.name("user_zip"));
	}

	public Select getCountry() {
		WebElement country = this.driver.findElement(By.name("user_country_id"));
		Select sel1 = new Select(country);
		return sel1;
	}

	public Select getState() {
		WebElement state = this.driver.findElement(By.name("user_state_id"));
		Select sel2 = new Select(state);
		return sel2;
	}

	public Select getCity() {
		WebElement city = this.driver.findElement(By.name("user_city"));
		Select sel3 = new Select(city);
		return sel3;
	}

	public WebElement getSaveBtn() {
		return this.driver.findElement(By.xpath("//*[@id='profileInfoFrm']/div[5]/div/fieldset/input"));
	}

	public WebElement getUploadImgBtn() {
		return this.driver.findElement(By.xpath("//*[@id=\"profileInfo\"]/div/div[1]/div/a"));
	}

	public WebElement getDeleteImgBtn() {
		return this.driver.findElement(By.xpath("//*[@id='profileInfo']/div/div[1]/div/a[2]/i"));
	}

	// METODS

	public void uploadImage() throws Exception {
		js.executeScript("arguments[0].click();", this.getUploadImgBtn());
		WebElement uploadImg = this.driver.findElement(By.xpath("//input[@name = 'file']"));
		String imgPath = new File("images/slika.png").getAbsolutePath();
		uploadImg.sendKeys(imgPath);
	}

	public void deleteImage() throws Exception {
		js.executeScript("arguments[0].click();", this.getDeleteImgBtn());
	}

	public void updateProfile(String firstName, String lastName, String address, String phone, String zipCode,
			String country, String state, String city) throws Exception {
		this.getFirstName().clear();
		this.getLastName().clear();
		this.getAddress().clear();
		this.getPhone().clear();
		this.getZipCode().clear();

		this.getFirstName().sendKeys(firstName);
		this.getLastName().sendKeys(lastName);
		this.getAddress().sendKeys(address);
		this.getPhone().sendKeys(phone);
		this.getZipCode().sendKeys(zipCode);
		this.getCountry().selectByVisibleText(country);
		Thread.sleep(500);
		this.getState().selectByVisibleText(state);
		Thread.sleep(500);
		this.getCity().selectByVisibleText(city);

		this.getSaveBtn().click();
	}

}
