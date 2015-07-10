package com.gmail.at.irotech.ehcache.service;

import com.gmail.at.irotech.ehcache.beans.vos.CacheVO;
import com.gmail.at.irotech.ehcache.services.CacheService;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Status;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/junit-persistence-context.xml")
public class CacheServiceTest {

    private final String TEST_CONFIG_DIR = "src/test/resources/";

    @Autowired
    private SessionFactory sessionFactory;
    private Session session;

    @Autowired
    private CacheService cacheService;

    @Resource(name = "cacheManager")
    private CacheManager cacheManager;

    @Before
    public void setUp() {
        session = sessionFactory.getCurrentSession();
//        cacheManager = CacheManager.create(TEST_CONFIG_DIR + "junit-ehcache.xml");
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

    @Test
    public void testGetTheVOValue() {
        CacheVO firstCall = cacheService.getTheVOValue(1L);
        assertEquals(firstCall, cacheService.getTheVOValue(1L));
    }

}
