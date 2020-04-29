package br.edu.ifpb.mt.ads.dac.beans.dashboard.organizador.pedidosAvaliados;

import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.edu.ifpb.mt.ads.dac.model.Evento;

@Named
@RequestScoped
public class ChartLinePedidosAvaliadosEvento extends ChartLinePedidosAvaliados {

	private static final long serialVersionUID = -2542840104030577362L;
	
	private Evento evento;
	
	public void init() {
		Date hoje = new Date();
		
		Date dataAtualizacaoEstadoDe = evento.getDataCadastroEvento();
		Date dataAtualizacaoEstadoAte = evento.getDataEvento();
		
		dataAtualizacaoEstadoAte = dataAtualizacaoEstadoAte.after(hoje) ? hoje : dataAtualizacaoEstadoAte;
		
		pedidoParticipacaoFilter.setDataAtualizacaoEstadoDe(dataAtualizacaoEstadoDe);;
		pedidoParticipacaoFilter.setDataAtualizacaoEstadoAte(dataAtualizacaoEstadoAte);
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
