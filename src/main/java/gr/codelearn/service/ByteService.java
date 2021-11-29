package gr.codelearn.service;

import gr.codelearn.domain.Directory;

import java.io.*;
import java.util.Optional;

public class ByteService<T> {

    public void writeObjectToFile(T entity, String filename){
        try(FileOutputStream fileOutputStream = new FileOutputStream(Directory.FILE_DIRECTORY.getPath() + filename);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)){
            objectOutputStream.writeObject(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Optional<T> readObjectFromFile(String filename){
        try(FileInputStream fileInputStream = new FileInputStream(Directory.FILE_DIRECTORY.getPath() + filename);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)){
            T entity = null;
            while(true) {
                try {
                    entity = (T) objectInputStream.readObject();
                }
                catch (EOFException e){
                    break;
                }
            }
            return Optional.of(entity);
        } catch (IOException | ClassNotFoundException ioException) {
            ioException.printStackTrace();
        }
        return Optional.empty();
    }
}
