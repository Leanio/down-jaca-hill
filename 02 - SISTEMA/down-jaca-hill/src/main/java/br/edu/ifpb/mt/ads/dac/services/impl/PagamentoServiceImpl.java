package br.edu.ifpb.mt.ads.dac.services.impl;

import java.util.Date;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.edu.ifpb.mt.ads.dac.dao.PagamentoDAO;
import br.edu.ifpb.mt.ads.dac.dao.PersistenciaDacException;
import br.edu.ifpb.mt.ads.dac.filters.PagamentoFilter;
import br.edu.ifpb.mt.ads.dac.model.Pagamento;
import br.edu.ifpb.mt.ads.dac.model.PedidoParticipacao;
import br.edu.ifpb.mt.ads.dac.model.enumerations.EstadoPedido;
import br.edu.ifpb.mt.ads.dac.services.PagamentoService;
import br.edu.ifpb.mt.ads.dac.services.PedidoParticipacaoService;
import br.edu.ifpb.mt.ads.dac.services.ServiceDacException;
import br.edu.ifpb.mt.ads.dac.util.dashboard.ResultadoDashboard;
import br.edu.ifpb.mt.ads.dac.util.interceptors.TransacionalCdi;

@ApplicationScoped
public class PagamentoServiceImpl implements PagamentoService {

	private static final long serialVersionUID = -7844781132287268349L;
	
	@Inject
	private PagamentoDAO pagamentoDAO;
	
	@Inject
	private PedidoParticipacaoService pedidoParticipacaoService;

	@TransacionalCdi
	public void salvar(Pagamento pagamento) throws ServiceDacException {
		try {
			Date dataPagamento = new Date();
			EstadoPedido estadoPedidoParticipacao = EstadoPedido.ACEITO;
			
			PedidoParticipacao pedidoParticipacao = pagamento.getPedidoParticipacao();
			pedidoParticipacao.setEstadoPedidoParticipacao(estadoPedidoParticipacao);		
			pagamento.setDataPagamento(dataPagamento);
			
			pagamentoDAO.salvar(pagamento);
			
			pedidoParticipacaoService.atualizar(pedidoParticipacao);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
		
	}

	@TransacionalCdi
	public void remover(Long codigo) throws ServiceDacException {
		try {
			pagamentoDAO.remover(codigo);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
		
	}

	@TransacionalCdi
	public Pagamento atualizar(Pagamento pagamento) throws ServiceDacException {
		try {
			return pagamentoDAO.atualizar(pagamento);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	@Override
	public Pagamento buscarPeloCodigo(Long codigo) throws ServiceDacException {
		try {
			return pagamentoDAO.buscarPeloCodigo(codigo);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	@Override
	public List<Pagamento> listar() throws ServiceDacException {
		try {
			return pagamentoDAO.listar();
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	@Override
	public List<ResultadoDashboard> ganhosPagamentoMensal(PagamentoFilter pagamentoFilter) {
		return pagamentoDAO.ganhosPagamentoMensal(pagamentoFilter);
	}

}
