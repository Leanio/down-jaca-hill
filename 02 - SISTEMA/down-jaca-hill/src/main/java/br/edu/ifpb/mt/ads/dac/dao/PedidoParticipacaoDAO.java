package br.edu.ifpb.mt.ads.dac.dao;

import java.util.List;

import br.edu.ifpb.mt.ads.dac.filters.PedidoParticipacaoFilter;
import br.edu.ifpb.mt.ads.dac.model.Ciclista;
import br.edu.ifpb.mt.ads.dac.model.Evento;
import br.edu.ifpb.mt.ads.dac.model.PedidoParticipacao;
import br.edu.ifpb.mt.ads.dac.util.dashboard.ResultadoDashboard;

public interface PedidoParticipacaoDAO extends DAO<PedidoParticipacao, Long> {

	public List<PedidoParticipacao> filtrar(PedidoParticipacaoFilter pedidoParticipacaoFilter) throws PersistenciaDacException;
	
	public boolean existePedidoParticipacaoAceito(Ciclista ciclista, Evento evento);
	
	public long total(PedidoParticipacaoFilter pedidoParticipacaoFilter);

	public List<ResultadoDashboard> totalPedidosAvaliadosData(PedidoParticipacaoFilter pedidoParticipacaoFilter);
	
	public List<ResultadoDashboard> totalPedidosData(PedidoParticipacaoFilter pedidoParticipacaoFilter);
	
	public List<ResultadoDashboard> totalPedidosEstado(PedidoParticipacaoFilter pedidoParticipacaoFilter);
	
}
