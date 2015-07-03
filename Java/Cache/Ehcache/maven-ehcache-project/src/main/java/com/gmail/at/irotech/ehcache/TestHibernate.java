package com.gmail.at.irotech.ehcache;

import com.gmail.at.irotech.ehcache.beans.entities.CacheEntity;
import com.gmail.at.irotech.ehcache.utils.HibernateUtils;
import org.hibernate.Session;

public class TestHibernate {

    public static void main(String ... args) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        CacheEntity entity = new CacheEntity();
        entity.setId(1L);
        entity.setName("John");
        entity.setSurname("Doe");
        entity.setEmail("john.doe@foo.com");
        session.save(entity);
        session.getTransaction().commit();
        HibernateUtils.shutdown();
    }

}
