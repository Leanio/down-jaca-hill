package br.edu.ifpb.mt.ads.dac.services.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.edu.ifpb.mt.ads.dac.dao.EventoDAO;
import br.edu.ifpb.mt.ads.dac.dao.PersistenciaDacException;
import br.edu.ifpb.mt.ads.dac.filters.EventoFilter;
import br.edu.ifpb.mt.ads.dac.model.Evento;
import br.edu.ifpb.mt.ads.dac.model.Participacao;
import br.edu.ifpb.mt.ads.dac.model.Premiacao;
import br.edu.ifpb.mt.ads.dac.model.enumerations.EstadoEvento;
import br.edu.ifpb.mt.ads.dac.services.EventoService;
import br.edu.ifpb.mt.ads.dac.services.ParticipacaoService;
import br.edu.ifpb.mt.ads.dac.services.ServiceDacException;
import br.edu.ifpb.mt.ads.dac.util.interceptors.TransacionalCdi;

@ApplicationScoped
public class EventoServiceImpl implements EventoService {

	private static final long serialVersionUID = 2513588515913376461L;

	@Inject
	private EventoDAO eventoDAO;
	
	@Inject
	private ParticipacaoService participacaoService;

	@TransacionalCdi
	public void salvar(Evento evento) throws ServiceDacException {
		try {
			validarDatasDoEvento(evento);
			
			EstadoEvento estadoEvento = EstadoEvento.CARTAZ;
			Date dataCadastroEvento = new Date();

			evento.setEstadoEvento(estadoEvento);
			evento.setDataCadastroEvento(dataCadastroEvento);

			eventoDAO.salvar(evento);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	@TransacionalCdi
	public void remover(Long codigo) throws ServiceDacException {
		try {
			verificarAssociacaoComPedidoParticipacao(codigo);

			eventoDAO.remover(codigo);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	@TransacionalCdi
	public Evento atualizar(Evento evento) throws ServiceDacException {
		try {
			validarDatasDoEvento(evento);
			
			return eventoDAO.atualizar(evento);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	@Override
	public Evento buscarPeloCodigo(Long codigo) throws ServiceDacException {
		try {
			return eventoDAO.buscarPeloCodigo(codigo);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	@Override
	public List<Evento> listar() throws ServiceDacException {
		try {
			return eventoDAO.listar();
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	@Override
	public List<Evento> filtrar(EventoFilter eventoFilter) throws ServiceDacException {
		return eventoDAO.filtrar(eventoFilter);
	}
	
	@Override
	public long total(EventoFilter eventoFilter) {
		return eventoDAO.total(eventoFilter);
	}

	@TransacionalCdi
	public void decrementarVaga(Evento evento) throws ServiceDacException {
		try {
			Long codigo = evento.getCodigo();
			Evento eventoAtual = eventoDAO.buscarPeloCodigo(codigo);

			Integer quantidadeVagas = eventoAtual.getQuantidadeVagas();
			
			if (quantidadeVagas == 0) {
				throw new ServiceDacException("Não há vaga disponível");
			}
			
			quantidadeVagas--;
			eventoAtual.setQuantidadeVagas(quantidadeVagas);
			
			eventoDAO.atualizar(eventoAtual);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	@TransacionalCdi
	public void incrementarVaga(Evento evento) throws ServiceDacException {
		try {
			Long codigo = evento.getCodigo();
			Evento eventoAtual = eventoDAO.buscarPeloCodigo(codigo);

			Integer quantidadeVagas = eventoAtual.getQuantidadeVagas();

			quantidadeVagas++;
			eventoAtual.setQuantidadeVagas(quantidadeVagas);

			eventoDAO.atualizar(eventoAtual);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}

	}
	
	@TransacionalCdi
	public void definirVencedores(Evento evento) throws ServiceDacException {
		try {
			Long codigo = evento.getCodigo();
			Evento eventoAtual = eventoDAO.buscarPeloCodigo(codigo);
			
			List<Premiacao> premiacoes = new ArrayList<>(eventoAtual.getPremiacao());
			Collections.sort(premiacoes);
			
			List<Participacao> participacoesOrdenada = participacaoService.listaOrdenadaPeloTempoGasto(eventoAtual);
			
			for (int i = 0; i < premiacoes.size() && i < participacoesOrdenada.size(); i++) {
				Premiacao premiacao = premiacoes.get(i);
				Participacao participacao = participacoesOrdenada.get(i);
				
				premiacao.setParticipacao(participacao);
				participacao.setPremiacao(premiacao);
			}
			
			eventoDAO.atualizar(eventoAtual);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	@Override
	public void validarEventoComVaga(Evento evento) throws ServiceDacException {
		if (!eventoDAO.eventoPossuiVagaDisponivel(evento)) {
			throw new ServiceDacException("Não há vaga disponível");
		}
	}

	private void verificarAssociacaoComPedidoParticipacao(Long codigo) throws ServiceDacException {
		if (eventoDAO.existeAssociacaoComPedidoParticipacao(codigo)) {
			throw new ServiceDacException("Evento com pedido de participação");
		}
	}
	
	private void validarDatasDoEvento(Evento evento) throws ServiceDacException {
		Date pedidoParticipacaoDe = evento.getPedidoParticipacaoDe();
		Date pedidoParticipacaoAte = evento.getPedidoParticipacaoAte();
		Date dataEvento = evento.getDataEvento();
		
		if (pedidoParticipacaoDe.compareTo(pedidoParticipacaoAte) == 1) {
			throw new ServiceDacException("A data de inicio dos pedidos de participação não pode ser maior que a data de termino");
		}
		
		if (pedidoParticipacaoAte.compareTo(dataEvento) == 1) {
			throw new ServiceDacException("A data de termino dos pedidos de participação não pode ser maior que a data do evento");
		}
	}

}
