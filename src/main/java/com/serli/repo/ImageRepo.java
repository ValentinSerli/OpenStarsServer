package com.serli.repo;

import com.serli.data.Image;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepo extends CrudRepository<Image,Integer> {
    Image findFirstByNomPlanete(String nomPlanete);
}
