package br.edu.ifpb.mt.ads.dac.beans.dashboard.organizador.pedidos.donut;

import java.util.List;

import javax.inject.Inject;

import br.edu.ifpb.mt.ads.dac.beans.dashboard.Donut;
import br.edu.ifpb.mt.ads.dac.filters.PedidoParticipacaoFilter;
import br.edu.ifpb.mt.ads.dac.model.Organizador;
import br.edu.ifpb.mt.ads.dac.services.PedidoParticipacaoService;
import br.edu.ifpb.mt.ads.dac.util.dashboard.ResultadoDashboard;
import br.edu.ifpb.mt.ads.dac.util.qualifiers.OrganizadorAutenticado;

public abstract class DonutPedidos extends Donut {

	private static final long serialVersionUID = -8254395362847655609L;
	
	@Inject
	private PedidoParticipacaoService pedidoParticipacaoService;
	
	@Inject
	@OrganizadorAutenticado
	protected Organizador organizador;
	
	protected PedidoParticipacaoFilter pedidoParticipacaoFilter;

    public void criarFiltro() {
    	pedidoParticipacaoFilter = new PedidoParticipacaoFilter();
    }

	@Override
	public void createDonutModel() {
		List<ResultadoDashboard> resultado = pedidoParticipacaoService.totalPedidosEstado(pedidoParticipacaoFilter); 
		
		add(resultado, "rgb(93, 173, 226)", "rgb(82, 190, 128)", "rgb(236, 112, 99)");
	}

	public PedidoParticipacaoFilter getPedidoParticipacaoFilter() {
		return pedidoParticipacaoFilter;
	}

	public void setPedidoParticipacaoFilter(PedidoParticipacaoFilter pedidoParticipacaoFilter) {
		this.pedidoParticipacaoFilter = pedidoParticipacaoFilter;
	}

}
