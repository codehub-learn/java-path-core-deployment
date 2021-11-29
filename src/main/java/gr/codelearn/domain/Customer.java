package gr.codelearn.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer implements Serializable {
    private static final long serialVersionUID = 3;

    private String name;
    private String gender;
    private int yearOfBirth;
    private transient String address;
    private Date dateOfRegistration;

    private void writeObject(ObjectOutputStream outputStream) throws IOException {
        this.gender += "Encrypted";
        outputStream.defaultWriteObject();
    }

    private void readObject(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        inputStream.defaultReadObject();
        this.gender = this.gender.substring(0,1);
    }
}
