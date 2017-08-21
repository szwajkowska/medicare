package pl.ania.domain.visits;

import pl.ania.domain.Specialization;
import pl.ania.domain.doctors.Doctor;
import pl.ania.security.User;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Visit {

    @Id
    private String id;

    private Date dateOfVisit;

    private boolean taken;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "specialization_id")
    private Specialization specialization;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Visit(String id, Date dateOfVisit, Doctor doctor, Specialization specialization) {
        this.id = id;
        this.dateOfVisit = dateOfVisit;
        this.doctor = doctor;
        this.specialization = specialization;
    }

    public Visit(String id, Date dateOfVisit, Doctor doctor, boolean taken, User user, Specialization specialization) {
        this.id = id;
        this.dateOfVisit = dateOfVisit;
        this.doctor = doctor;
        this.taken = taken;
        this.user = user;
        this.specialization = specialization;
    }

    public Visit(){}

    public String getId() {
        return id;
    }

    public Date getDateOfVisit() {
        return dateOfVisit;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public boolean isTaken() {
        return taken;
    }

    public User getUser() {
        return user;
    }

    public Specialization getSpecialization() {
        return specialization;
    }
}
