package gr.codelearn.service;

import gr.codelearn.domain.Customer;
import gr.codelearn.domain.Directory;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerCSVService {

    public void writeCustomersToFile(List<Customer> customers, String filename) {
        Path path = Paths.get(Directory.FILE_DIRECTORY.getPath() + filename);
        try(Writer writer = Files.newBufferedWriter(path);
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)){
            customers.forEach(customer -> {
                try {
                    csvPrinter.printRecord(customer.getName(), customer.getGender(), customer.getYearOfBirth(), customer.getAddress(), customer.getDateOfRegistration());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public Optional<List<Customer>> readCustomersFromFile(String filename) {
        Path path = Paths.get(Directory.FILE_DIRECTORY.getPath() + filename);
        try(Reader reader = Files.newBufferedReader(path);
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)){
            ArrayList<Customer> customers = new ArrayList<>();
            List<CSVRecord> recordList = csvParser.getRecords();
            recordList.forEach(record -> {
                Customer customer = new Customer();
                customer.setName(record.get(0));
                customer.setGender(record.get(1));
                customer.setYearOfBirth(Integer.parseInt(record.get(2)));
                customer.setAddress(record.get(3));
                // todo implement date parsing
                //customer.setDateOfRegistration(record.get(4));
                customers.add(customer);
            });
            return Optional.of(customers);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return Optional.empty();
    }
}

