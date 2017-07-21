package pl.ania.domain.doctors;

import org.hibernate.annotations.*;
import pl.ania.domain.Specialization;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.List;

@Entity
public class Doctor {

    @Id
    private String id;

    private String firstName;

    private String lastName;

    @ManyToMany
    @JoinTable(name = "doctor_specialization",
            joinColumns = @JoinColumn(name = "doctor_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "specialization_id", referencedColumnName = "id"))
    private List<Specialization> specializations;

    public Doctor(String firstName, String lastName, String id, List<Specialization> specializations) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.specializations = specializations;
    }

    public Doctor() {
    }

    public List<Specialization> getSpecializations() {
        return specializations;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getId() {
        return id;
    }
}
