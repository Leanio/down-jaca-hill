package br.edu.ifpb.mt.ads.dac.dao.impl;

import static br.edu.ifpb.mt.ads.dac.util.dashboard.Util.gerarListaMes;
import static br.edu.ifpb.mt.ads.dac.util.dashboard.Util.preencherResultado;

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

import br.edu.ifpb.mt.ads.dac.dao.PagamentoDAO;
import br.edu.ifpb.mt.ads.dac.filters.PagamentoFilter;
import br.edu.ifpb.mt.ads.dac.model.Evento;
import br.edu.ifpb.mt.ads.dac.model.Evento_;
import br.edu.ifpb.mt.ads.dac.model.Organizador;
import br.edu.ifpb.mt.ads.dac.model.Pagamento;
import br.edu.ifpb.mt.ads.dac.model.Pagamento_;
import br.edu.ifpb.mt.ads.dac.model.PedidoParticipacao;
import br.edu.ifpb.mt.ads.dac.model.PedidoParticipacao_;
import br.edu.ifpb.mt.ads.dac.util.dashboard.ResultadoDashboard;

public class PagamentoDAOImpl extends GenericoDAOImpl<Pagamento, Long> implements PagamentoDAO {

	public PagamentoDAOImpl() {
		super(Pagamento.class);
	}
	
	@Override
	public List<ResultadoDashboard> ganhosPagamentoMensal(PagamentoFilter pagamentoFilter) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
		Root<Pagamento> root = criteriaQuery.from(Pagamento.class);
		
		Path<Date> pathDataPagamento = root.<Date>get(Pagamento_.dataPagamento);
		Path<Float> pathTaxa = root.<PedidoParticipacao>get(Pagamento_.pedidoParticipacao).<Float>get(PedidoParticipacao_.taxaParticipacao);
		
		Expression<String> mes = criteriaBuilder.function("month", String.class, pathDataPagamento); 
		Expression<Float> expression = criteriaBuilder.sum(pathTaxa);
		
		Predicate[] predicate = getPredicateFilter(criteriaBuilder, root, pagamentoFilter);
		
		criteriaQuery.multiselect(mes, expression);
		criteriaQuery.where(predicate);
		criteriaQuery.groupBy(mes);
		
		TypedQuery<Object[]> typedQuery = entityManager.createQuery(criteriaQuery);
		
		List<Object[]> resultado = typedQuery.getResultList();
		List<ResultadoDashboard> lista = gerarListaMes(pagamentoFilter.getDataPagamentoDe(), pagamentoFilter.getDataPagamentoAte());
		
		preencherResultado(resultado, lista);
		
		return lista;
	}

	private Predicate[] getPredicateFilter(CriteriaBuilder criteriaBuilder, Root<Pagamento> root, PagamentoFilter pagamentoFilter) {
		List<Predicate> predicate = new ArrayList<Predicate>();
		
		if (pagamentoFilter.getDataPagamentoDe() != null) {
			predicate.add(criteriaBuilder.greaterThanOrEqualTo(root.<Date>get(Pagamento_.dataPagamento), pagamentoFilter.getDataPagamentoDe()));
		}
		
		if (pagamentoFilter.getDataPagamentoAte() != null) {
			predicate.add(criteriaBuilder.lessThanOrEqualTo(root.<Date>get(Pagamento_.dataPagamento), pagamentoFilter.getDataPagamentoAte()));	
		}
		
		Path<Evento> pathEvento = root.<PedidoParticipacao>get(Pagamento_.pedidoParticipacao).<Evento>get(PedidoParticipacao_.evento);
		
		if (pagamentoFilter.getEvento() != null) {
			predicate.add(criteriaBuilder.equal(pathEvento, pagamentoFilter.getEvento()));
		}
		
		if (pagamentoFilter.getOrganizador() != null) {
			predicate.add(criteriaBuilder.equal(pathEvento.<Organizador>get(Evento_.organizador), pagamentoFilter.getOrganizador()));
		}
		
		return predicate.toArray(new Predicate[0]);
	}
	
}
