package pl.ania.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "specializations")
public class Specialization {

    @Id
    private String id;

    private String specializationName;

    @PersistenceConstructor
    public Specialization(String id, String specializationName) {
        this.id = id;
        this.specializationName = specializationName;
    }

    public Specialization(){}

    public Specialization(String specializationName) {
        this.specializationName = specializationName;
    }

    public String getSpecializationName() {
        return specializationName;
    }

    public String getId() {
        return id;
    }

}
