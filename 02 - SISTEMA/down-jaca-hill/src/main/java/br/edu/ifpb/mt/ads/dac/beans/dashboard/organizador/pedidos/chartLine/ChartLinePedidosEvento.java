package br.edu.ifpb.mt.ads.dac.beans.dashboard.organizador.pedidos.chartLine;

import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.edu.ifpb.mt.ads.dac.model.Evento;

@Named
@RequestScoped
public class ChartLinePedidosEvento extends ChartLinePedidos {
	
	private static final long serialVersionUID = -4242812555669605538L;
	
	private Evento evento;
	
	public void init() {
		Date hoje = new Date();

		Date dataCadastroPedidoParticipacaoDe = evento.getDataCadastroEvento();
		Date dataCadastroPedidoParticipacaoAte = evento.getDataEvento();
		
		dataCadastroPedidoParticipacaoAte = dataCadastroPedidoParticipacaoAte.after(hoje) ? hoje : dataCadastroPedidoParticipacaoAte;
		
		pedidoParticipacaoFilter.setDataCadastroPedidoParticipacaoDe(dataCadastroPedidoParticipacaoDe);
		pedidoParticipacaoFilter.setDataCadastroPedidoParticipacaoAte(dataCadastroPedidoParticipacaoAte);
		pedidoParticipacaoFilter.setOrganizador(organizador);
		pedidoParticipacaoFilter.setEvento(evento);
		
		createLineModel();
	}
	
	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

}
