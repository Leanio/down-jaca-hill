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

import br.edu.ifpb.mt.ads.dac.dao.PedidoParticipacaoDAO;
import br.edu.ifpb.mt.ads.dac.dao.PersistenciaDacException;
import br.edu.ifpb.mt.ads.dac.filters.PedidoParticipacaoFilter;
import br.edu.ifpb.mt.ads.dac.model.Ciclista;
import br.edu.ifpb.mt.ads.dac.model.Evento;
import br.edu.ifpb.mt.ads.dac.model.Evento_;
import br.edu.ifpb.mt.ads.dac.model.Organizador;
import br.edu.ifpb.mt.ads.dac.model.Pagamento;
import br.edu.ifpb.mt.ads.dac.model.Participacao;
import br.edu.ifpb.mt.ads.dac.model.PedidoParticipacao;
import br.edu.ifpb.mt.ads.dac.model.PedidoParticipacao_;
import br.edu.ifpb.mt.ads.dac.model.enumerations.EstadoPedido;
import br.edu.ifpb.mt.ads.dac.util.dashboard.ResultadoDashboard;

import static br.edu.ifpb.mt.ads.dac.util.dashboard.Util.gerarListaDia;
import static br.edu.ifpb.mt.ads.dac.util.dashboard.Util.preencherResultado;

public class PedidoParticipacaoDAOImpl extends GenericoDAOImpl<PedidoParticipacao, Long> implements PedidoParticipacaoDAO {

	public PedidoParticipacaoDAOImpl() {
		super(PedidoParticipacao.class);
	}

	@Override
	public List<PedidoParticipacao> filtrar(PedidoParticipacaoFilter pedidoParticipacaoFilter) throws PersistenciaDacException {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<PedidoParticipacao> criteriaQuery = criteriaBuilder.createQuery(PedidoParticipacao.class);
		Root<PedidoParticipacao> root = criteriaQuery.from(PedidoParticipacao.class);
		
		Predicate[] predicate = getPredicateFilter(criteriaBuilder, root, pedidoParticipacaoFilter);
		
		criteriaQuery.select(root);
		criteriaQuery.where(predicate);
		
		TypedQuery<PedidoParticipacao> typedQuery = entityManager.createQuery(criteriaQuery);
		
		adicionarPaginacao(typedQuery, pedidoParticipacaoFilter);
		
		List<PedidoParticipacao> resultado = typedQuery.getResultList();
		
		return resultado;
	}
	
	@Override
	public long total(PedidoParticipacaoFilter pedidoParticipacaoFilter) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<PedidoParticipacao> root = criteriaQuery.from(PedidoParticipacao.class);
		
		Expression<Long> expression = criteriaBuilder.count(root);
		
		Predicate[] predicate = getPredicateFilter(criteriaBuilder, root, pedidoParticipacaoFilter);
		
		criteriaQuery.select(expression);
		criteriaQuery.where(predicate);
		
		TypedQuery<Long> typedQuery = entityManager.createQuery(criteriaQuery);
		
		Long resultado = typedQuery.getSingleResult();
		
