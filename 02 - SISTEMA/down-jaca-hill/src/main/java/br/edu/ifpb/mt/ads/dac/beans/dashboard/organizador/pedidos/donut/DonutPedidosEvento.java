package br.edu.ifpb.mt.ads.dac.beans.dashboard.organizador.pedidos.donut;

import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.edu.ifpb.mt.ads.dac.model.Evento;

@Named
@RequestScoped
public class DonutPedidosEvento extends DonutPedidos {

	private static final long serialVersionUID = -5304237357899252633L;
	
	private Evento evento;
	
	public void init() {
		Date hoje = new Date();
		
		Date dataAtualizacaoEstadoDe = evento.getDataCadastroEvento();
		Date dataAtualizacaoEstadoAte = evento.getDataEvento();
		
		dataAtualizacaoEstadoAte = dataAtualizacaoEstadoAte.after(hoje) ? hoje : dataAtualizacaoEstadoAte;
		
		pedidoParticipacaoFilter.setDataCadastroPedidoParticipacaoDe(dataAtualizacaoEstadoDe);;
		pedidoParticipacaoFilter.setDataCadastroPedidoParticipacaoAte(dataAtualizacaoEstadoAte);
		pedidoParticipacaoFilter.setOrganizador(organizador);
		pedidoParticipacaoFilter.setEvento(evento);
		
		createDonutModel();
	}
	
	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

}
