package br.edu.ifpb.mt.ads.dac.beans.dashboard.organizador.pedidos.chartLine;

import static br.edu.ifpb.mt.ads.dac.util.dashboard.Util.gerarLabels;

import java.util.List;

import javax.inject.Inject;

import br.edu.ifpb.mt.ads.dac.beans.dashboard.ChartLine;
import br.edu.ifpb.mt.ads.dac.filters.PedidoParticipacaoFilter;
import br.edu.ifpb.mt.ads.dac.model.Organizador;
import br.edu.ifpb.mt.ads.dac.services.PedidoParticipacaoService;
import br.edu.ifpb.mt.ads.dac.util.dashboard.ResultadoDashboard;
import br.edu.ifpb.mt.ads.dac.util.qualifiers.OrganizadorAutenticado;

public abstract class ChartLinePedidos extends ChartLine {
	
	private static final long serialVersionUID = 4742335429091207008L;

	@Inject
	private PedidoParticipacaoService pedidoParticipacaoService;
	
	@Inject
	@OrganizadorAutenticado
	protected Organizador organizador;
	
	protected PedidoParticipacaoFilter pedidoParticipacaoFilter;
	
    public void createLineModel() {
        List<ResultadoDashboard> resultado = pedidoParticipacaoService.totalPedidosData(pedidoParticipacaoFilter);
        
        add(resultado, "Pedidos de participação", "rgb(93, 173, 226)");
   
        List<String> labels = gerarLabels(resultado);
        data.setLabels(labels);
    }
    
    public void criarFiltro() {
    	pedidoParticipacaoFilter = new PedidoParticipacaoFilter();
    }

	public PedidoParticipacaoFilter getPedidoParticipacaoFilter() {
		return pedidoParticipacaoFilter;
	}

	public void setPedidoParticipacaoFilter(PedidoParticipacaoFilter pedidoParticipacaoFilter) {
		this.pedidoParticipacaoFilter = pedidoParticipacaoFilter;
	}
	
}
