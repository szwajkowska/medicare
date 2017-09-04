package pl.ania.controllers;

import java.util.Date;

public class VisitResponse {

    private final String id;

    private final Date date;

    public VisitResponse(String id, Date date) {
        this.id = id;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }
}
