package br.edu.ifpb.mt.ads.dac.services.impl;

import java.util.Date;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.edu.ifpb.mt.ads.dac.dao.PedidoParticipacaoDAO;
import br.edu.ifpb.mt.ads.dac.dao.PersistenciaDacException;
import br.edu.ifpb.mt.ads.dac.filters.PedidoParticipacaoFilter;
import br.edu.ifpb.mt.ads.dac.model.Ciclista;
import br.edu.ifpb.mt.ads.dac.model.Evento;
import br.edu.ifpb.mt.ads.dac.model.PedidoParticipacao;
import br.edu.ifpb.mt.ads.dac.model.enumerations.EstadoEvento;
import br.edu.ifpb.mt.ads.dac.model.enumerations.EstadoPedido;
import br.edu.ifpb.mt.ads.dac.services.EventoService;
import br.edu.ifpb.mt.ads.dac.services.PedidoParticipacaoService;
import br.edu.ifpb.mt.ads.dac.services.ServiceDacException;
import br.edu.ifpb.mt.ads.dac.util.dashboard.ResultadoDashboard;
import br.edu.ifpb.mt.ads.dac.util.interceptors.TransacionalCdi;

@ApplicationScoped
public class PedidoParticipacaoServiceImpl implements PedidoParticipacaoService {

	private static final long serialVersionUID = 2423256047993231878L;

	@Inject
	private PedidoParticipacaoDAO pedidoParticipacaoDAO;

	@Inject
	private EventoService eventoService;

