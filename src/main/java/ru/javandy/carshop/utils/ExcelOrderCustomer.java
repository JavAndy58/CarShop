package ru.javandy.carshop.utils;


import lombok.RequiredArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import ru.javandy.carshop.dto.DetailDto;
import ru.javandy.carshop.dto.OrderDto;
import ru.javandy.carshop.model.Order;
import ru.javandy.carshop.service.OrderService;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

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

            XSSFRow rowNumberOrder = sheet.createRow(1);
            XSSFCell cellNumberOrder = rowNumberOrder.createCell(2);
            cellNumberOrder.setCellValue("Заказ №");
            XSSFCell cellNumberOrderData = rowNumberOrder.createCell(4);
            cellNumberOrderData.setCellValue(orderDto.getId());


            XSSFRow rowNameCustomer = sheet.createRow(3);
            XSSFCell cellNameCustomer = rowNameCustomer.createCell(0);
            cellNameCustomer.setCellValue("ФИО");
            XSSFCell cellNameCustomerData = rowNameCustomer.createCell(1);
            cellNameCustomerData.setCellValue(orderDto.getCustomer().getName());
            XSSFCell cellIp = rowNameCustomer.createCell(2);
            cellIp.setCellValue("ИП Громов А.А. маг. CarBon ");

            XSSFRow rowTelephone = sheet.createRow(4);
            XSSFCell cellTelephone = rowTelephone.createCell(0);
            cellTelephone.setCellValue("Телефон");
            XSSFCell cellTelephoneData = rowTelephone.createCell(1);
            cellTelephoneData.setCellValue(orderDto.getCustomer().getPhoneNumber());
            XSSFCell cellAdres = rowTelephone.createCell(2);
            cellAdres.setCellValue("ул.Ново-Терновская, 3");

            XSSFRow rowAuto = sheet.createRow(5);
            XSSFCell cellAuto = rowAuto.createCell(0);
            cellAuto.setCellValue("Авто");
            XSSFCell cellAutoData = rowAuto.createCell(1);
            cellAutoData.setCellValue(orderDto.getCar().getName());
            XSSFCell cellPhone = rowAuto.createCell(2);
            cellPhone.setCellValue("8-927-289-34-34");


            XSSFRow rowVin = sheet.createRow(6);
            XSSFCell cellVin = rowVin.createCell(0);
            cellVin.setCellValue("Vin код");
            XSSFCell cellVinData = rowVin.createCell(1);
            cellVinData.setCellValue(orderDto.getCar().getVinCode());


            List<DetailDto> detailDtoList = orderDto.getDetails();

            CellStyle style = workbook.createCellStyle();
            style.setBorderBottom(BorderStyle.THIN);
            style.setBorderLeft(BorderStyle.THIN);
            style.setBorderRight(BorderStyle.THIN);
            style.setBorderTop(BorderStyle.THIN);

            XSSFRow rowTable = sheet.createRow(8);
            XSSFCell cellN = rowTable.createCell(0);
            cellN.setCellStyle(style);
            cellN.setCellValue("№");
            XSSFCell cellName = rowTable.createCell(1);
            cellName.setCellStyle(style);
            cellName.setCellValue("Наименование детали");
            XSSFCell cellNum = rowTable.createCell(2);
            cellNum.setCellStyle(style);
            cellNum.setCellValue("Кол-во");
            XSSFCell cellRetailPrice = rowTable.createCell(3);
            cellRetailPrice.setCellStyle(style);
            cellRetailPrice.setCellValue("Цена");
            XSSFCell cellSumMoney = rowTable.createCell(4);
            cellSumMoney.setCellStyle(style);
            cellSumMoney.setCellValue("Сумма");

            int rowNum = 8;
            int tempN = 0;
            for (DetailDto detailDto : detailDtoList) {


                XSSFRow row = sheet.createRow(rowNum++);
                XSSFCell cellNData = row.createCell(0);
                cellNData.setCellStyle(style);
                cellNData.setCellValue(tempN++);

                XSSFCell cellNameData = row.createCell(1);
                cellNameData.setCellStyle(style);
                cellNameData.setCellValue(detailDto.getName());

                XSSFCell cellAmount = row.createCell(2);
                cellAmount.setCellType(CellType.NUMERIC);
                cellAmount.setCellStyle(style);
                cellAmount.setCellValue(detailDto.getAmount());

                XSSFCell cellRetailPriceData = row.createCell(3);
                cellRetailPriceData.setCellType(CellType.NUMERIC);
                cellRetailPriceData.setCellStyle(style);
                cellRetailPriceData.setCellValue(detailDto.getRetailPrice());

                XSSFCell cellSumMoneyData = row.createCell(4);
                cellSumMoneyData.setCellType(CellType.NUMERIC);
                cellSumMoneyData.setCellStyle(style);
                cellSumMoneyData.setCellValue(detailDto.getRetailPrice());

            }

            XSSFCell cellTotal = sheet.createRow(23).createCell(4);
            cellTotal.setCellType(CellType.NUMERIC);
            cellTotal.setCellStyle(style);
            cellTotal.setCellValue(orderDto.getPayOrder());


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
//            Runtime rt = Runtime.getRuntime();
//            Process process = rt.exec("C:\\Program Files\\Microsoft Office\\root\\Office16\\EXCEL.EXE c:\\projects\\CarShop\\data\\Excel\\OrderCustomer.xlsx");

            Runtime.getRuntime().exec("C:\\Program Files\\Microsoft Office\\root\\Office16\\EXCEL.EXE c:\\projects\\CarShop\\data\\Excel\\OrderCustomer.xlsx");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
