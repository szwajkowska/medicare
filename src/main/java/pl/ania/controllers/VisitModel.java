package pl.ania.controllers;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class VisitModel {

    @NotNull
    private Date dateOfVisit;

    @NotBlank
    private String doctorId;

    public Date getDateOfVisit() {
        return dateOfVisit;
    }

//    public void setDateOfVisit(String dateOfVisit) throws ParseException {
//
//
//            this.dateOfVisit = new SimpleDateFormat("YYYY-MM-dd'T'HH:mm").parse(dateOfVisit);
//
//    }


    public void setDateOfVisit(Date dateOfVisit) {
        this.dateOfVisit = dateOfVisit;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }
}
