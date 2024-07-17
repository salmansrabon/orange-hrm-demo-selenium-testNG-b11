package testrunner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;
import setup.Setup;

public class LoginTestRunner extends Setup {
    LoginPage loginPage;
    @Test(priority = 1, description = "Admin can not login with invalid creds")
    public void doLoginIfWrongCreds(){
        loginPage=new LoginPage(driver);
        String loginTitleActual= driver.findElement(By.className("orangehrm-login-title")).getText();
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertTrue(loginTitleActual.contains("Login"));
        softAssert.assertTrue(driver.findElement(By.className("orangehrm-login-branding")).isDisplayed());

        loginPage.doLogin("wronguser","wrongpass");
        WebElement alertTextElem= driver.findElement(By.className("oxd-alert-content-text"));
        softAssert.assertEquals(alertTextElem.getText(),"Invalid credentials");

        softAssert.assertAll();
    }
    @Test(priority = 2,description = "Admin can login with valid creds", groups = "smoke")
    public void doLogin(){
        loginPage=new LoginPage(driver);
        loginPage.doLogin("Admin","admin123");
        //loginPage.doLogin(System.getProperty("username"),System.getProperty("password"));
        Assert.assertTrue(loginPage.btnProfileImage.isDisplayed());
    }
    @Test(priority = 3, description = "Admin can logout by clicking on logout button", groups="smoke")
    public void doLogout(){
        loginPage=new LoginPage(driver);
        loginPage.doLogout();
        WebElement forgotPasswordLinkElem= driver.findElement(By.className("orangehrm-login-forgot-header"));
        Assert.assertTrue(forgotPasswordLinkElem.isDisplayed());
    }


}
