package com.gmail.at.irotech.ehcache.utils;

import com.gmail.at.irotech.ehcache.services.CacheService;
import net.sf.ehcache.CacheException;
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

import javax.transaction.Transactional;

import static org.junit.Assert.*;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/junit-persistence-context.xml")
public class CacheUtilsTest {

    @Autowired
    private SessionFactory sessionFactory;
    private Session session;

    private CacheManager cacheManager;

    private final int CACHES_IN_EHCACHE_XML = 1;
    private final String TEST_CONFIG_DIR = "src/test/resources/";

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

    @Test
    public void shouldExistASessionFactoryTest() {
        assertNotNull(session);
    }

    @Test
    public void CreateCacheManager() {
        cacheManager = CacheManager.create();
        cacheManager.getEhcache("");
        assertNotNull(cacheManager);
        assertEquals(CACHES_IN_EHCACHE_XML, cacheManager.getCacheNames().length);
    }

    @Test
    public void testCreateCacheManagerFromFile() throws CacheException {
        cacheManager = CacheManager.create(TEST_CONFIG_DIR + "junit-ehcache.xml");
        assertNotNull(cacheManager);
        assertEquals(1, cacheManager.getCacheNames().length);
    }

}
