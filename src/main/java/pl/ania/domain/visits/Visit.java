package pl.ania.domain.visits;

import pl.ania.domain.doctors.Doctor;
import javax.persistence.*;
import java.util.Date;

@Entity
public class Visit {

    @Id
    private String id;

    private Date dateOfVisit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    public Visit(){
    }


    public Visit(String id, Date dateOfVisit, Doctor doctor) {
        this.id = id;
        this.dateOfVisit = dateOfVisit;
        this.doctor = doctor;
    }

    public String getId() {
        return id;
    }

    public Date getDateOfVisit() {
        return dateOfVisit;
    }

    public Doctor getDoctor() {
        return doctor;
    }
}
