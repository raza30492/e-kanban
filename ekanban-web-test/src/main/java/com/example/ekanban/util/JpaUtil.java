package com.example.ekanban.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by mdzahidraza on 03/06/17.
 */
public class JpaUtil {

    private static EntityManager entityManager;

    public static EntityManager getEntityManager() {
        if (entityManager == null) {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
            entityManager = emf.createEntityManager();
        }
        return entityManager;
    }
}
