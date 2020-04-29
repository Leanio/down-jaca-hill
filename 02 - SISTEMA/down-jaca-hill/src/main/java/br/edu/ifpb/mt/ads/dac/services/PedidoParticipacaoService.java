package br.edu.ifpb.mt.ads.dac.services;

import java.util.List;

import br.edu.ifpb.mt.ads.dac.filters.PedidoParticipacaoFilter;
import br.edu.ifpb.mt.ads.dac.model.PedidoParticipacao;
import br.edu.ifpb.mt.ads.dac.util.dashboard.ResultadoDashboard;

public interface PedidoParticipacaoService extends Service<PedidoParticipacao, Long> {

	public List<PedidoParticipacao> filtrar(PedidoParticipacaoFilter pedidoParticipacaoFilter) throws ServiceDacException;

	public long total(PedidoParticipacaoFilter pedidoParticipacaoFilter);

	public List<ResultadoDashboard> totalPedidosAvaliadosData(PedidoParticipacaoFilter pedidoParticipacaoFilter);

	public List<ResultadoDashboard> totalPedidosData(PedidoParticipacaoFilter pedidoParticipacaoFilter);

	public List<ResultadoDashboard> totalPedidosEstado(PedidoParticipacaoFilter pedidoParticipacaoFilter);

}
