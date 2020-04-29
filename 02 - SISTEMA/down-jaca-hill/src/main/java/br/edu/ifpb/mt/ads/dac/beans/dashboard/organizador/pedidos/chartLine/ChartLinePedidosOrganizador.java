package br.edu.ifpb.mt.ads.dac.beans.dashboard.organizador.pedidos.chartLine;

import static br.edu.ifpb.mt.ads.dac.util.date.DateUtil.subtrair;

import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.edu.ifpb.mt.ads.dac.util.date.DateUtil;

@Named
@RequestScoped
public class ChartLinePedidosOrganizador extends ChartLinePedidos {

	private static final long serialVersionUID = 7135610168821214845L;
	
	private Integer dias = 15;
	
	public void init() {	
		Date hoje = new Date();
		
		Date dataCadastroPedidoParticipacaoDe = subtrair(hoje, dias-1, DateUtil.DIA);
		Date dataCadastroPedidoParticipacaoAte = hoje;
		
		pedidoParticipacaoFilter.setDataCadastroPedidoParticipacaoDe(dataCadastroPedidoParticipacaoDe);
		pedidoParticipacaoFilter.setDataCadastroPedidoParticipacaoAte(dataCadastroPedidoParticipacaoAte);
		pedidoParticipacaoFilter.setOrganizador(organizador);
		
		createLineModel();
	}
	
	public Integer getDias() {
		return dias;
	}

	public void setDias(Integer dias) {
		this.dias = dias;
	}

}
