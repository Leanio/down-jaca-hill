package br.edu.ifpb.mt.ads.dac.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.edu.ifpb.mt.ads.dac.dao.CiclistaDAO;
import br.edu.ifpb.mt.ads.dac.dao.PersistenciaDacException;
import br.edu.ifpb.mt.ads.dac.model.Ciclista;
import br.edu.ifpb.mt.ads.dac.model.Ciclista_;
import br.edu.ifpb.mt.ads.dac.model.Conta;
import br.edu.ifpb.mt.ads.dac.model.Conta_;
import br.edu.ifpb.mt.ads.dac.model.Participacao;
import br.edu.ifpb.mt.ads.dac.model.Participacao_;
import br.edu.ifpb.mt.ads.dac.model.PedidoParticipacao;
import br.edu.ifpb.mt.ads.dac.model.PedidoParticipacao_;
import br.edu.ifpb.mt.ads.dac.model.Pessoa;
import br.edu.ifpb.mt.ads.dac.model.Pessoa_;
import br.edu.ifpb.mt.ads.dac.model.Premiacao;
import br.edu.ifpb.mt.ads.dac.model.Premiacao_;

public class CiclistaDAOImpl extends GenericoDAOImpl<Ciclista, Long> implements CiclistaDAO {

	public CiclistaDAOImpl() {
		super(Ciclista.class);
	}

	@Override
	public List<Object[]> ranking() throws PersistenciaDacException {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
		Root<Premiacao> root = criteriaQuery.from(Premiacao.class);
		
		Path<Participacao> participacao = root.<Participacao>get(Premiacao_.participacao);
		Path<PedidoParticipacao> pedidoParticipacao = participacao.<PedidoParticipacao>get(Participacao_.pedidoParticipacao);
		Path<Ciclista> ciclista = pedidoParticipacao.<Ciclista>get(PedidoParticipacao_.ciclista);
		Path<Conta> conta = ciclista.<Pessoa>get(Ciclista_.pessoa).<Conta>get(Pessoa_.conta);
		Path<String> login = conta.<String>get(Conta_.login);
		
		Expression<Long> count = criteriaBuilder.count(root);
		
		Order order = criteriaBuilder.desc(count);
		
		Predicate predicate = criteriaBuilder.equal(root.<Integer>get(Premiacao_.colocacao), 1);
		
		criteriaQuery.multiselect(login, count); 
		criteriaQuery.where(predicate);
		criteriaQuery.orderBy(order); 
		criteriaQuery.groupBy(login);
		
		TypedQuery<Object[]> typedQuery = entityManager.createQuery(criteriaQuery);
		
		List<Object[]> resultado = typedQuery.getResultList();
		
		return resultado;
	}

}
