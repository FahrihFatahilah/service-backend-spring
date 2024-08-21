package com.dans.jobs.repository;

import com.dans.jobs.entity.AttanceModel;
import com.dans.jobs.entity.AttandaceResponse;
import com.dans.jobs.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AbsensiRepository extends CrudRepository<AttanceModel, Integer> {
    Optional<AttanceModel> findByNip(String nip);


}
