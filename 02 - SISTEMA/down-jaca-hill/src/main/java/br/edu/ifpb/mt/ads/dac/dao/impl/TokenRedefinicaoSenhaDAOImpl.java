package br.edu.ifpb.mt.ads.dac.dao.impl;

import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.edu.ifpb.mt.ads.dac.dao.PersistenciaDacException;
import br.edu.ifpb.mt.ads.dac.dao.TokenRedefinicaoSenhaDAO;
import br.edu.ifpb.mt.ads.dac.model.Conta;
import br.edu.ifpb.mt.ads.dac.model.TokenRedefinicaoSenha;
import br.edu.ifpb.mt.ads.dac.model.TokenRedefinicaoSenha_;

public class TokenRedefinicaoSenhaDAOImpl extends GenericoDAOImpl<TokenRedefinicaoSenha, Long> implements TokenRedefinicaoSenhaDAO {

	public TokenRedefinicaoSenhaDAOImpl() {
		super(TokenRedefinicaoSenha.class);
	}

	@Override
	public TokenRedefinicaoSenha buscarPelaConta(Conta conta) throws PersistenciaDacException {
		try {
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<TokenRedefinicaoSenha> criteriaQuery = criteriaBuilder.createQuery(TokenRedefinicaoSenha.class);
			Root<TokenRedefinicaoSenha> root = criteriaQuery.from(TokenRedefinicaoSenha.class);
			
			Predicate predicate = criteriaBuilder.equal(root.<Conta>get(TokenRedefinicaoSenha_.conta), conta);
			
			criteriaQuery.select(root);
			criteriaQuery.where(predicate);
			
			TypedQuery<TokenRedefinicaoSenha> typedQuery = entityManager.createQuery(criteriaQuery);
			
			TokenRedefinicaoSenha resultado = typedQuery.getSingleResult();
			
			return resultado;
		} catch (PersistenceException e) {
			return null;
		}
	}

	@Override
	public TokenRedefinicaoSenha buscarPeloToken(String token) throws PersistenciaDacException {
		try {
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<TokenRedefinicaoSenha> criteriaQuery = criteriaBuilder.createQuery(TokenRedefinicaoSenha.class);
			Root<TokenRedefinicaoSenha> root = criteriaQuery.from(TokenRedefinicaoSenha.class);
			
			Predicate predicate = criteriaBuilder.equal(root.<String>get(TokenRedefinicaoSenha_.token), token);
			
			criteriaQuery.select(root);
			criteriaQuery.where(predicate);
			
			TypedQuery<TokenRedefinicaoSenha> typedQuery = entityManager.createQuery(criteriaQuery);
			
			TokenRedefinicaoSenha resultado = typedQuery.getSingleResult();
			
			return resultado;
		} catch (PersistenceException e) {
			throw new PersistenciaDacException("Token não encontrado");
		}
	}

}
