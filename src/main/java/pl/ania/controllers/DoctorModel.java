package pl.ania.controllers;

import pl.ania.domain.Specialization;

import java.util.List;

public class DoctorModel {

    private String firstName;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    private String lastName;

    private String specializationId;



    public String getFirstName() {
        return firstName;
    }



    public String getLastName() {
        return lastName;
    }

    public String getSpecializationId() {
        return specializationId;
    }

    public void setSpecializationId(String specializationId) {
        this.specializationId = specializationId;
    }

}
