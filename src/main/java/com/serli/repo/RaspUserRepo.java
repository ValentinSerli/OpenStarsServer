package com.serli.repo;

import com.serli.data.Rasp;
import com.serli.data.RaspUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaspUserRepo extends CrudRepository<RaspUser, Integer> {
//    RaspUser findByRaspId(Integer id);
    RaspUser findByRaspIdMembre(Integer id);
}
