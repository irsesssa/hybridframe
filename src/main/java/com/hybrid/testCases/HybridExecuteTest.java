package com.hybrid.testCases;

import java.io.IOException;
import java.util.Properties;

import com.hybrid.excelExportandFile.ReadRealSportExcelFile;
import com.hybrid.operation.ReadObject;
import com.hybrid.operation.UIOperation;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



public class HybridExecuteTest {

    WebDriver webdriver = null;
    @Test(dataProvider="hybridData")

    public void testLogin(String testcaseName,String keyword,String objectName,String objectType,String value) throws Exception {
        // TODO Auto-generated method stub


        if(testcaseName!=null&&testcaseName.length()!=0){
            webdriver=new ChromeDriver();
        }
        ReadObject object = new ReadObject();
        Properties allObjects =  object.getObjectRepository();
        UIOperation operation = new UIOperation(webdriver);

        operation.perform(allObjects, keyword, objectName, objectType, value);

    }


    @DataProvider(name="hybridData")
    public Object[][] getDataFromDataProvider() throws IOException{
        Object[][] object = null;
        ReadRealSportExcelFile file = new ReadRealSportExcelFile();

        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Drivers\\chromedriver.exe");

        Sheet  realSportSheet = file.readExcel(System.getProperty("user.dir"),"TestCase.xlsx" , "KeywordFramework");

        int rowCount = realSportSheet.getLastRowNum()- realSportSheet.getFirstRowNum();
        object = new Object[rowCount][5];
        for (int i = 0; i < rowCount; i++) {

            Row row =  realSportSheet.getRow(i+1);

            for (int j = 0; j < row.getLastCellNum(); j++) {

                object[i][j] = row.getCell(j).toString();
            }

        }
        System.out.println("");
        return object;
    }
}