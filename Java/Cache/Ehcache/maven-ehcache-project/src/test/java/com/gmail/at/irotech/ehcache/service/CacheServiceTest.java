package com.gmail.at.irotech.ehcache.service;

import com.gmail.at.irotech.ehcache.services.CacheService;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Status;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/junit-persistence-context.xml")
public class CacheServiceTest {

    @Autowired
    private SessionFactory sessionFactory;
    private Session session;

    private CacheService cacheService;
    private CacheManager cacheManager;

    @Before
    public void setUp() {
        session = sessionFactory.getCurrentSession();
    }

    @After
    public void tearDown() {
        if(null != cacheManager) {
            if(cacheManager.getStatus().equals(Status.STATUS_ALIVE)) {
                assertTrue(CacheManager.ALL_CACHE_MANAGERS.contains(cacheManager));
            }
            cacheManager.shutdown();
            assertFalse(CacheManager.ALL_CACHE_MANAGERS.contains(cacheManager));
        }
    }

}
