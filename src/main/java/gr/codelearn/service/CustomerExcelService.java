package gr.codelearn.service;

import gr.codelearn.domain.Customer;
import gr.codelearn.domain.Directory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class CustomerExcelService {

    public void writeCustomersToFile(List<Customer> customers, String filename) {
        try(FileOutputStream fileOutputStream = new FileOutputStream(Directory.FILE_DIRECTORY.getPath() + filename);
            XSSFWorkbook workbook = new XSSFWorkbook()){
            XSSFSheet sheet = workbook.createSheet("Customers");
            int rowIndex = 0;
            for (Customer customer : customers) {
                XSSFRow row = sheet.createRow(rowIndex);
                XSSFCell cell0 = row.createCell(0);
                cell0.setCellValue(customer.getName());
                XSSFCell cell1 = row.createCell(1);
                cell1.setCellValue(customer.getGender());
                XSSFCell cell2 = row.createCell(2);
                cell2.setCellValue(customer.getYearOfBirth());
                XSSFCell cell3 = row.createCell(3);
                cell3.setCellValue(customer.getAddress());
                XSSFCell cell4 = row.createCell(4);
                cell4.setCellValue(customer.getDateOfRegistration());
                rowIndex++;
            }
            workbook.write(fileOutputStream);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public Optional<List<Customer>> readCustomersFromFile(String filename) {
        try(FileInputStream fileInputStream = new FileInputStream(Directory.FILE_DIRECTORY.getPath() + filename);
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream)){
            ArrayList<Customer> customerList = new ArrayList<>();
            XSSFSheet customerSheet = workbook.getSheet("Customers");
            Iterator<Row> rowIterator = customerSheet.iterator();
            while (rowIterator.hasNext()){
                Row currentRow = rowIterator.next();
                Customer customer = new Customer();
                Cell cell0 = currentRow.getCell(0);
                customer.setName(cell0.getStringCellValue());
                Cell cell1 = currentRow.getCell(1);
                customer.setGender(cell1.getStringCellValue());
                Cell cell2 = currentRow.getCell(2);
                customer.setYearOfBirth((int) cell2.getNumericCellValue());
                Cell cell3 = currentRow.getCell(3);
                customer.setAddress(cell3.getStringCellValue());
                Cell cell4 = currentRow.getCell(4);
                customer.setDateOfRegistration(cell4.getDateCellValue());
                customerList.add(customer);
            }
            return Optional.of(customerList);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return Optional.empty();
    }
}
