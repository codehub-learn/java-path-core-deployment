package gr.codelearn.service;

import gr.codelearn.domain.Directory;
import lombok.extern.slf4j.Slf4j;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Slf4j
public class XMLService<T> {

    public void writeObjectToFile(List<T> entity, String filename){
        try(FileOutputStream fileOutputStream = new FileOutputStream(Directory.FILE_DIRECTORY.getPath() + filename);
            XMLEncoder encoder = new XMLEncoder(fileOutputStream)){
            encoder.setExceptionListener(e -> log.info("Exception occurred! {}", e.getMessage()));
            encoder.writeObject(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Optional<List<T>> readObjectFromFile(String filename){
        try(FileInputStream fileInputStream = new FileInputStream(Directory.FILE_DIRECTORY.getPath() + filename);
            XMLDecoder decoder = new XMLDecoder(fileInputStream)){
            List<T> entityList = (List<T>) decoder.readObject();
            return Optional.of(entityList);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return Optional.empty();
    }
}
