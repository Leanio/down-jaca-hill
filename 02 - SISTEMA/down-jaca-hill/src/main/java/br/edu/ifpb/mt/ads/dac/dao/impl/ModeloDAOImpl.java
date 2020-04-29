package br.edu.ifpb.mt.ads.dac.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.edu.ifpb.mt.ads.dac.dao.ModeloDAO;
import br.edu.ifpb.mt.ads.dac.filters.ModeloFilter;
import br.edu.ifpb.mt.ads.dac.model.Marca;
import br.edu.ifpb.mt.ads.dac.model.Modelo;
import br.edu.ifpb.mt.ads.dac.model.Modelo_;
import br.edu.ifpb.mt.ads.dac.model.PedidoParticipacao;
import br.edu.ifpb.mt.ads.dac.model.PedidoParticipacao_;

public class ModeloDAOImpl extends GenericoDAOImpl<Modelo, Long> implements ModeloDAO {

	public ModeloDAOImpl() {
		super(Modelo.class);
	}

	@Override
	public List<Modelo> filtrar(ModeloFilter modeloFilter) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Modelo> criteriaQuery = criteriaBuilder.createQuery(Modelo.class);
		Root<Modelo> root = criteriaQuery.from(Modelo.class);
		
		Predicate[] predicate = getPredicateFilter(criteriaBuilder, root, modeloFilter);
		
		criteriaQuery.select(root);
		criteriaQuery.where(predicate);
		
		TypedQuery<Modelo> typedQuery = entityManager.createQuery(criteriaQuery);
		
		adicionarPaginacao(typedQuery, modeloFilter);
		
		List<Modelo> resultado = typedQuery.getResultList();
		
		return resultado;
	}

	@Override
	public boolean existeOutroModeloComMesmoNomeEMarca(Modelo modelo) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Modelo> root = criteriaQuery.from(Modelo.class);
		
		Expression<Long> expression = criteriaBuilder.count(root);
		
		Predicate[] predicate = getPredicateExisteOutroModeloComMesmoNomeEMarca(criteriaBuilder, root, modelo);
		
		criteriaQuery.select(expression);
		criteriaQuery.where(predicate);
		
		TypedQuery<Long> typedQuery = entityManager.createQuery(criteriaQuery);
		
		Long resultado = typedQuery.getSingleResult();
		
		return resultado > 0;
	}
	
	@Override
	public boolean existeAssociacaoComPedidoParticipacao(Long codigo) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<PedidoParticipacao> root = criteriaQuery.from(PedidoParticipacao.class);
		
		Expression<Long> expression = criteriaBuilder.count(root);
		
		Predicate predicate = criteriaBuilder.equal(root.<Modelo>get(PedidoParticipacao_.modelo), codigo);
		
		criteriaQuery.select(expression);
		criteriaQuery.where(predicate);
		
		TypedQuery<Long> typedQuery = entityManager.createQuery(criteriaQuery);
		
		Long resultado = typedQuery.getSingleResult();
		
		return resultado > 0;
	}

	@Override
	public long total(ModeloFilter modeloFilter) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Modelo> root = criteriaQuery.from(Modelo.class);
		
		Expression<Long> expression = criteriaBuilder.count(root);
		
		Predicate[] predicate = getPredicateFilter(criteriaBuilder, root, modeloFilter);
		
		criteriaQuery.select(expression);
		criteriaQuery.where(predicate);
		
		TypedQuery<Long> typedQuery = entityManager.createQuery(criteriaQuery);
		
		Long resultado = typedQuery.getSingleResult();
		
		return resultado;
	}
	
	private Predicate[] getPredicateFilter(CriteriaBuilder criteriaBuilder, Root<Modelo> root, ModeloFilter modeloFilter) {
		List<Predicate> predicate = new ArrayList<Predicate>();
		
		if (modeloFilter.getNome() != null) {
			predicate.add(criteriaBuilder.like(criteriaBuilder.lower(root.<String>get(Modelo_.nome)), "%" + modeloFilter.getNome().toLowerCase() + "%"));
		}
		
		if (modeloFilter.getMarca() != null) {
			predicate.add(criteriaBuilder.equal(root.<Marca>get(Modelo_.marca), modeloFilter.getMarca()));
		}
		
		return predicate.toArray(new Predicate[0]);
	}
	
	private Predicate[] getPredicateExisteOutroModeloComMesmoNomeEMarca(CriteriaBuilder criteriaBuilder, Root<Modelo> root, Modelo modelo) {
		List<Predicate> predicate = new ArrayList<Predicate>();
		
		if (modelo.getCodigo() != null) {
			predicate.add(criteriaBuilder.notEqual(root, modelo));
		}
		
		if (modelo.getNome() != null) {
			predicate.add(criteriaBuilder.equal(criteriaBuilder.lower(root.<String>get(Modelo_.nome)), modelo.getNome().toLowerCase()));
		}
		
		if (modelo.getMarca() != null) {
			predicate.add(criteriaBuilder.equal(root.<Marca>get(Modelo_.marca), modelo.getMarca()));
		}
		
		return predicate.toArray(new Predicate[0]);
	}

}
