package br.edu.ifpb.mt.ads.dac.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.edu.ifpb.mt.ads.dac.dao.MarcaDAO;
import br.edu.ifpb.mt.ads.dac.filters.MarcaFilter;
import br.edu.ifpb.mt.ads.dac.model.Marca;
import br.edu.ifpb.mt.ads.dac.model.Marca_;
import br.edu.ifpb.mt.ads.dac.model.Modelo;
import br.edu.ifpb.mt.ads.dac.model.Modelo_;

public class MarcaDAOImpl extends GenericoDAOImpl<Marca, Long> implements MarcaDAO {

	public MarcaDAOImpl() {
		super(Marca.class);
	}

	@Override
	public List<Marca> filtrar(MarcaFilter marcaFilter) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Marca> criteriaQuery = criteriaBuilder.createQuery(Marca.class);
		Root<Marca> root = criteriaQuery.from(Marca.class);
		
		Predicate[] predicate = getPredicateFilter(criteriaBuilder, root, marcaFilter);
		
		criteriaQuery.select(root);
		criteriaQuery.where(predicate);
		
		TypedQuery<Marca> typedQuery = entityManager.createQuery(criteriaQuery);
		
		adicionarPaginacao(typedQuery, marcaFilter);
		
		List<Marca> resultado = typedQuery.getResultList();
		
		return resultado;
	}

	@Override
	public boolean existeOutraMarcaComMesmoNome(Marca marca) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Marca> root = criteriaQuery.from(Marca.class);
		
		Expression<Long> expression = criteriaBuilder.count(root);
		
		Predicate[] predicate = getPredicateExisteOutraMarcaComMesmoNome(criteriaBuilder, root, marca);
		
		criteriaQuery.select(expression);
		criteriaQuery.where(predicate);
		
		TypedQuery<Long> typedQuery = entityManager.createQuery(criteriaQuery);
		
		Long resultado = typedQuery.getSingleResult();
		
		return resultado > 0;
	}
	
	@Override
	public boolean existeAssociacaoComModelo(Long codigo) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Modelo> root = criteriaQuery.from(Modelo.class);
		
		Expression<Long> expression = criteriaBuilder.count(root);
		
		Predicate predicate = criteriaBuilder.equal(root.<Marca>get(Modelo_.marca), codigo);
		
		criteriaQuery.select(expression);
		criteriaQuery.where(predicate);
		
		TypedQuery<Long> typedQuery = entityManager.createQuery(criteriaQuery);
		
		Long resultado = typedQuery.getSingleResult();
		
		return resultado > 0;
	}
	
	@Override
	public long total(MarcaFilter marcaFilter) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Marca> root = criteriaQuery.from(Marca.class);
		
		Expression<Long> expression = criteriaBuilder.count(root);
		
		Predicate[] predicate = getPredicateFilter(criteriaBuilder, root, marcaFilter);
		
		criteriaQuery.select(expression);
		criteriaQuery.where(predicate);
		
		TypedQuery<Long> typedQuery = entityManager.createQuery(criteriaQuery);
		
		Long resultado = typedQuery.getSingleResult();
		
		return resultado;
	}
	
	private Predicate[] getPredicateFilter(CriteriaBuilder criteriaBuilder, Root<Marca> root, MarcaFilter marcaFilter) {
		List<Predicate> predicate = new ArrayList<Predicate>();
		
		if (marcaFilter.getNome() != null) {
			predicate.add(criteriaBuilder.like(criteriaBuilder.lower(root.<String>get(Marca_.nome)), "%" + marcaFilter.getNome().toLowerCase() + "%"));
		}
		
		return predicate.toArray(new Predicate[0]);
	}
	
	private Predicate[] getPredicateExisteOutraMarcaComMesmoNome(CriteriaBuilder criteriaBuilder, Root<Marca> root, Marca marca) {
		List<Predicate> predicate = new ArrayList<Predicate>();
		
		if (marca.getCodigo() != null) {
			predicate.add(criteriaBuilder.notEqual(root, marca));
		}
		
		if (marca.getNome() != null) {
			predicate.add(criteriaBuilder.equal(criteriaBuilder.lower(root.<String>get(Marca_.nome)), marca.getNome().toLowerCase()));
		}
		
		return predicate.toArray(new Predicate[0]);
	}

}
