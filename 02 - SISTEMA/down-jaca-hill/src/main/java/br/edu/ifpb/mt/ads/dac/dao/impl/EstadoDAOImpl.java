package br.edu.ifpb.mt.ads.dac.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.edu.ifpb.mt.ads.dac.dao.EstadoDAO;
import br.edu.ifpb.mt.ads.dac.filters.EstadoFilter;
import br.edu.ifpb.mt.ads.dac.model.Cidade;
import br.edu.ifpb.mt.ads.dac.model.Cidade_;
import br.edu.ifpb.mt.ads.dac.model.Estado;
import br.edu.ifpb.mt.ads.dac.model.Estado_;

public class EstadoDAOImpl extends GenericoDAOImpl<Estado, Long> implements EstadoDAO {

	public EstadoDAOImpl() {
		super(Estado.class);
	}

	@Override
	public List<Estado> filtrar(EstadoFilter estadoFilter) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Estado> criteriaQuery = criteriaBuilder.createQuery(Estado.class);
		Root<Estado> root = criteriaQuery.from(Estado.class);
		
		Predicate[] predicate = getPredicateFilter(criteriaBuilder, root, estadoFilter);
		
		criteriaQuery.select(root);
		criteriaQuery.where(predicate);
		
		TypedQuery<Estado> typedQuery = entityManager.createQuery(criteriaQuery);
		
		adicionarPaginacao(typedQuery, estadoFilter);
		
		List<Estado> resultado = typedQuery.getResultList();
		
		return resultado;
	}

	@Override
	public boolean existeOutroEstadoComMesmoNome(Estado estado) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Estado> root = criteriaQuery.from(Estado.class);

		Expression<Long> expression = criteriaBuilder.count(root);
		
		Predicate[] predicate = getPredicateExisteOutroEstadoComMesmoNome(criteriaBuilder, root, estado);
		
		criteriaQuery.select(expression);
		criteriaQuery.where(predicate);
		
		TypedQuery<Long> typedQuery = entityManager.createQuery(criteriaQuery);
		
		Long resultado = typedQuery.getSingleResult();
		
		return resultado > 0;
	}
	
	@Override
	public boolean existeAssociacaoComCidade(Long codigo) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Cidade> root = criteriaQuery.from(Cidade.class);
		
		Expression<Long> expression = criteriaBuilder.count(root);
		
		Predicate predicate = criteriaBuilder.equal(root.<Estado>get(Cidade_.estado), codigo);
		
		criteriaQuery.select(expression);
		criteriaQuery.where(predicate);
		
		TypedQuery<Long> typedQuery = entityManager.createQuery(criteriaQuery);
		
		Long resultado = typedQuery.getSingleResult();
		
		return resultado > 0;
	}
	
	@Override
	public long total(EstadoFilter estadoFilter) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Estado> root = criteriaQuery.from(Estado.class);
		
		Predicate[] predicate = getPredicateFilter(criteriaBuilder, root, estadoFilter);
		
		Expression<Long> expression = criteriaBuilder.count(root);
		
		criteriaQuery.select(expression);
		criteriaQuery.where(predicate);
		
		TypedQuery<Long> typedQuery = entityManager.createQuery(criteriaQuery);
		
		Long resultado = typedQuery.getSingleResult();
		
		return resultado;
	}
	
	private Predicate[] getPredicateFilter(CriteriaBuilder criteriaBuilder, Root<Estado> root, EstadoFilter estadoFilter) {
		List<Predicate> predicate = new ArrayList<Predicate>();
		
		if (estadoFilter.getNome() != null) {
			predicate.add(criteriaBuilder.like(criteriaBuilder.lower(root.<String>get(Estado_.nome)), "%" + estadoFilter.getNome().toLowerCase() + "%"));
		}
		
		return predicate.toArray(new Predicate[0]);
	}
	
	private Predicate[] getPredicateExisteOutroEstadoComMesmoNome(CriteriaBuilder criteriaBuilder, Root<Estado> root, Estado estado) {
		List<Predicate> predicate = new ArrayList<Predicate>();
		
		if (estado.getCodigo() != null) {
			predicate.add(criteriaBuilder.notEqual(root, estado));
		}
		
		if (estado.getNome() != null) {
			predicate.add(criteriaBuilder.equal(criteriaBuilder.lower(root.<String>get(Estado_.nome)), estado.getNome().toLowerCase()));
		}
		
		return predicate.toArray(new Predicate[0]);
	}
	
}
