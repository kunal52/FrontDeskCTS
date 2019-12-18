package com.cognizant.hospitalfrontdesk.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.context.annotation.Configuration;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "appointment")
@ConfigurationProperties(prefix = "appointment1")
public class AppointmentModel {

    private String specialistName;
    private String patientName;
    private String appointmentDay;
    private String appointmentTime;

    public AppointmentModel() {
    }

    public AppointmentModel(String specialistName, String patientName, String appointmentDay, String appointmentTime) {
        this.specialistName = specialistName;
        this.patientName = patientName;
        this.appointmentDay = appointmentDay;
        this.appointmentTime = appointmentTime;
    }

    public String getSpecialistName() {
        return specialistName;
    }

    public void setSpecialistName(String specialistName) {
        this.specialistName = specialistName;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getAppointmentDay() {
        return appointmentDay;
    }

    public void setAppointmentDay(String appointmentDay) {
        this.appointmentDay = appointmentDay;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    @Override
    public String toString() {
        return "AppointmentModel{" +
                "specialistName='" + specialistName + '\'' +
                ", patientName='" + patientName + '\'' +
                ", appointmentDay='" + appointmentDay + '\'' +
                ", appointmentTime='" + appointmentTime + '\'' +
                '}';
    }
}
