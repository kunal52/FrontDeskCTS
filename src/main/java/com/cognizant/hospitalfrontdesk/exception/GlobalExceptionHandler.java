package com.cognizant.hospitalfrontdesk.exception;

import com.cognizant.hospitalfrontdesk.model.ApiErrors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Collections;
import java.util.List;


@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler({
            NoSpecialistDetailsFoundForHospitalNameException.class,
            NoHospitalDetailsFoundException.class
    })
    public final ResponseEntity<?> handleException(Exception e, WebRequest request)
    {
        HttpHeaders headers=new HttpHeaders();

        if(e instanceof NoSpecialistDetailsFoundForHospitalNameException)
        {
            HttpStatus status = HttpStatus.NOT_FOUND;
            NoSpecialistDetailsFoundForHospitalNameException hospitalNameException= (NoSpecialistDetailsFoundForHospitalNameException) e;
            return noSpecialistDetailsFoundForHospitalName(hospitalNameException, headers, status, request);
        }

        if(e instanceof NoHospitalDetailsFoundException)
        {
            HttpStatus status = HttpStatus.NOT_FOUND;
            NoHospitalDetailsFoundException hospitalNameException= (NoHospitalDetailsFoundException) e;
            return noHospitalNameFoundException(hospitalNameException, headers, status, request);
        }

        return ResponseEntity.notFound().build();
    }


    private ResponseEntity<ApiErrors>noSpecialistDetailsFoundForHospitalName(NoSpecialistDetailsFoundForHospitalNameException ex,
                                                                             HttpHeaders headers,
                                                                             HttpStatus status,
                                                                             WebRequest request)
    {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return handleExceptionInternal(ex, new ApiErrors(errors), headers, status, request);
    }

    private ResponseEntity<ApiErrors>noHospitalNameFoundException(NoHospitalDetailsFoundException ex,
                                                                             HttpHeaders headers,
                                                                             HttpStatus status,
                                                                             WebRequest request)
    {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return handleExceptionInternal(ex, new ApiErrors(errors), headers, status, request);
    }


    private ResponseEntity<ApiErrors> handleExceptionInternal(Exception ex, @Nullable ApiErrors body,
                                                              HttpHeaders headers, HttpStatus status,
                                                              WebRequest request)
    {
        return new ResponseEntity<>(body, headers, status);
    }





}
