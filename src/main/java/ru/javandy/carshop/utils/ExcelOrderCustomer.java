package ru.javandy.carshop.utils;


import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.*;

public class ExcelOrderCustomer {

    public static void writeXLSXFile() throws IOException {
        File xlsxFile = new File("C:\\projects\\CarShop\\src\\main\\resources\\Excel\\OrderCustomer.xlsx");

        try {
            FileInputStream inputStream = new FileInputStream(xlsxFile);
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheet("Лист 1");



            inputStream.close();
            FileOutputStream outputStream = new FileOutputStream(xlsxFile);
            workbook.write(outputStream);

            workbook.close();
            outputStream.close();


        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
