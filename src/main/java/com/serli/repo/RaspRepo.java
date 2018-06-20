package com.serli.repo;

import com.serli.data.Membre;
import com.serli.data.Rasp;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaspRepo extends CrudRepository<Rasp, String> {

}
