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

import br.edu.ifpb.mt.ads.dac.dao.GrupoDAO;
import br.edu.ifpb.mt.ads.dac.dao.PersistenciaDacException;
import br.edu.ifpb.mt.ads.dac.model.Grupo;
import br.edu.ifpb.mt.ads.dac.model.Grupo_;

public class GrupoDAOImpl extends GenericoDAOImpl<Grupo, Long> implements GrupoDAO {

	public GrupoDAOImpl() {
		super(Grupo.class);
	}
	
	@Override
	public Grupo buscarPeloNome(String nome) throws PersistenciaDacException {
		try {
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Grupo> criteriaQuery = criteriaBuilder.createQuery(Grupo.class);
			Root<Grupo> root = criteriaQuery.from(Grupo.class);
			
			Predicate predicate = criteriaBuilder.equal(criteriaBuilder.lower(root.<String>get(Grupo_.nome)), nome.toLowerCase());
			
			criteriaQuery.select(root);
			criteriaQuery.where(predicate);
			
			TypedQuery<Grupo> typedQuery = entityManager.createQuery(criteriaQuery);
			
			Grupo resultado = typedQuery.getSingleResult();
			
			return resultado;
		} catch (PersistenceException e) {
			throw new PersistenciaDacException("Grupo não encontrado");
		}
	}

	@Override
	public boolean existeOutroGrupoComMesmoNome(Grupo grupo) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Grupo> root = criteriaQuery.from(Grupo.class);

		Expression<Long> expression = criteriaBuilder.count(root);
		
		Predicate[] predicate = getPredicateExisteOutroGrupoComMesmoNome(criteriaBuilder, root, grupo);
		
		criteriaQuery.select(expression);
		criteriaQuery.where(predicate);
		
		TypedQuery<Long> typedQuery = entityManager.createQuery(criteriaQuery);
		
		Long resultado = typedQuery.getSingleResult();
		
		return resultado > 0;
	}
	
	private Predicate[] getPredicateExisteOutroGrupoComMesmoNome(CriteriaBuilder criteriaBuilder, Root<Grupo> root, Grupo grupo) {
		List<Predicate> predicate = new ArrayList<Predicate>();
		
		if (grupo.getCodigo() != null) {
			predicate.add(criteriaBuilder.notEqual(root, grupo));
		}
		
		if (grupo.getNome() != null) {
			predicate.add(criteriaBuilder.equal(criteriaBuilder.lower(root.<String>get(Grupo_.nome)), grupo.getNome().toLowerCase()));
		}

		return predicate.toArray(new Predicate[0]);
	}

}
