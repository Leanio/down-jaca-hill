package br.edu.ifpb.mt.ads.dac.beans.dashboard.organizador.pedidosAvaliados;

import static br.edu.ifpb.mt.ads.dac.util.dashboard.Util.gerarLabels;
import static br.edu.ifpb.mt.ads.dac.util.dashboard.Util.juntarListasSemRepetir;

import java.util.List;

import javax.inject.Inject;

import br.edu.ifpb.mt.ads.dac.beans.dashboard.ChartLine;
import br.edu.ifpb.mt.ads.dac.filters.PedidoParticipacaoFilter;
import br.edu.ifpb.mt.ads.dac.model.Organizador;
import br.edu.ifpb.mt.ads.dac.model.enumerations.EstadoPedido;
import br.edu.ifpb.mt.ads.dac.services.PedidoParticipacaoService;
import br.edu.ifpb.mt.ads.dac.util.dashboard.ResultadoDashboard;
import br.edu.ifpb.mt.ads.dac.util.qualifiers.OrganizadorAutenticado;

public abstract class ChartLinePedidosAvaliados extends ChartLine {
	
	private static final long serialVersionUID = 4742335429091207008L;

	@Inject
	private PedidoParticipacaoService pedidoParticipacaoService;
	
	@Inject
	@OrganizadorAutenticado
	protected Organizador organizador;
	
	protected PedidoParticipacaoFilter pedidoParticipacaoFilter;
	
    public void createLineModel() {    
        pedidoParticipacaoFilter.setEstadoPedidoParticipacao(EstadoPedido.ACEITO);
        List<ResultadoDashboard> resultadoAceito = pedidoParticipacaoService.totalPedidosAvaliadosData(pedidoParticipacaoFilter);
        
        pedidoParticipacaoFilter.setEstadoPedidoParticipacao(EstadoPedido.RECUSADO);
        List<ResultadoDashboard> resultadoRecusado = pedidoParticipacaoService.totalPedidosAvaliadosData(pedidoParticipacaoFilter);
        
        add(resultadoAceito, "Aceito", "rgb(82, 190, 128)");
        add(resultadoRecusado, "Recusado", "rgb(236, 112, 99)");
   
        List<String> labelsAceito = gerarLabels(resultadoAceito);
        List<String> labelsRecusado = gerarLabels(resultadoRecusado);
        
        List<String> labels = juntarListasSemRepetir(labelsAceito, labelsRecusado);
        
        data.setLabels(labels);
      
        pedidoParticipacaoFilter.setEstadoPedidoParticipacao(null);
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
