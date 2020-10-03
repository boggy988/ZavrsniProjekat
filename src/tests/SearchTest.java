package tests;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.LocationPopupPage;
import pages.SearchResultPage;

public class SearchTest extends BasicTest {

	@Test(priority = 10)
	public void searchResultsTest() throws Exception {

		this.driver.get(baseURL + "/meals");

		LocationPopupPage lpp = new LocationPopupPage(driver, wait, js);
		SearchResultPage srp = new SearchResultPage(driver, wait, js);

		SoftAssert sa = new SoftAssert();

		lpp.setLocationName("City Center - Albany");
		Thread.sleep(1000);

		File file = new File("data/data.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(0);

		for (int i = 1; i <= 6; i++) {
			Thread.sleep(1000);
			XSSFRow row = sheet.getRow(i);

			lpp.selectLocation();
			String Location = row.getCell(0).getStringCellValue();
			lpp.setLocationName(Location);
			Thread.sleep(1000);

			String urlFromData = row.getCell(1).getStringCellValue();
			this.driver.get(urlFromData);

			int realCount = srp.numOfFoundMelas();
			int resultCount = (int) row.getCell(2).getNumericCellValue();
			Assert.assertEquals(realCount, resultCount, "[ERROR] Number of results is not the same.");
			Thread.sleep(1000);

			for (int j = 3; j < 3 + row.getCell(2).getNumericCellValue(); j++) {
				String realResult = srp.namesOfMeals().get(j - 3);
				String result = row.getCell(j).getStringCellValue();
				sa.assertTrue(realResult.contains(result), "[ERROR] The order of the results is not the same.");
				Thread.sleep(2000);
			}
			sa.assertAll();
		}

		fis.close();
		wb.close();

	}

}
