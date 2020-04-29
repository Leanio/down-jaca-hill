package br.edu.ifpb.mt.ads.dac.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.edu.ifpb.mt.ads.dac.dao.RecursoDAO;
import br.edu.ifpb.mt.ads.dac.filters.RecursoFilter;
import br.edu.ifpb.mt.ads.dac.model.ItemRecurso;
import br.edu.ifpb.mt.ads.dac.model.ItemRecurso_;
import br.edu.ifpb.mt.ads.dac.model.Recurso;
import br.edu.ifpb.mt.ads.dac.model.Recurso_;

public class RecursoDAOImpl extends GenericoDAOImpl<Recurso, Long> implements RecursoDAO {

	public RecursoDAOImpl() {
		super(Recurso.class);
	}

	@Override
	public List<Recurso> filtrar(RecursoFilter recursoFilter) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Recurso> criteriaQuery = criteriaBuilder.createQuery(Recurso.class);
		Root<Recurso> root = criteriaQuery.from(Recurso.class);

		Predicate[] predicate = getPredicateFilter(criteriaBuilder, root, recursoFilter);

		criteriaQuery.select(root);
		criteriaQuery.where(predicate);

		TypedQuery<Recurso> typedQuery = entityManager.createQuery(criteriaQuery);

		adicionarPaginacao(typedQuery, recursoFilter);

		List<Recurso> resultado = typedQuery.getResultList();

		return resultado;
	}

	@Override
	public boolean existeOutroRecursoComMesmoNome(Recurso recurso) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Recurso> root = criteriaQuery.from(Recurso.class);

		Expression<Long> expression = criteriaBuilder.count(root);

		Predicate[] predicate = getPredicateExisteOutroRecursoComMesmoNome(criteriaBuilder, root, recurso);

		criteriaQuery.select(expression);
		criteriaQuery.where(predicate);

		TypedQuery<Long> typedQuery = entityManager.createQuery(criteriaQuery);

		Long resultado = typedQuery.getSingleResult();

		return resultado > 0;
	}

	@Override
	public boolean existeAssociacaoComItemRecurso(Long codigo) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<ItemRecurso> root = criteriaQuery.from(ItemRecurso.class);

		Expression<Long> expression = criteriaBuilder.count(root);

		Predicate predicate = criteriaBuilder.equal(root.<Recurso>get(ItemRecurso_.recurso), codigo);

		criteriaQuery.select(expression);
		criteriaQuery.where(predicate);

		TypedQuery<Long> typedQuery = entityManager.createQuery(criteriaQuery);

		Long resultado = typedQuery.getSingleResult();

		return resultado > 0;
	}

	@Override
	public long total(RecursoFilter recursoFilter) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Recurso> root = criteriaQuery.from(Recurso.class);

		Expression<Long> expression = criteriaBuilder.count(root);

		Predicate[] predicate = getPredicateFilter(criteriaBuilder, root, recursoFilter);

		criteriaQuery.select(expression);
		criteriaQuery.where(predicate);

		TypedQuery<Long> typedQuery = entityManager.createQuery(criteriaQuery);

		Long resultado = typedQuery.getSingleResult();

		return resultado;
	}

	private Predicate[] getPredicateFilter(CriteriaBuilder criteriaBuilder, Root<Recurso> root, RecursoFilter recursoFilter) {
		List<Predicate> predicate = new ArrayList<Predicate>();

		if (recursoFilter.getNome() != null) {
			predicate.add(criteriaBuilder.like(criteriaBuilder.lower(root.<String>get(Recurso_.nome)), "%" + recursoFilter.getNome().toLowerCase() + "%"));
		}

			return predicate.toArray(new Predicate[0]);
	}

	private Predicate[] getPredicateExisteOutroRecursoComMesmoNome(CriteriaBuilder criteriaBuilder, Root<Recurso> root,	Recurso recurso) {
		List<Predicate> predicate = new ArrayList<Predicate>();

		if (recurso.getCodigo() != null) {
			predicate.add(criteriaBuilder.notEqual(root, recurso));
		}

		if (recurso.getNome() != null) {
			predicate.add(criteriaBuilder.equal(criteriaBuilder.lower(root.<String>get(Recurso_.nome)),
					recurso.getNome().toLowerCase()));
		}

		return predicate.toArray(new Predicate[0]);
	}

}
