package testrunner;

import com.github.javafaker.Faker;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.PIMPage;
import setup.EmployeeModel;
import setup.Setup;
import utils.Utils;

import java.io.IOException;
import java.time.Duration;

public class PIMTestRunner extends Setup {
    LoginPage loginPage;
    PIMPage pimPage;
    @BeforeTest(groups = "smoke")
    public void doLogin(){
        loginPage=new LoginPage(driver);
        loginPage.doLogin("Admin","admin123");
    }
    @Test(priority = 1, groups = "smoke")
    public void checkEmployeeList() throws InterruptedException {
        pimPage=new PIMPage(driver);
        pimPage.leftMenubar.get(1).click();
        Thread.sleep(5000);
        String messageActual= driver.findElements(By.className("oxd-text--span")).get(12).getText();
        String messageExpected="Records Found";
        Assert.assertTrue(messageActual.contains(messageExpected));
    }
    @Test(priority = 2)
    public void addNewEmployee() throws IOException, ParseException {
        pimPage.button.get(2).click();
        driver.findElement(By.className("oxd-switch-input")).click();
        pimPage=new PIMPage(driver);
        Faker faker=new Faker();
        String firstName=faker.name().firstName();
        String lastName=faker.name().lastName();
        String username= faker.name().username();
        String password="P@ssword123";

        EmployeeModel model=new EmployeeModel();
        model.setFirstname(firstName);
        model.setLastname(lastName);
        model.setUsername(username);
        model.setPassword(password);

        pimPage.createNewEmployee(model);
        WebElement headerTitleElem= driver.findElement(By.xpath("//h6[text()=\"Personal Details\"]"));
        System.out.println(headerTitleElem.getText());
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.visibilityOf(headerTitleElem));
        String textTitle= headerTitleElem.getText();
        Assert.assertTrue(textTitle.contains("Personal Details"));

        Utils.saveUsers(model);

    }
}
