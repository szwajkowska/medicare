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

    private Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "specialization_id")
    private Specialization specialization;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Visit(String id, Date date, Doctor doctor, Specialization specialization) {
        this.id = id;
        this.date = date;
        this.doctor = doctor;
        this.specialization = specialization;
    }

    public Visit(String id, Date date, Doctor doctor, User user, Specialization specialization) {
        this.id = id;
        this.date = date;
        this.doctor = doctor;
        this.user = user;
        this.specialization = specialization;
    }

    public Visit(){}

    public String getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public User getUser() {
        return user;
    }

    public Specialization getSpecialization() {
        return specialization;
    }
}
