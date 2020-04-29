package br.edu.ifpb.mt.ads.dac.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.edu.ifpb.mt.ads.dac.dao.ParticipacaoDAO;
import br.edu.ifpb.mt.ads.dac.dao.PersistenciaDacException;
import br.edu.ifpb.mt.ads.dac.filters.ParticipacaoFilter;
import br.edu.ifpb.mt.ads.dac.model.Ciclista;
import br.edu.ifpb.mt.ads.dac.model.Evento;
import br.edu.ifpb.mt.ads.dac.model.Evento_;
import br.edu.ifpb.mt.ads.dac.model.Organizador;
import br.edu.ifpb.mt.ads.dac.model.Participacao;
import br.edu.ifpb.mt.ads.dac.model.Participacao_;
import br.edu.ifpb.mt.ads.dac.model.PedidoParticipacao;
import br.edu.ifpb.mt.ads.dac.model.PedidoParticipacao_;

public class ParticipacaoDAOImpl extends GenericoDAOImpl<Participacao, Long> implements ParticipacaoDAO {

	public ParticipacaoDAOImpl() {
		super(Participacao.class);
	}

	@Override
	public List<Participacao> filtrar(ParticipacaoFilter participacaoFilter) throws PersistenciaDacException {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Participacao> criteriaQuery = criteriaBuilder.createQuery(Participacao.class);
		Root<Participacao> root = criteriaQuery.from(Participacao.class);
		
		Predicate[] predicate = getPredicateFilter(criteriaBuilder, root, participacaoFilter);
		
		criteriaQuery.select(root);
		criteriaQuery.where(predicate);
		
		TypedQuery<Participacao> typedQuery = entityManager.createQuery(criteriaQuery);
		
		adicionarPaginacao(typedQuery, participacaoFilter);
		
		List<Participacao> resultado = typedQuery.getResultList();
		
		return resultado;
	}
	
	@Override
	public long total(ParticipacaoFilter participacaoFilter) throws PersistenciaDacException {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Participacao> root = criteriaQuery.from(Participacao.class);
		
		Predicate[] predicate = getPredicateFilter(criteriaBuilder, root, participacaoFilter);
		
		Expression<Long> expression = criteriaBuilder.count(root);
		
		criteriaQuery.select(expression);
		criteriaQuery.where(predicate);
		
		TypedQuery<Long> typedQuery = entityManager.createQuery(criteriaQuery);
		
		Long resultado = typedQuery.getSingleResult();
		
		return resultado;
	}
	
	@Override
	public List<Participacao> listaOrdenadaPeloTempoGasto(Evento evento) throws PersistenciaDacException {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Participacao> criteriaQuery = criteriaBuilder.createQuery(Participacao.class);
		Root<Participacao> root = criteriaQuery.from(Participacao.class);
		
		Predicate predicate = criteriaBuilder.equal(root.<PedidoParticipacao>get(Participacao_.pedidoParticipacao).<Evento>get(PedidoParticipacao_.evento), evento);
		
		Order order = criteriaBuilder.asc(root.<Date>get(Participacao_.tempoGasto));
		
		criteriaQuery.select(root);
		criteriaQuery.where(predicate);
		criteriaQuery.orderBy(order);
		
		TypedQuery<Participacao> typedQuery = entityManager.createQuery(criteriaQuery);
		
		typedQuery.setMaxResults(evento.getPremiacao().size());
		
		List<Participacao> resultado = typedQuery.getResultList();
		
		return resultado;
	}
	
	private Predicate[] getPredicateFilter(CriteriaBuilder criteriaBuilder, Root<Participacao> root, ParticipacaoFilter participacaoFilter) {
		List<Predicate> predicate = new ArrayList<Predicate>();
		
		Path<PedidoParticipacao> pathPedidoParticipacao = root.<PedidoParticipacao>get(Participacao_.pedidoParticipacao);
		Path<Evento> pathEvento = pathPedidoParticipacao.<Evento>get(PedidoParticipacao_.evento);
		
		if (participacaoFilter.getCiclista() != null) {
			predicate.add(criteriaBuilder.equal(pathPedidoParticipacao.<Ciclista>get(PedidoParticipacao_.ciclista), participacaoFilter.getCiclista()));
		}
		
		if (participacaoFilter.getOrganizador() != null) {
			predicate.add(criteriaBuilder.equal(pathEvento.<Organizador>get(Evento_.organizador), participacaoFilter.getOrganizador()));
		}
		
		if (participacaoFilter.getEvento() != null ) {
			predicate.add(criteriaBuilder.equal(pathEvento, participacaoFilter.getEvento()));
		}
		
		return predicate.toArray(new Predicate[0]);
	}


}
