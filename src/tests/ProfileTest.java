package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.AuthPage;
import pages.LocationPopupPage;
import pages.LoginPage;
import pages.NotificationSystemPage;
import pages.ProfilePage;

public class ProfileTest extends BasicTest {

	@Test(priority = 10)
	public void editProfileTest() throws Exception {

		this.driver.get(baseURL + "/guest-user/login-form");

		LocationPopupPage lpp = new LocationPopupPage(driver, wait, js);
		LoginPage lp = new LoginPage(driver, wait, js);
		NotificationSystemPage nsp = new NotificationSystemPage(driver, wait, js);
		ProfilePage pp = new ProfilePage(driver, wait, js);
		AuthPage ap = new AuthPage(driver, wait, js);

		lpp.closePopup();

		lp.login(demoEmail, demoPass);
		String logms = nsp.verifMsg();
		Assert.assertEquals(logms, "Login Successfull", "[ERROR] Login Failed.");

		this.driver.get(baseURL + "/member/profile");
		pp.updateProfile("Michael", "Kid", "Bulevar nemanjica 32", "0640456456", "18000", "United States", "Indiana",
				"Troy");
		String setMsg = nsp.verifMsg();
		Assert.assertEquals(setMsg, "Setup Successful", "[ERROR] Profile didnt update.");

		ap.logoutAcc();
		String logoutMsg = nsp.verifMsg();
		Assert.assertEquals(logoutMsg, "Logout Successfull!", "[ERROR] Login Failed.");

	}

	@Test(priority = 20)
	public void changeProfileImageTest() throws Exception {

		this.driver.get(baseURL + "/guest-user/login-form");

		LocationPopupPage lpp = new LocationPopupPage(driver, wait, js);
		LoginPage lp = new LoginPage(driver, wait, js);
		NotificationSystemPage nsp = new NotificationSystemPage(driver, wait, js);
		ProfilePage pp = new ProfilePage(driver, wait, js);
		AuthPage ap = new AuthPage(driver, wait, js);

		lpp.closePopup();

		lp.login(demoEmail, demoPass);
		String logms = nsp.verifMsg();
		Assert.assertEquals(logms, "Login Successfull", "[ERROR] Login Failed.");

		this.driver.get(baseURL + "/member/profile");

		pp.uploadImage();
		String sucImgUp = nsp.verifMsg();
		Assert.assertEquals(sucImgUp, "Profile Image Uploaded Successfully", "[ERROR] Profile image didnt update.");
		nsp.dissLoginMsg();

		pp.deleteImage();
		String delImg = nsp.verifMsg();
		Assert.assertEquals(delImg, "Profile Image Deleted Successfully", "[ERROR] Profile image didnt delete.");
		nsp.dissLoginMsg();

		ap.logoutAcc();
		String logoutMsg = nsp.verifMsg();
		Assert.assertEquals(logoutMsg, "Logout Successfull!", "[ERROR] Logout Failed.");

	}

}