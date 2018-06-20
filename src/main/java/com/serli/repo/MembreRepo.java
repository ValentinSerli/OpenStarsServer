package com.serli.repo;

import com.serli.data.Membre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembreRepo extends CrudRepository<Membre, Integer> {
    Membre findByMail(String mail);
    Membre findByPassword(String password);
    Membre findByPasswordAndMail(String mail, String password);
}
