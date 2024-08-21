package com.dans.jobs.controller;

import com.dans.jobs.entity.AttanceModel;
import com.dans.jobs.entity.AttandaceResponse;
import com.dans.jobs.entity.RegisterUser;
import com.dans.jobs.entity.User;
import com.dans.jobs.service.AbsensiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/absensi")
@RestController
public class AbsensiController {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AbsensiService absensiService;


    @PostMapping("/insertAbsensi")
    public ResponseEntity<AttandaceResponse> register(@RequestBody AttanceModel request) throws Exception {
        logger.info(">>> incoming get list jobs");
        AttandaceResponse resp = new AttandaceResponse();

        try{
            absensiService.insertAbsensi(request);
            resp.setCode(HttpStatus.OK.value());
            resp.setStatus("success");
            resp.setMessage("Insert success");
            return ResponseEntity.ok(resp);
        }catch (Throwable t){
            resp.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setStatus("failed");
            resp.setMessage("Insert Failed");
            throw t;
        }

    }
}
