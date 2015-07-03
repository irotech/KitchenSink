package com.gmail.at.irotech.ehcache.utils;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class CacheUtils {

    @Autowired
    CacheManager cacheManager;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void removeCacheElement(Serializable id) {
        if(null != cacheManager) {
            logger.info("Removing the elements in cache for business id {} ", id);
            try {
                removeCacheElement(id, cacheManager.getCache(Constants.CacheConstants.CACHE_TEST_OBJ_NAME));
                logger.info("Removed.");
            } catch (Exception e) {
                logger.info("Error clearing the cache for Id: {}, Error is {} ", id, e);
            }
        }
    }

    private void removeCacheElement(Serializable id, Cache cacheToBeInvalidate) {
        if(null != cacheToBeInvalidate) {
            for(Object key : cacheToBeInvalidate.getKeys()) {
                if(key.toString().startsWith(id.toString())) {
                    cacheToBeInvalidate.remove(key);
                }
            }
        }
    }

}
