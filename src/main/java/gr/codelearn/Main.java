package gr.codelearn;

import gr.codelearn.domain.Customer;
import gr.codelearn.service.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
public class Main {
    public static void main(String[] args) {
        Customer customer1 = Customer.builder().name("Ioannis Kalamariss").gender("M").yearOfBirth(1970).address("Athens 32").dateOfRegistration(new Date()).build();
        List<Customer> customerList = new ArrayList<>();
        Customer customer2 = Customer.builder().name("Ioannis Kalamaris").gender("M").yearOfBirth(1970).address("Athens 32").dateOfRegistration(new Date()).build();
        Customer customer3 = Customer.builder().name("Dimitris Dim").gender("M").yearOfBirth(1870).address("Thes 15").dateOfRegistration(new Date()).build();
        Customer customer4 = Customer.builder().name("Thomas Thom").gender("M").yearOfBirth(1999).address("Volos 31").dateOfRegistration(new Date()).build();
        Customer customer5 = Customer.builder().name("Thanos Than").gender("M").yearOfBirth(1300).address("Lamia 17").dateOfRegistration(new Date()).build();
        Customer customer6 = Customer.builder().name("Katerina Kater").gender("F").yearOfBirth(2010).address("Sparta 1").dateOfRegistration(new Date()).build();

        // Object
        ByteService<Customer> byteService = new ByteService<>();
        byteService.writeObjectToFile(customer1, "customer.txt");
        Optional<Customer> customerOptional = byteService.readObjectFromFile("customer.txt");
        customerOptional.ifPresent(customer -> log.info("{}", customer));

        //OnlineCustomer onlineCustomer = new OnlineCustomer();
        //byteService.writeObjectToFile(onlineCustomer, "online_customer.txt");
        //List<Customer> customerList = new ArrayList<>();

        // XML
        customerList.add(customer2);
        customerList.add(customer3);
        customerList.add(customer4);
        customerList.add(customer5);
        customerList.add(customer6);
        XMLService<Customer> xmlService = new XMLService<>();
        xmlService.writeObjectToFile(customerList, "customers.xml");
        Optional<List<Customer>> optionalCustomerListXML = xmlService.readObjectFromFile("customers.xml");
        List<Customer> customersXML = optionalCustomerListXML.orElse(List.of());
        customersXML.forEach(customer -> log.info("XML: {}", customer));

        // CSV
        CustomerCSVService customerCSVService = new CustomerCSVService();
        customerCSVService.writeCustomersToFile(customerList, "customers.csv");
        Optional<List<Customer>> optionalCustomerListCSV = customerCSVService.readCustomersFromFile("customers.csv");
        List<Customer> customersCSV = optionalCustomerListCSV.orElse(List.of());
        customersCSV.forEach(customer -> log.info("CSV: {}", customer));

        // JSON
        CustomerJSONService customerJSONService = new CustomerJSONService();
        customerJSONService.writeCustomersToFile(customerList, "customers.json");
        Optional<List<Customer>> optionalCustomerListJSON = customerJSONService.readCustomersFromFile("customers.json");
        List<Customer> customersJSON = optionalCustomerListJSON.orElse(List.of());
        customersJSON.forEach(customer -> log.info("JSON: {}", customer));

        CustomerExcelService customerExcelService = new CustomerExcelService();
        customerExcelService.writeCustomersToFile(customerList, "customers.xlsx");
        Optional<List<Customer>> customersExcelOptional = customerExcelService.readCustomersFromFile("customers.xlsx");
        List<Customer> customersExcel = customersExcelOptional.orElse(List.of());
        customersExcel.forEach(customer -> log.info("EXCEL: {}", customer));
    }
}
