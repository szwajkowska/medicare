package pl.ania.domain.doctors;

import pl.ania.domain.Specialization;
import pl.ania.domain.visits.Visit;
import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<Visit> visits;

    public Doctor() {
    }

    public Doctor(String firstName, String lastName, String id, List<Specialization> specializations) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.specializations = specializations;
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

    public List<Visit> getVisits() {
        return visits;
    }
}
