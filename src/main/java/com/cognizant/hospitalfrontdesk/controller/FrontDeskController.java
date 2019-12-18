package com.cognizant.hospitalfrontdesk.controller;



import com.cognizant.hospitalfrontdesk.exception.NoHospitalDetailsFoundException;
import com.cognizant.hospitalfrontdesk.exception.NoSpecialistDetailsFoundForHospitalNameException;
import com.cognizant.hospitalfrontdesk.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${url.frontdeskcontroller.root}")
public class FrontDeskController {

    @Autowired
    private
    AppointmentModel appointmentModel;


    @Cacheable("specialist")
    @GetMapping(value = "${url.frontdeskcontroller.specialist}", produces =  {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?>getSpecialistDetails(@PathVariable String hospitalname,@PathVariable String type) throws NoSpecialistDetailsFoundForHospitalNameException {

        List<SpecialistModel> allSpecialist = getAllSpecialist();
        List<SpecialistModel> collect = allSpecialist.stream().filter(specialist -> specialist.getType().equalsIgnoreCase(type))
                .collect(Collectors.toList());


        if(collect.size()==0)
        throw NoSpecialistDetailsFoundForHospitalNameException.createWith("Specialist For "+hospitalname);
        return ResponseEntity.ok(collect);
    }


    @GetMapping(value="${url.frontdeskcontroller.appointment}",consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}, produces =  {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?>getNewAppointment(@RequestBody AppointmentModel appointment) throws NoSpecialistDetailsFoundForHospitalNameException {

        System.out.println(appointment.toString());

        if(appointment.getSpecialistName().equalsIgnoreCase(appointmentModel.getSpecialistName())&&appointment.getAppointmentDay().equalsIgnoreCase(appointmentModel.getAppointmentDay()))
        return ResponseEntity.ok(this.appointmentModel);

        throw NoSpecialistDetailsFoundForHospitalNameException.createWith("No Specialist Details Found");
    }

    @GetMapping(value = "${url.frontdeskcontroller.noofbeds}", produces =  {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?>getNoOfBeds(@PathVariable String hospitalName) throws NoSpecialistDetailsFoundForHospitalNameException {
        HospitalDetails hostpitalDetails = getHostpitalDetails(hospitalName);
        if(hostpitalDetails!=null)
        {
            List<PatientDetails> patientDetailsList = hostpitalDetails.getPatientDetailsList();
            long discharge = patientDetailsList.stream()
                    .filter(patientDetails -> patientDetails.getStatus().equalsIgnoreCase("DISCHARGE"))
                    .count();

            if(discharge>0)
            {
                return ResponseEntity.ok("No of Beds Available is = "+discharge);
            }

        }

        throw NoHospitalDetailsFoundException.createWith(hospitalName);

    }




    private List<SpecialistModel>getAllSpecialist()
    {
        List<SpecialistModel>specialistModels=new ArrayList<>();
        specialistModels.add(new SpecialistModel("Dentist","sandhya","Monday,Wednesday","5 to 6","Y",946));
        specialistModels.add(new SpecialistModel("Dentist","saranya","Monday,Thursday","6 to 8","N",946));

        return specialistModels;
    }



    private HospitalDetails getHostpitalDetails(String hospitalName)
    {

        HospitalDetails hospitalDetails=new HospitalDetails();

        List<PatientDetails>patientDetailsList=new ArrayList<>();

        patientDetailsList.add(new PatientDetails("Patient1","DISCHARGE"));
        patientDetailsList.add(new PatientDetails("Patient2","DISCHARGE"));
        patientDetailsList.add(new PatientDetails("Patient3","DISCHARGE"));
        patientDetailsList.add(new PatientDetails("Patient4","DISCHARGE"));
        patientDetailsList.add(new PatientDetails("Patient5","DISCHARGE"));
        patientDetailsList.add(new PatientDetails("Patient6","DISCHARGE"));

        hospitalDetails.setHostpitalName("Hospital1");
        hospitalDetails.setPatientDetailsList(patientDetailsList);


        if(hospitalName.equalsIgnoreCase(hospitalDetails.getHostpitalName()))
            return hospitalDetails;
        return null;


    }


}
