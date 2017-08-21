package pl.ania.controllers;

import java.util.Date;

public class VisitResponse {

    private final String id;

    private final Date dateOfVisit;

    public VisitResponse(String id, Date dateOfVisit) {
        this.id = id;
        this.dateOfVisit = dateOfVisit;
    }

    public String getId() {
        return id;
    }

    public Date getDateOfVisit() {
        return dateOfVisit;
    }
}
