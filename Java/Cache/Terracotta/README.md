Terracotta

server
	conf: vcandelaRepo\Terracotta-3.6.2\bin\tc-config.xml

<project>/src/main/config/ehcache.xml
	<terracottaConfig url="${terracotta.config.location}" rejoin="true" /> [VM -Dterracotta.config.location=localhost:9510]

persistence-context.xml
	...
	<bean id="sessionFactory"
		class="org.springframework.orm.hibernate3.LocalSessionFactoryBean">
		...
		<property name="hibernateProperties">
			<props>
				...
				<prop key="hibernate.cache.region.factory_class">net.sf.ehcache.hibernate.EhCacheRegionFactory</prop>
				<prop key="hibernate.cache.use_query_cache">true</prop>
				<prop key="hibernate.cache.use_second_level_cache">true</prop>
				...
				<prop key="net.sf.ehcache.configurationResourceName">ehcache.xml</prop>	

services-context.xml
	...
	<cache:annotation-driven cache-manager="springCacheManager"/>
    <bean id="springCacheManager" class="org.springframework.cache.ehcache.EhCacheCacheManager" p:cacheManager-ref="springEhcache" />
    <bean id="springEhcache" class="org.springframework.cache.ehcache.EhCacheManagerFactoryBean" p:configLocation="classpath:ehcache.xml" p:shared="true" />

aop-context.xml
	...
	<bean id="persitentEntityCacheAspect" class="uk.co.wowcher.entitycache.aspect.PersistentEntityCacheAspect">
		<property name="sessionFactory" ref="sessionFactory"/>
		<property name="config" ref="threadLocalCacheConfig"/>
	</bean>
	
	<bean id="threadLocalCacheConfig" class="uk.co.wowcher.entitycache.config.ThreadLocalCacheConfig"/>

