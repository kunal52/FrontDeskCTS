package com.cognizant.hospitalfrontdesk.model;

public class PatientDetails {
    private String name;
    private String status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public PatientDetails(String name, String status) {
        this.name = name;
        this.status = status;
    }
}
