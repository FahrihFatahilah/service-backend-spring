package com.dans.jobs.controller;

import com.dans.jobs.entity.JobsResponse;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@RequestMapping("/jobs")
@RestController
public class JobsController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private RestTemplate restTemplate;

    @Value("${url.recruitment}")
    private String urlRecruitment;

    @Value("${url.details.recruitment}")
    private String urlDetails;

    @GetMapping("/getList")
    @Operation(description = "Endpoint to get all list jobs")
    public ResponseEntity<?> getJobs(){
        logger.info(">>> incoming get list jobs");
        ResponseEntity<JobsResponse[]> responseEntity = restTemplate.getForEntity(urlRecruitment, JobsResponse[].class);
        logger.info(">>> incoming get list jobs response {} ", responseEntity);


        return responseEntity;

    }

    @GetMapping("/getJobDetails/{id}")
    @Operation(description = "Endpoint to detail jobs using /{jobId}")
    public ResponseEntity<?> getJobs(@PathVariable String id){
        logger.info(">>> incoming detail jobs");
        ResponseEntity<JobsResponse> responseEntity = restTemplate.getForEntity(urlDetails+id, JobsResponse.class);
        logger.info(">>> incoming detail jobs response {} ",responseEntity);


        return responseEntity;

    }

}
