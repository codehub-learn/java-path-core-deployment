package gr.codelearn.service;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import gr.codelearn.domain.Customer;
import gr.codelearn.domain.Directory;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CustomerJSONService {

    public void writeCustomersToFile(List<Customer> customers, String filename) {
        try(FileWriter fileOutputStream = new FileWriter(Directory.FILE_DIRECTORY.getPath() + filename)){
            Gson gson = new Gson();
            String customersJson = gson.toJson(customers);
            fileOutputStream.write(customersJson);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public Optional<List<Customer>> readCustomersFromFile(String filename) {
        try(FileReader fileReader = new FileReader(Directory.FILE_DIRECTORY.getPath() + filename);
            JsonReader jsonReader = new JsonReader(fileReader)){
            Gson gson = new Gson();
            Customer[] customerArray = gson.fromJson(jsonReader, Customer[].class);
            return Optional.of(Arrays.asList(customerArray));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return Optional.empty();
    }
}
