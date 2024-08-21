package com.dans.jobs.service;

import com.dans.jobs.entity.AttanceModel;
import com.dans.jobs.entity.User;
import com.dans.jobs.repository.AbsensiRepository;
import com.dans.jobs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Service
public class AbsensiService {

    @Autowired
    AbsensiRepository absensiRepository;
    @Autowired
    UserRepository userRepository;

    public AttanceModel insertAbsensi(AttanceModel request) throws Exception {
        Optional<User> isexist = userRepository.findByNip(request.getNip());
        request.setWaktu(LocalTime.now().toString());
        request.setTanggal(LocalDate.now().toString());
        if(isexist.isEmpty()){
            throw new Exception("user doesn't exist, try again");
        }
        return absensiRepository.save(request);
    }
}
