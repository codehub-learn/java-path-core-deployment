package gr.codelearn.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OnlineCustomer extends Customer{
    private String username;

    private void writeObject(ObjectOutputStream outputStream) throws IOException {
        throw new NotSerializableException("This object cannot be serialised.");
    }

    private void readObject(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        throw new NotSerializableException("This object cannot be deserialized.");
    }
}
