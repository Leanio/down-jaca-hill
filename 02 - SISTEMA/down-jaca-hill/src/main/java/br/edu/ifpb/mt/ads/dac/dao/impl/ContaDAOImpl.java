package br.edu.ifpb.mt.ads.dac.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.edu.ifpb.mt.ads.dac.dao.ContaDAO;
import br.edu.ifpb.mt.ads.dac.dao.PersistenciaDacException;
import br.edu.ifpb.mt.ads.dac.model.Conta;
import br.edu.ifpb.mt.ads.dac.model.Conta_;

public class ContaDAOImpl extends GenericoDAOImpl<Conta, Long> implements ContaDAO {

	public ContaDAOImpl() {
		super(Conta.class);
	}

	@Override
	public Conta buscarPeloLogin(String login) throws PersistenciaDacException {
		try {
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Conta> criteriaQuery = criteriaBuilder.createQuery(Conta.class);
			Root<Conta> root = criteriaQuery.from(Conta.class);
			
			Predicate predicate = criteriaBuilder.equal(criteriaBuilder.lower(root.<String>get(Conta_.login)), login.toLowerCase());
			
			criteriaQuery.select(root);
			criteriaQuery.where(predicate);
			
			TypedQuery<Conta> typedQuery = entityManager.createQuery(criteriaQuery);
			
			Conta resultado = typedQuery.getSingleResult();
			
			return resultado;
		} catch (PersistenceException e) {
			throw new PersistenciaDacException("Conta não encontrada");
		}
	}

	@Override
	public boolean existeOutraContaComMesmoLogin(Conta conta) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Conta> root = criteriaQuery.from(Conta.class);

		Expression<Long> expression = criteriaBuilder.count(root);
		
		Predicate[] predicate = getPredicateExisteOutraContaComMesmoLogin(criteriaBuilder, root, conta);
		
		criteriaQuery.select(expression);
		criteriaQuery.where(predicate);
		
		TypedQuery<Long> typedQuery = entityManager.createQuery(criteriaQuery);
		
		Long resultado = typedQuery.getSingleResult();
		
		return resultado > 0;
	}
	
	@Override
	public boolean existeOutraContaComMesmoEmail(Conta conta) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Conta> root = criteriaQuery.from(Conta.class);

		Expression<Long> expression = criteriaBuilder.count(root);
		
		Predicate[] predicate = getPredicateExisteOutraContaComMesmoEmail(criteriaBuilder, root, conta);
		
		criteriaQuery.select(expression);
		criteriaQuery.where(predicate);
		
		TypedQuery<Long> typedQuery = entityManager.createQuery(criteriaQuery);
		
		Long resultado = typedQuery.getSingleResult();
		
		return resultado > 0;
	}
	
	private Predicate[] getPredicateExisteOutraContaComMesmoLogin(CriteriaBuilder criteriaBuilder, Root<Conta> root, Conta conta) {
		List<Predicate> predicate = new ArrayList<Predicate>();
		
		if (conta.getCodigo() != null) {
			predicate.add(criteriaBuilder.notEqual(root, conta));
		}
		
		if (conta.getLogin() != null) {
			predicate.add(criteriaBuilder.equal(criteriaBuilder.lower(root.<String>get(Conta_.login)), conta.getLogin().toLowerCase()));
		}

		return predicate.toArray(new Predicate[0]);
	}
	
	private Predicate[] getPredicateExisteOutraContaComMesmoEmail(CriteriaBuilder criteriaBuilder, Root<Conta> root, Conta conta) {
		List<Predicate> predicate = new ArrayList<Predicate>();
		
		if (conta.getCodigo() != null) {
			predicate.add(criteriaBuilder.notEqual(root, conta));
		}
		
		if (conta.getEmail() != null) {
			predicate.add(criteriaBuilder.equal(criteriaBuilder.lower(root.<String>get(Conta_.email)), conta.getEmail().toLowerCase()));
		}

		return predicate.toArray(new Predicate[0]);
	}

}
