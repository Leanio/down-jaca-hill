package br.edu.ifpb.mt.ads.dac.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.edu.ifpb.mt.ads.dac.dao.EventoDAO;
import br.edu.ifpb.mt.ads.dac.filters.EventoFilter;
import br.edu.ifpb.mt.ads.dac.model.Cidade;
import br.edu.ifpb.mt.ads.dac.model.Cidade_;
import br.edu.ifpb.mt.ads.dac.model.Endereco;
import br.edu.ifpb.mt.ads.dac.model.Endereco_;
import br.edu.ifpb.mt.ads.dac.model.Estado;
import br.edu.ifpb.mt.ads.dac.model.Evento;
import br.edu.ifpb.mt.ads.dac.model.Evento_;
import br.edu.ifpb.mt.ads.dac.model.Organizador;
import br.edu.ifpb.mt.ads.dac.model.PedidoParticipacao;
import br.edu.ifpb.mt.ads.dac.model.PedidoParticipacao_;
import br.edu.ifpb.mt.ads.dac.model.enumerations.EstadoEvento;

public class EventoDAOImpl extends GenericoDAOImpl<Evento, Long> implements EventoDAO {

	public EventoDAOImpl() {
		super(Evento.class);
	}

	@Override
	public List<Evento> filtrar(EventoFilter eventoFilter) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Evento> criteriaQuery = criteriaBuilder.createQuery(Evento.class);
		Root<Evento> root = criteriaQuery.from(Evento.class);

		Predicate[] predicate = getPredicateFilter(criteriaBuilder, root, eventoFilter);

		criteriaQuery.select(root);
		criteriaQuery.where(predicate);

		TypedQuery<Evento> typedQuery = entityManager.createQuery(criteriaQuery);
		
		adicionarPaginacao(typedQuery, eventoFilter);
		
		List<Evento> resultado = typedQuery.getResultList();

		return resultado;
	}
	

	@Override
	public boolean eventoPossuiVagaDisponivel(Evento evento) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Integer> criteriaQuery = criteriaBuilder.createQuery(Integer.class);
		Root<Evento> root = criteriaQuery.from(Evento.class);
		
		Path<Integer> quantidadeVagas = root.<Integer>get(Evento_.quantidadeVagas);
		
		Predicate predicate = criteriaBuilder.equal(root, evento);
		
		criteriaQuery.select(quantidadeVagas);
		criteriaQuery.where(predicate);
		
		TypedQuery<Integer> typedQuery = entityManager.createQuery(criteriaQuery);
		
		Integer resultado = typedQuery.getSingleResult();
		
		return resultado > 0;
	}
	
	@Override
	public boolean existeAssociacaoComPedidoParticipacao(Long codigo) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<PedidoParticipacao> root = criteriaQuery.from(PedidoParticipacao.class);
		
		Expression<Long> expression = criteriaBuilder.count(root);
		
		Predicate predicate = criteriaBuilder.equal(root.<Evento>get(PedidoParticipacao_.evento), codigo);
		
		criteriaQuery.select(expression);
		criteriaQuery.where(predicate);
		
		TypedQuery<Long> typedQuery = entityManager.createQuery(criteriaQuery);
		
		Long resultado = typedQuery.getSingleResult();
		
		return resultado > 0;
	}
	
	@Override
	public long total(EventoFilter eventoFilter) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Evento> root = criteriaQuery.from(Evento.class);

		Expression<Long> expression = criteriaBuilder.count(root);
		
		Predicate[] predicate = getPredicateFilter(criteriaBuilder, root, eventoFilter);

		criteriaQuery.select(expression);
		criteriaQuery.where(predicate);

		TypedQuery<Long> typedQuery = entityManager.createQuery(criteriaQuery);
		
		Long resultado = typedQuery.getSingleResult();

		return resultado;
	}
	
	private Predicate[] getPredicateFilter(CriteriaBuilder criteriaBuilder, Root<Evento> root, EventoFilter eventoFilter) {
		List<Predicate> predicate = new ArrayList<Predicate>();

		if (eventoFilter.getTitulo() != null) {
			predicate.add(criteriaBuilder.like(criteriaBuilder.lower(root.<String>get(Evento_.titulo)), "%" + eventoFilter.getTitulo().toLowerCase() + "%"));
		}
		
		if (eventoFilter.getQuantidadeVagasMinima() != null) {
			predicate.add(criteriaBuilder.greaterThanOrEqualTo(root.<Integer>get(Evento_.quantidadeVagas), eventoFilter.getQuantidadeVagasMinima()));
		}
		
		if (eventoFilter.getDataEventoDe() != null) {
			predicate.add(criteriaBuilder.greaterThanOrEqualTo(root.<Date>get(Evento_.dataEvento), eventoFilter.getDataEventoDe()));
		}
		
		if (eventoFilter.getDataEventoAte() != null) {
			predicate.add(criteriaBuilder.lessThanOrEqualTo(root.<Date>get(Evento_.dataEvento), eventoFilter.getDataEventoAte()));
		}
		
		if (eventoFilter.getTaxaParticipacaoDe() != null) {
			predicate.add(criteriaBuilder.greaterThanOrEqualTo(root.<Float>get(Evento_.taxaParticipacao), eventoFilter.getTaxaParticipacaoDe()));
		}
		
		if (eventoFilter.getTaxaParticipacaoAte() != null) {
			predicate.add(criteriaBuilder.lessThanOrEqualTo(root.<Float>get(Evento_.taxaParticipacao), eventoFilter.getTaxaParticipacaoAte()));
		}
//		
//		if (eventoFilter.getGratis() != null) {
//			if (eventoFilter.getGratis() == true) {
//				predicate.add(criteriaBuilder.isNull(root.<Float>get(Evento_.taxaParticipacao)));
//			}
//			
//			if (eventoFilter.getGratis() == false) {
//				predicate.add(criteriaBuilder.isNotNull(root.<Float>get(Evento_.taxaParticipacao)));
//			}
//		}
		
		if (eventoFilter.getEstadoEvento() != null) {
			predicate.add(criteriaBuilder.equal(root.<EstadoEvento>get(Evento_.estadoEvento), eventoFilter.getEstadoEvento()));
		}
		
		if (eventoFilter.getEstado() != null) {
			predicate.add(criteriaBuilder.equal(root.<Endereco>get(Evento_.enderecoEvento).<Cidade>get(Endereco_.cidade).<Estado>get(Cidade_.estado), eventoFilter.getEstado()));
		}
		
		if (eventoFilter.getCidade() != null) {
			predicate.add(criteriaBuilder.equal(root.<Endereco>get(Evento_.enderecoEvento).<Cidade>get(Endereco_.cidade), eventoFilter.getCidade()));
		}
		
		if (eventoFilter.getOrganizador() != null) {
			predicate.add(criteriaBuilder.equal(root.<Organizador>get(Evento_.organizador), eventoFilter.getOrganizador()));
		}

		return predicate.toArray(new Predicate[0]);
	}
	
}
