package guckflix.crawlservice.repository;

import guckflix.crawlservice.entity.Credit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class CreditRepository {

    @Autowired
    EntityManager em;

    public void save(Credit credit){
        em.persist(credit);
    }
}
