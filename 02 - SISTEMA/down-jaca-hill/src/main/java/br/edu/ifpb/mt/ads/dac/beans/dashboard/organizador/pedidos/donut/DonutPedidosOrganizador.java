package br.edu.ifpb.mt.ads.dac.beans.dashboard.organizador.pedidos.donut;

import static br.edu.ifpb.mt.ads.dac.util.date.DateUtil.subtrair;

import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.edu.ifpb.mt.ads.dac.util.date.DateUtil;

@Named
@RequestScoped
public class DonutPedidosOrganizador extends DonutPedidos {

	private static final long serialVersionUID = 2686677798171355705L;
	
	private Integer dias = 15;
	
	public void init() {
		Date hoje = new Date();
		
		Date dataCadastroPedidoParticipacaoDe = subtrair(hoje, dias-1, DateUtil.DIA);
		Date dataCadastroPedidoParticipacaoAte = hoje;
		
		pedidoParticipacaoFilter.setDataCadastroPedidoParticipacaoDe(dataCadastroPedidoParticipacaoDe);
		pedidoParticipacaoFilter.setDataCadastroPedidoParticipacaoAte(dataCadastroPedidoParticipacaoAte);
		pedidoParticipacaoFilter.setOrganizador(organizador);
		
		createDonutModel();
	}
	
	public Integer getDias() {
		return dias;
	}

	public void setDias(Integer dias) {
		this.dias = dias;
	}

}
