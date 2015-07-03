package com.gmail.at.irotech.ehcache.services;

import com.gmail.at.irotech.ehcache.beans.vos.CacheVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;

import java.util.Arrays;

public class CacheService implements ICacheService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Cacheable(value = "cacheVOValue", key = "#id")
    @Override
    public CacheVO getTheVOValue(long id) {

        logger.info("Cache calculation the object's value ..");

        CacheVO cacheVO = new CacheVO();

        cacheVO.setId(id);
        cacheVO.setString("String");
        cacheVO.setList(Arrays.asList("abc", "def", "ghk"));

        logger.info("Cached object created.");

        return cacheVO;
    }

}
