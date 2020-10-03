package tests;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.CartSummaryPage;
import pages.LocationPopupPage;
import pages.LoginPage;
import pages.MealPage;
import pages.NotificationSystemPage;

public class MealItemTest extends BasicTest {

	@Test(priority = 10)
	public void addMealToCartTest() throws Exception {

		this.driver.get(baseURL + "/meal/lobster-shrimp-chicken-quesadilla-combo");

		LocationPopupPage lpp = new LocationPopupPage(driver, wait, js);
		MealPage mp = new MealPage(driver, wait, js);
		NotificationSystemPage nsp = new NotificationSystemPage(driver, wait, js);

		lpp.closePopup();

		mp.addMealToCart(3);

		String errMsg = nsp.verifMsg();
		Assert.assertEquals(errMsg, "The Following Errors Occurred:" + "\n" + "Please Select Location", "[ERROR] Error message not exist.");
		nsp.dissLoginMsg();

		lpp.selectLocation();
		lpp.setLocationName("City Center - Albany");
		Thread.sleep(1000);

		mp.addMealToCart(3);
		Thread.sleep(1000);

		String mealAdd = nsp.verifMsg();
		Assert.assertEquals(mealAdd, "Meal Added To Cart", "[ERROR] Meal is not added to cart.");
		Thread.sleep(1000);
	}

	@Test(priority = 20)
	public void addMealToFavorite() throws Exception {

		this.driver.get(baseURL + "/meal/lobster-shrimp-chicken-quesadilla-combo");

		LocationPopupPage lpp = new LocationPopupPage(driver, wait, js);
		MealPage mp = new MealPage(driver, wait, js);
		NotificationSystemPage nsp = new NotificationSystemPage(driver, wait, js);
		LoginPage lp = new LoginPage(driver, wait, js);

		lpp.closePopup();

		mp.addMealFavorite();
		Thread.sleep(1000);

		String loginPls = nsp.verifMsg();
		Assert.assertEquals(loginPls, "Please login first!", "[ERROR] Login Failed.");

		this.driver.get(baseURL + "/guest-user/login-form");
		lp.login(demoEmail, demoPass);

		this.driver.get(baseURL + "/meal/lobster-shrimp-chicken-quesadilla-combo");
		mp.addMealFavorite();
		Thread.sleep(1000);

		String addFav = nsp.verifMsg();
		Assert.assertEquals(addFav, "Product has been added to your favorites.", "[ERROR] Product is not added to favorite.");

	}

	@Test(priority = 30)
	public void clearCartTest() throws Exception {

		this.driver.get(baseURL + "/meals");
		SoftAssert sa = new SoftAssert();

		LocationPopupPage lpp = new LocationPopupPage(driver, wait, js);
		MealPage mp = new MealPage(driver, wait, js);
		NotificationSystemPage nsp = new NotificationSystemPage(driver, wait, js);
		CartSummaryPage csp = new CartSummaryPage(driver, wait, js);

		lpp.setLocationName("City Center - Albany");
		Thread.sleep(1000);

		File file = new File("data/data.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("Meals");

		for (int i = 1; i < sheet.getLastRowNum(); i++) {
			XSSFRow row = sheet.getRow(i);

			XSSFCell url1 = row.getCell(0);
			String url = url1.getStringCellValue();

			this.driver.get(url);
			Thread.sleep(1000);

			mp.addMealToCart(2);
			Thread.sleep(1000);

			sa.assertEquals(nsp.verifMsg(), "Meal Added To Cart", "[ERROR] Cart is empty.");
			Thread.sleep(500);
		}
		sa.assertAll();

		csp.clearAll();

		String removeAll = nsp.verifMsg();
		Assert.assertEquals(removeAll, "All meals removed from Cart successfully", "[ERROR] Meals are not removed." );
		
		fis.close();
		wb.close();
	}
}
