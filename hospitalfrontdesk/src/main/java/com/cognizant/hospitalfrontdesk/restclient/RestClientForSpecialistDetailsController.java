package com.cognizant.hospitalfrontdesk.restclient;

import com.cognizant.hospitalfrontdesk.model.SpecialistModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("rest")
public class RestClientForSpecialistDetailsController {

    @Autowired
    private
    RestTemplate restTemplate;

    @Value("${restclient.specialistdetails.uri}")
    private
    String specilistDetailsUri;

    @GetMapping("specialistdetails")
    public ResponseEntity<?>getRestForSpecialistDetails()
    {
        ResponseEntity<SpecialistModel[]> forEntity = restTemplate.getForEntity(specilistDetailsUri, SpecialistModel[].class);
        SpecialistModel[] body = forEntity.getBody();
        MediaType contentType = forEntity.getHeaders().getContentType();
        HttpStatus statusCode = forEntity.getStatusCode();

        return ResponseEntity.ok(body);
    }


}
