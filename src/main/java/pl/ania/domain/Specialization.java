package pl.ania.domain;

import org.hibernate.annotations.Cascade;
import pl.ania.domain.doctors.Doctor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Specialization {

    @Id
    private String id;
    private String specializationName;

    @ManyToMany(mappedBy = "specializations")
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    private List<Doctor> doctors;

    public Specialization(String id, String specializationName) {
        this.id = id;
        this.specializationName = specializationName;
    }

    public Specialization() {
    }

    public Specialization(String specializationName) {
        this.specializationName = specializationName;
    }

    public String getId() {
        return id;
    }

    public String getSpecializationName() {
        return specializationName;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }
}
