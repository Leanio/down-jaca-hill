package br.edu.ifpb.mt.ads.dac.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.edu.ifpb.mt.ads.dac.dao.OrganizadorDAO;
import br.edu.ifpb.mt.ads.dac.model.Organizador;
import br.edu.ifpb.mt.ads.dac.model.Organizador_;

public class OrganizadorDAOImpl extends GenericoDAOImpl<Organizador, Long> implements OrganizadorDAO {

	public OrganizadorDAOImpl() {
		super(Organizador.class);
	}

	@Override
	public boolean existeOutroOrganizadorComMesmoRg(Organizador organizador) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Organizador> root = criteriaQuery.from(Organizador.class);

		Expression<Long> expression = criteriaBuilder.count(root);
		
		Predicate[] predicate = getPredicateExisteOutroOrganizadorComMesmoRg(criteriaBuilder, root, organizador);
		
		criteriaQuery.select(expression);
		criteriaQuery.where(predicate);
		
		TypedQuery<Long> typedQuery = entityManager.createQuery(criteriaQuery);
		
		Long resultado = typedQuery.getSingleResult();
		
		return resultado > 0;
	}
	
	private Predicate[] getPredicateExisteOutroOrganizadorComMesmoRg(CriteriaBuilder criteriaBuilder, Root<Organizador> root, Organizador organizador) {
		List<Predicate> predicate = new ArrayList<Predicate>();
		
		if (organizador.getCodigo() != null) {
			predicate.add(criteriaBuilder.notEqual(root, organizador));
		}
		
		if (organizador.getRg() != null) {
			predicate.add(criteriaBuilder.equal(criteriaBuilder.lower(root.<String>get(Organizador_.rg)), organizador.getRg().toLowerCase()));
		}

		return predicate.toArray(new Predicate[0]);
	}

}
