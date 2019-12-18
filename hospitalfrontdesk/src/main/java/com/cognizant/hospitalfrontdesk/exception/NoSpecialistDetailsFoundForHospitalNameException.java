package com.cognizant.hospitalfrontdesk.exception;


public class NoSpecialistDetailsFoundForHospitalNameException extends Exception {

    private String hospitalName;

    public NoSpecialistDetailsFoundForHospitalNameException(String hospitalName) {
        this.hospitalName=hospitalName;
    }

    public static NoSpecialistDetailsFoundForHospitalNameException createWith(String hospitalName)
    {
        return new NoSpecialistDetailsFoundForHospitalNameException(hospitalName);
    }

    @Override
    public String getMessage() {
        return "Hospital Name "+hospitalName+" Not Found";
    }
}
