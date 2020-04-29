package br.edu.ifpb.mt.ads.dac.beans.dashboard.organizador.pedidosAvaliados;

import static br.edu.ifpb.mt.ads.dac.util.date.DateUtil.subtrair;

import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.edu.ifpb.mt.ads.dac.util.date.DateUtil;

@Named
@RequestScoped
public class ChartLinePedidosAvaliadosOrganizador extends ChartLinePedidosAvaliados {

	private static final long serialVersionUID = -1559965251003078604L;
	
	private Integer dias = 15;
	
	public void init() {
		Date hoje = new Date();
		
		Date dataAtualizacaoEstadoDe = subtrair(hoje, dias-1, DateUtil.DIA);
		Date dataAtualizacaoEstadoAte = hoje;
		
		pedidoParticipacaoFilter.setDataAtualizacaoEstadoDe(dataAtualizacaoEstadoDe);;
		pedidoParticipacaoFilter.setDataAtualizacaoEstadoAte(dataAtualizacaoEstadoAte);
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
