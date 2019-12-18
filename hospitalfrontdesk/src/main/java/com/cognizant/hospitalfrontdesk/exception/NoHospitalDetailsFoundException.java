package com.cognizant.hospitalfrontdesk.exception;

public class NoHospitalDetailsFoundException extends Exception {

    private String hospitalName;

    public NoHospitalDetailsFoundException(String hospitalName) {
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
