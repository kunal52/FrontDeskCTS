package com.cognizant.hospitalfrontdesk.model;

import java.util.List;

public class HospitalDetails {
    private String hostpitalName;
    private List<PatientDetails>patientDetailsList;

    public HospitalDetails() {
    }

    public HospitalDetails(String hostpitalName, List<PatientDetails> patientDetailsList) {
        this.hostpitalName = hostpitalName;
        this.patientDetailsList = patientDetailsList;
    }

    public String getHostpitalName() {
        return hostpitalName;
    }

    public void setHostpitalName(String hostpitalName) {
        this.hostpitalName = hostpitalName;
    }

    public List<PatientDetails> getPatientDetailsList() {
        return patientDetailsList;
    }

    public void setPatientDetailsList(List<PatientDetails> patientDetailsList) {
        this.patientDetailsList = patientDetailsList;
    }
}
