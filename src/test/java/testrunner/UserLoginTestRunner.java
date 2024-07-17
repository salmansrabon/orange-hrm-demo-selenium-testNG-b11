package testrunner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import pages.LoginPage;
import setup.Setup;

import java.io.IOException;

import static utils.Utils.readJSONData;

public class UserLoginTestRunner extends Setup {
    @Test(priority = 1, testName = "user Login", description = "User can login with valid credentials")
    public void userLogin() throws IOException, ParseException {
        LoginPage loginPage=new LoginPage(driver);
        JSONArray empArray= readJSONData();
        JSONObject empObj= (JSONObject) empArray.get(empArray.size()-1);

        String username=empObj.get("userName").toString();
        String password=empObj.get("password").toString();
        loginPage.doLogin(username,password);
    }
}
