package br.edu.ifpb.mt.ads.dac.util.interceptors.impl;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.edu.ifpb.mt.ads.dac.util.interceptors.TransacionalCdi;

/**
 * Outra(s) implementação(ões):
 * 
 * <pre>
 * http://github.com/matzew/hbase-jpa-jsf/blob/master/src/main/java/net/wessendorf/cdi/transactional/TransactionalInterceptor.java
 * </pre>
 * 
 * (taken from the Apache OpenWebBeans project's reservation example)
 * 
 * <pre>
 * https://github.com/algaworks/curso-javaee-primefaces/blob/master/ControlandoTransacoesBeansCDI/src/main/java/com/algaworks/pedidovenda/util/jpa/TransactionInterceptor.java
 * </pre>
 * 
 * @author jaindsonvs
 */
@Interceptor
@TransacionalCdi
public class TransacionalInterceptor {

	@Inject
	private EntityManager em;

	@AroundInvoke
	public Object intercept(InvocationContext ctx) throws Exception {
		Object resultado = null;
		boolean criador = false;
		EntityTransaction trx = em.getTransaction();

		try {
			if (!trx.isActive()) {
				trx.begin();
				criador = true;
			}

			resultado = ctx.proceed();
		} catch (Exception pe) {
			pe.printStackTrace();
			if (trx.isActive() && criador) {
				// Rollback if any error happens before reaching commit
				trx.rollback();
			}
			throw pe;
		} finally {
			if (trx.isActive() && criador) {
				trx.commit();
			}
		}

		return resultado;
	}

}
