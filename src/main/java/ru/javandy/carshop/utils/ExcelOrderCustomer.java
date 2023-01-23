package ru.javandy.carshop.utils;


import lombok.RequiredArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import ru.javandy.carshop.dto.OrderDto;
import ru.javandy.carshop.model.Order;
import ru.javandy.carshop.service.OrderService;


import java.awt.*;
import java.io.*;

@Component
@RequiredArgsConstructor
public class ExcelOrderCustomer {

    public void writeXLSXFile(OrderDto orderDto) {
        File xlsxFile = new File("C:/projects/CarShop/data/Excel/OrderCustomer.xlsx");
        String sheetName = "Чек";

        try (FileInputStream fileInputStream = new FileInputStream(xlsxFile);
             XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
             FileOutputStream fileOutputStream = new FileOutputStream(xlsxFile)) {

            XSSFSheet sheet = workbook.getSheet(sheetName);

            XSSFCell cell1 = sheet.createRow(3).createCell(1);
            cell1.setCellType(CellType.STRING);
            cell1.setCellValue(orderDto.getCustomer().getName());

            XSSFCell cell2 = sheet.createRow(4).createCell(1);
            cell2.setCellType(CellType.STRING);
            cell2.setCellValue(orderDto.getCustomer().getPhoneNumber());



//        for (int r = 0; r < 5; r++) {
//            XSSFRow row = sheet.createRow(r);
//
//            for (int c = 0; c < 5; c++) {
//                XSSFCell cell = row.createCell(c);
//                cell.setCellValue("Cell" + r + " " + c);
//            }
//        }

            workbook.write(fileOutputStream);
            fileOutputStream.flush();
            Desktop.getDesktop().print(new File(String.valueOf(xlsxFile)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
