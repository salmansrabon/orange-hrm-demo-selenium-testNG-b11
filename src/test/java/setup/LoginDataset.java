package setup;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LoginDataset {
    @DataProvider(name = "LoginData")
    public Object[][] getData(){
        return new Object[][]{
                {"admin","admin123"},
                {"user1","1234"},
                {"user2","1234"}
        };
    }
    @DataProvider(name = "LoginCSVData")
    public Object[][] getCSVData() throws IOException {
        String csvFilePath="./src/test/resources/users.csv";
        List<Object[]> data=new ArrayList<>();
        CSVParser csvParser= new CSVParser(new FileReader(csvFilePath), CSVFormat.DEFAULT.withFirstRecordAsHeader());

        for(CSVRecord csvRecord: csvParser){
            String username=csvRecord.get("username");
            String password=csvRecord.get("password");
            data.add(new Object[]{username,password});
        }
        return data.toArray(new Object[0][]);
    }

}
