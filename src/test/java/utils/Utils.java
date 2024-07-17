package utils;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import setup.EmployeeModel;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static void saveUsers(EmployeeModel model) throws IOException, ParseException {
        String fileLocation="./src/test/resources/users.json";
        JSONParser parser=new JSONParser();
        JSONArray empArray= (JSONArray) parser.parse(new FileReader(fileLocation));

        JSONObject empObj=new JSONObject();
        empObj.put("firstName",model.getFirstname());
        empObj.put("lastName",model.getLastname());
        empObj.put("userName",model.getUsername());
        empObj.put("password",model.getPassword());

        empArray.add(empObj);

        FileWriter writer=new FileWriter(fileLocation);
        writer.write(empArray.toJSONString());
        writer.flush();
        writer.close();

    }
    public static JSONArray readJSONData() throws IOException, ParseException {
        String fileLocation="./src/test/resources/users.json";
        JSONParser parser=new JSONParser();
        JSONArray empArray= (JSONArray) parser.parse(new FileReader(fileLocation));
        return empArray;
    }

    public static void takeScreenshot(WebDriver driver) throws IOException {
        File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String time = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss-aa").format(new Date());
        String fileWithPath = "./src/test/resources/screenshots/" + time + ".png";
        File DestFile = new File(fileWithPath);
        FileUtils.copyFile(screenshotFile, DestFile);
    }
}
