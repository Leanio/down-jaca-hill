package br.edu.ifpb.mt.ads.dac.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.edu.ifpb.mt.ads.dac.dao.CidadeDAO;
import br.edu.ifpb.mt.ads.dac.filters.CidadeFilter;
import br.edu.ifpb.mt.ads.dac.model.Cidade;
import br.edu.ifpb.mt.ads.dac.model.Cidade_;
import br.edu.ifpb.mt.ads.dac.model.Endereco;
import br.edu.ifpb.mt.ads.dac.model.Endereco_;
import br.edu.ifpb.mt.ads.dac.model.Estado;

public class CidadeDAOImpl extends GenericoDAOImpl<Cidade, Long> implements CidadeDAO {

	public CidadeDAOImpl() {
		super(Cidade.class);
	}
	
	@Override
	public List<Cidade> filtrar(CidadeFilter cidadeFilter) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Cidade> criteriaQuery = criteriaBuilder.createQuery(Cidade.class);
		Root<Cidade> root = criteriaQuery.from(Cidade.class);
		
		Predicate[] predicate = getPredicateFilter(criteriaBuilder, root, cidadeFilter);
		
		criteriaQuery.select(root);
		criteriaQuery.where(predicate);
		
		TypedQuery<Cidade> typedQuery = entityManager.createQuery(criteriaQuery);
		
		adicionarPaginacao(typedQuery, cidadeFilter);
		
		List<Cidade> resultado = typedQuery.getResultList();
		
		return resultado;
	}

	@Override
	public boolean existeOutraCidadeComMesmoNomeEEstado(Cidade cidade) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Cidade> root = criteriaQuery.from(Cidade.class);
		
		Expression<Long> expression = criteriaBuilder.count(root);
		
		Predicate[] predicate = getPredicateExisteOutraCidadeComMesmoNomeEEstado(criteriaBuilder, root, cidade);
		
		criteriaQuery.select(expression);
		criteriaQuery.where(predicate);
		
		TypedQuery<Long> typedQuery = entityManager.createQuery(criteriaQuery);
		
		Long resultado = typedQuery.getSingleResult();
		
		return resultado > 0;
	}
	
	@Override
	public boolean existeAssociacaoComEndereco(Long codigo) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Endereco> root = criteriaQuery.from(Endereco.class);
		
		Expression<Long> expression = criteriaBuilder.count(root);
		
		Predicate predicate = criteriaBuilder.equal(root.<Cidade>get(Endereco_.cidade), codigo);
		
		criteriaQuery.select(expression);
		criteriaQuery.where(predicate);
		
		TypedQuery<Long> typedQuery = entityManager.createQuery(criteriaQuery);
		
		Long resultado = typedQuery.getSingleResult();
		
		return resultado > 0;
	}

	@Override
	public long total(CidadeFilter cidadeFilter) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Cidade> root = criteriaQuery.from(Cidade.class);
		
		Expression<Long> expression = criteriaBuilder.count(root);
		
		Predicate[] predicate = getPredicateFilter(criteriaBuilder, root, cidadeFilter);
		
		criteriaQuery.select(expression);
		criteriaQuery.where(predicate);
		
		TypedQuery<Long> typedQuery = entityManager.createQuery(criteriaQuery);
	
		Long resultado = typedQuery.getSingleResult();
		
		return resultado;
	}
	
	private Predicate[] getPredicateFilter(CriteriaBuilder criteriaBuilder, Root<Cidade> root, CidadeFilter cidadeFilter) {
		List<Predicate> predicate = new ArrayList<Predicate>();
		
		if (cidadeFilter.getNome() != null) {
			predicate.add(criteriaBuilder.like(criteriaBuilder.lower(root.<String>get(Cidade_.nome)), "%" + cidadeFilter.getNome().toLowerCase() + "%"));
		}
		
		if (cidadeFilter.getIsLiberadaEvento() != null) {
			predicate.add(criteriaBuilder.equal(root.<Boolean>get(Cidade_.isLiberadaEvento), cidadeFilter.getIsLiberadaEvento()));
		}
		
		if (cidadeFilter.getEstado() != null) {
			predicate.add(criteriaBuilder.equal(root.<Estado>get(Cidade_.estado), cidadeFilter.getEstado()));
		}
		
		return predicate.toArray(new Predicate[0]);
	}
	
	private Predicate[] getPredicateExisteOutraCidadeComMesmoNomeEEstado(CriteriaBuilder criteriaBuilder, Root<Cidade> root, Cidade cidade) {
		List<Predicate> predicate = new ArrayList<Predicate>();
		
		if (cidade.getCodigo() != null) {
			predicate.add(criteriaBuilder.notEqual(root, cidade));
		}
		
		if (cidade.getNome() != null) {
			predicate.add(criteriaBuilder.equal(criteriaBuilder.lower(root.<String>get(Cidade_.nome)), cidade.getNome().toLowerCase()));
		}
		
		if (cidade.getEstado() != null) {
			predicate.add(criteriaBuilder.equal(root.<Estado>get(Cidade_.estado), cidade.getEstado()));
		}
		
		return predicate.toArray(new Predicate[0]);
	}

}
