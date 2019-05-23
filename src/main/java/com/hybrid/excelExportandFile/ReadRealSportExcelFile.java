package com.hybrid.excelExportandFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ReadRealSportExcelFile {

    public Sheet readExcel(String filePath,String fileName,String sheetName) throws IOException{
        File file =	new File(filePath+"/"+fileName);
        FileInputStream inputStream = new FileInputStream(file);
        Workbook realSportWorkbook = null;

        String fileExtensionName = fileName.substring(fileName.indexOf("."));

        if(fileExtensionName.equals(".xlsx")){
            realSportWorkbook = new XSSFWorkbook(inputStream);
        }

        else if(fileExtensionName.equals(".xls")){

            realSportWorkbook = new HSSFWorkbook(inputStream);
        }

        Sheet  realSportSheet =  realSportWorkbook.getSheet(sheetName);
        return realSportSheet;
    }
}