	@TransacionalCdi
	public void salvar(PedidoParticipacao pedidoParticipacao) throws ServiceDacException {
		try {
			Long codigoEvento = pedidoParticipacao.getEvento().getCodigo();
			Evento eventoAtual = eventoService.buscarPeloCodigo(codigoEvento);

			validarPedidoParticipacao(eventoAtual);

			Float taxaParticipacao = eventoAtual.getTaxaParticipacao();

			EstadoPedido estadoPedidoParticipacao = EstadoPedido.AGUARDANDO;
			Date dataCadastroPedidoParticipacao = new Date();

			pedidoParticipacao.setEstadoPedidoParticipacao(estadoPedidoParticipacao);
			pedidoParticipacao.setDataCadastroPedidoParticipacao(dataCadastroPedidoParticipacao);
			pedidoParticipacao.setTaxaParticipacao(taxaParticipacao);

			pedidoParticipacaoDAO.salvar(pedidoParticipacao);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	@TransacionalCdi
	public void remover(Long codigo) throws ServiceDacException {
		try {
			PedidoParticipacao pedidoParticipacao = pedidoParticipacaoDAO.buscarPeloCodigo(codigo);
			Evento evento = pedidoParticipacao.getEvento();

			validarRemocaoPedidoParticipacao(pedidoParticipacao);

			EstadoPedido estadoPedidoParticipacao = pedidoParticipacao.getEstadoPedidoParticipacao();

			if (estadoPedidoParticipacao == EstadoPedido.ACEITO) {
				eventoService.incrementarVaga(evento);
			}

			pedidoParticipacaoDAO.remover(codigo);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}

	}

	@TransacionalCdi
	public PedidoParticipacao atualizar(PedidoParticipacao pedidoParticipacao) throws ServiceDacException {
		try {
			Long codigo = pedidoParticipacao.getCodigo();
			PedidoParticipacao pedidoParticipacaoAtual = pedidoParticipacaoDAO.buscarPeloCodigo(codigo);

			Evento evento = pedidoParticipacaoAtual.getEvento();

			EstadoPedido estadoPedido = pedidoParticipacao.getEstadoPedidoParticipacao();
			EstadoPedido estadoPedidoAtual = pedidoParticipacaoAtual.getEstadoPedidoParticipacao();

			if (estadoPedidoAtual != EstadoPedido.ACEITO && estadoPedido == EstadoPedido.ACEITO) {
				verificarPedidoParticipacaoAceito(pedidoParticipacaoAtual);

				eventoService.decrementarVaga(evento);
			}

			if (estadoPedidoAtual == EstadoPedido.ACEITO && estadoPedido != EstadoPedido.ACEITO) {

				if (pedidoParticipacaoAtual.getPagamento() != null) {
					throw new ServiceDacException("Não é possível alterar o pedido com taxa de participação paga");
				}

				eventoService.incrementarVaga(evento);
			}

			Date dataAtualizacaoEstado = new Date();
			pedidoParticipacao.setDataAtualizacaoEstado(dataAtualizacaoEstado);

			return pedidoParticipacaoDAO.atualizar(pedidoParticipacao);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	@Override
	public PedidoParticipacao buscarPeloCodigo(Long codigo) throws ServiceDacException {
		try {
			return pedidoParticipacaoDAO.buscarPeloCodigo(codigo);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	@Override
	public List<PedidoParticipacao> listar() throws ServiceDacException {
		try {
			return pedidoParticipacaoDAO.listar();
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	@Override
	public List<PedidoParticipacao> filtrar(PedidoParticipacaoFilter pedidoParticipacaoFilter)
			throws ServiceDacException {
		try {
			return pedidoParticipacaoDAO.filtrar(pedidoParticipacaoFilter);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	@Override
	public long total(PedidoParticipacaoFilter pedidoParticipacaoFilter) {
		return pedidoParticipacaoDAO.total(pedidoParticipacaoFilter);
	}

	@Override
	public List<ResultadoDashboard> totalPedidosAvaliadosData(PedidoParticipacaoFilter pedidoParticipacaoFilter) {
		return pedidoParticipacaoDAO.totalPedidosAvaliadosData(pedidoParticipacaoFilter);
	}

	@Override
	public List<ResultadoDashboard> totalPedidosData(PedidoParticipacaoFilter pedidoParticipacaoFilter) {
		return pedidoParticipacaoDAO.totalPedidosData(pedidoParticipacaoFilter);
	}

	@Override
	public List<ResultadoDashboard> totalPedidosEstado(PedidoParticipacaoFilter pedidoParticipacaoFilter) {
		return pedidoParticipacaoDAO.totalPedidosEstado(pedidoParticipacaoFilter);
	}

	private void validarRemocaoPedidoParticipacao(PedidoParticipacao pedidoParticipacao) throws ServiceDacException {
		if (pedidoParticipacao.getPagamento() != null) {
			throw new ServiceDacException("Pedido de participação pago não pode ser removido");
		}

		if (pedidoParticipacao.getParticipacao() != null) {
			throw new ServiceDacException("Pedido de participação com particição confirmada não pode ser removido");
		}
	}

	private void validarPedidoParticipacao(Evento eventoAtual) throws ServiceDacException {
		Integer quantidadeVagas = eventoAtual.getQuantidadeVagas();

		if (quantidadeVagas <= 0) {
			throw new ServiceDacException("Não há mais vagas nesse evento");
		}

		Date pedidoParticipacaoAte = eventoAtual.getPedidoParticipacaoAte();
		Date hoje = new Date();

		if (pedidoParticipacaoAte.before(hoje)) {
			throw new ServiceDacException("O período de pedidos para esse evento acabou");

		}

		Date pedidoParticipacaoDe = eventoAtual.getPedidoParticipacaoDe();

		if (pedidoParticipacaoDe.after(hoje)) {
			throw new ServiceDacException("O período de pedidos para esse evento ainda não iniciou");
		}

		EstadoEvento estadoEvento = eventoAtual.getEstadoEvento();

		if (estadoEvento != EstadoEvento.CARTAZ) {
			throw new ServiceDacException("O evento não está em cartaz");
		}
	}

	private void verificarPedidoParticipacaoAceito(PedidoParticipacao pedidoParticipacao) throws ServiceDacException {
		Ciclista ciclista = pedidoParticipacao.getCiclista();
		Evento evento = pedidoParticipacao.getEvento();
		
		if (pedidoParticipacaoDAO.existePedidoParticipacaoAceito(ciclista, evento)) {
			throw new ServiceDacException("Este ciclista ja possui um pedido de participação aceito");
		}
	}

}
