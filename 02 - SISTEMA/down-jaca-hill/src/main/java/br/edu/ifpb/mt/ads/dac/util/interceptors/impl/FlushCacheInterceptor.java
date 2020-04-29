package br.edu.ifpb.mt.ads.dac.util.interceptors.impl;

import java.security.Principal;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.jboss.security.CacheableManager;

import br.edu.ifpb.mt.ads.dac.util.interceptors.FlushCacheCdi;

@Interceptor
@FlushCacheCdi
public class FlushCacheInterceptor {

	@Inject
    private Principal principal;
	
	@Resource(name = "java:jboss/jaas/dacJdbcRealm/authenticationMgr")
    private CacheableManager<?, Principal> authenticationManager;
	
	@AroundInvoke
	public Object intercept(InvocationContext ctx) throws Exception {
		Object obj = ctx.proceed();
		authenticationManager.flushCache(principal);
		return obj;
	}
}