		return resultado;
	}
	
	@Override
	public List<ResultadoDashboard> totalPedidosAvaliadosData(PedidoParticipacaoFilter pedidoParticipacaoFilter) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
		Root<PedidoParticipacao> root = criteriaQuery.from(PedidoParticipacao.class);
		
		Path<Date> pathDataAtualizacaoEstado = root.<Date>get(PedidoParticipacao_.dataAtualizacaoEstado);
		
		Expression<Long> expression = criteriaBuilder.count(root);
		
		Predicate[] predicate = getPredicateFilter(criteriaBuilder, root, pedidoParticipacaoFilter);
		
		criteriaQuery.multiselect(pathDataAtualizacaoEstado, expression);
		criteriaQuery.where(predicate);
		criteriaQuery.groupBy(pathDataAtualizacaoEstado);
		
		TypedQuery<Object[]> typedQuery = entityManager.createQuery(criteriaQuery);
		
		List<Object[]> resultado = typedQuery.getResultList();
		
		List<ResultadoDashboard> lista = gerarListaDia(pedidoParticipacaoFilter.getDataAtualizacaoEstadoDe(), pedidoParticipacaoFilter.getDataAtualizacaoEstadoAte());
		
		preencherResultado(resultado, lista);
		
		return lista;
	}
	
	@Override
	public List<ResultadoDashboard> totalPedidosData(PedidoParticipacaoFilter pedidoParticipacaoFilter) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
		Root<PedidoParticipacao> root = criteriaQuery.from(PedidoParticipacao.class);
		
		Path<Date> pathDataPedidoParticipacao = root.<Date>get(PedidoParticipacao_.dataCadastroPedidoParticipacao);
		
		Expression<Long> expression = criteriaBuilder.count(root);
		
		Predicate[] predicate = getPredicateFilter(criteriaBuilder, root, pedidoParticipacaoFilter);
		
		criteriaQuery.multiselect(pathDataPedidoParticipacao, expression);
		criteriaQuery.where(predicate);
		criteriaQuery.groupBy(pathDataPedidoParticipacao);
		
		TypedQuery<Object[]> typedQuery = entityManager.createQuery(criteriaQuery);
		
		List<Object[]> resultado = typedQuery.getResultList();
		
		List<ResultadoDashboard> lista = gerarListaDia(pedidoParticipacaoFilter.getDataCadastroPedidoParticipacaoDe(), pedidoParticipacaoFilter.getDataCadastroPedidoParticipacaoAte());
		
		preencherResultado(resultado, lista);
		
		return lista;
	}
	
	@Override
	public List<ResultadoDashboard> totalPedidosEstado(PedidoParticipacaoFilter pedidoParticipacaoFilter) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
		Root<PedidoParticipacao> root = criteriaQuery.from(PedidoParticipacao.class);
		
		Path<EstadoPedido> pathEstadoPedido = root.<EstadoPedido>get(PedidoParticipacao_.estadoPedidoParticipacao);
		
		Expression<Long> expression = criteriaBuilder.count(root);
		
		Predicate[] predicate = getPredicateFilter(criteriaBuilder, root, pedidoParticipacaoFilter);
		
		criteriaQuery.multiselect(pathEstadoPedido, expression);
		criteriaQuery.where(predicate);
		criteriaQuery.groupBy(pathEstadoPedido);
		
		TypedQuery<Object[]> typedQuery = entityManager.createQuery(criteriaQuery);
		
		List<Object[]> resultado = typedQuery.getResultList();
		
		List<ResultadoDashboard> lista = new ArrayList<>();
		
		for (Object[] obj : resultado) {
			ResultadoDashboard rs = new ResultadoDashboard();
			rs.setGrupo(((EstadoPedido)obj[0]).getNome());
			rs.setTotal((Number) obj[1]);
			
			lista.add(rs);
		}

		return lista;
	}
	
	private Predicate[] getPredicateFilter(CriteriaBuilder criteriaBuilder, Root<PedidoParticipacao> root, PedidoParticipacaoFilter pedidoParticipacaoFilter) {
		List<Predicate> predicate = new ArrayList<Predicate>();
		
		if (pedidoParticipacaoFilter.getEstadoPedidoParticipacao() != null) {
			predicate.add(criteriaBuilder.equal(root.<EstadoPedido>get(PedidoParticipacao_.estadoPedidoParticipacao), pedidoParticipacaoFilter.getEstadoPedidoParticipacao()));
		}
		
		if (pedidoParticipacaoFilter.getDataAtualizacaoEstadoDe() != null) {
			predicate.add(criteriaBuilder.greaterThanOrEqualTo(root.<Date>get(PedidoParticipacao_.dataAtualizacaoEstado), pedidoParticipacaoFilter.getDataAtualizacaoEstadoDe()));
		}
		
		if (pedidoParticipacaoFilter.getDataAtualizacaoEstadoAte() != null) {
			predicate.add(criteriaBuilder.lessThanOrEqualTo(root.<Date>get(PedidoParticipacao_.dataAtualizacaoEstado), pedidoParticipacaoFilter.getDataAtualizacaoEstadoAte()));
		}
		
		if (pedidoParticipacaoFilter.getDataCadastroPedidoParticipacaoDe() != null) {
			predicate.add(criteriaBuilder.greaterThanOrEqualTo(root.<Date>get(PedidoParticipacao_.dataCadastroPedidoParticipacao), pedidoParticipacaoFilter.getDataCadastroPedidoParticipacaoDe()));
		}
		
		if (pedidoParticipacaoFilter.getDataCadastroPedidoParticipacaoAte() != null) {
			predicate.add(criteriaBuilder.lessThanOrEqualTo(root.<Date>get(PedidoParticipacao_.dataCadastroPedidoParticipacao), pedidoParticipacaoFilter.getDataCadastroPedidoParticipacaoAte()));
		}
		
		if (pedidoParticipacaoFilter.getTaxaParticipacaoPaga() != null) {
			
			if (pedidoParticipacaoFilter.getTaxaParticipacaoPaga()) {
				predicate.add(criteriaBuilder.isNotNull(root.<Pagamento>get(PedidoParticipacao_.pagamento)));
			}
			
			if (!pedidoParticipacaoFilter.getTaxaParticipacaoPaga()) {
				predicate.add(criteriaBuilder.isNull(root.<Pagamento>get(PedidoParticipacao_.pagamento)));
			}
			
		}
		
		if (pedidoParticipacaoFilter.getParticipouDoEvento() != null) {
			
			if (pedidoParticipacaoFilter.getParticipouDoEvento()) {
				predicate.add(criteriaBuilder.isNotNull(root.<Participacao>get(PedidoParticipacao_.participacao)));
			}
			
			if (!pedidoParticipacaoFilter.getParticipouDoEvento()) {
				predicate.add(criteriaBuilder.isNull(root.<Participacao>get(PedidoParticipacao_.participacao)));
			}
			
		}
		
		if (pedidoParticipacaoFilter.getEvento() != null) {
			predicate.add(criteriaBuilder.equal(root.<Evento>get(PedidoParticipacao_.evento), pedidoParticipacaoFilter.getEvento()));
		}
		
		if (pedidoParticipacaoFilter.getCiclista() != null) {
			predicate.add(criteriaBuilder.equal(root.<Ciclista>get(PedidoParticipacao_.ciclista), pedidoParticipacaoFilter.getCiclista()));
		}
		
		if (pedidoParticipacaoFilter.getOrganizador() != null) {
			predicate.add(criteriaBuilder.equal(root.<Evento>get(PedidoParticipacao_.evento).<Organizador>get(Evento_.organizador), pedidoParticipacaoFilter.getOrganizador()));
		}
		
		return predicate.toArray(new Predicate[0]);
	}

	@Override
	public boolean existePedidoParticipacaoAceito(Ciclista ciclista, Evento evento) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<PedidoParticipacao> root = criteriaQuery.from(PedidoParticipacao.class);

		Expression<Long> expression = criteriaBuilder.count(root);
		
		Predicate[] predicate = getPredicateExistePedidoParticipacaoAceito(criteriaBuilder, root, ciclista, evento);
		
		criteriaQuery.select(expression);
		criteriaQuery.where(predicate);
		
		TypedQuery<Long> typedQuery = entityManager.createQuery(criteriaQuery);
		
		Long resultado = typedQuery.getSingleResult();
		
		return resultado > 0;
	}

	private Predicate[] getPredicateExistePedidoParticipacaoAceito(CriteriaBuilder criteriaBuilder, Root<PedidoParticipacao> root, Ciclista ciclista, Evento evento) {
		List<Predicate> predicate = new ArrayList<Predicate>();
		
		if (ciclista != null) {
			predicate.add(criteriaBuilder.equal(root.<Ciclista>get(PedidoParticipacao_.ciclista), ciclista));
		}
		
		if (evento != null) {
			predicate.add(criteriaBuilder.equal(root.<Evento>get(PedidoParticipacao_.evento), evento));
		}
		
		predicate.add(criteriaBuilder.equal(root.<EstadoPedido>get(PedidoParticipacao_.estadoPedidoParticipacao), EstadoPedido.ACEITO));
		
		return predicate.toArray(new Predicate[0]);
	}

}
