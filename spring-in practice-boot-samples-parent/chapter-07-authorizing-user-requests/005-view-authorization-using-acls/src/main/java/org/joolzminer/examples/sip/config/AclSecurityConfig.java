package org.joolzminer.examples.sip.config;

import javax.sql.DataSource;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.acls.AclPermissionEvaluator;
import org.springframework.security.acls.domain.AclAuthorizationStrategy;
import org.springframework.security.acls.domain.AclAuthorizationStrategyImpl;
import org.springframework.security.acls.domain.ConsoleAuditLogger;
import org.springframework.security.acls.domain.DefaultPermissionGrantingStrategy;
import org.springframework.security.acls.domain.EhCacheBasedAclCache;
import org.springframework.security.acls.jdbc.BasicLookupStrategy;
import org.springframework.security.acls.jdbc.JdbcMutableAclService;
import org.springframework.security.acls.jdbc.LookupStrategy;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.security.acls.model.PermissionGrantingStrategy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Configuration
public class AclSecurityConfig {

	@Autowired
    private DataSource dataSource;
		
    @Bean
    public CacheManager cacheManager() {
    	CacheManager cacheManager = CacheManager.create();
    	cacheManager.addCache(new Cache("aclCache", 500, false, false, 30,  30));
    	return cacheManager;
    }
	
    @Bean
    public EhCacheBasedAclCache ehCacheBasedAclCache() {    	
    	return new EhCacheBasedAclCache(cacheManager().getCache("aclCache"), permissionGrantingStrategy(), aclAuthorizationStrategy());    	
    }
    
    @Bean
    public AclAuthorizationStrategy aclAuthorizationStrategy() {
    	return new AclAuthorizationStrategyImpl(new SimpleGrantedAuthority("ROLE_ADMIN"));    	
    }
    
    @Bean
    public PermissionGrantingStrategy permissionGrantingStrategy() {
    	return new DefaultPermissionGrantingStrategy(new ConsoleAuditLogger());
    }

    
    @Bean
    LookupStrategy lookupStrategy() {
    	return new BasicLookupStrategy(dataSource, ehCacheBasedAclCache(), aclAuthorizationStrategy(), permissionGrantingStrategy());
    }
    
    @Bean
    public MutableAclService mutableAclService() {
    	return new JdbcMutableAclService(dataSource, lookupStrategy(), ehCacheBasedAclCache());
    }
    
    @Bean
    public PermissionEvaluator permissionEvaluator() {
    	return new AclPermissionEvaluator(mutableAclService());
    }
    
    @Bean
    public MethodSecurityExpressionHandler methodSecurityExpressionHandler() {
    	DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
    	expressionHandler.setPermissionEvaluator(permissionEvaluator());
    	return expressionHandler;
    }

}


