package br.edu.ifpb.mt.ads.dac.beans.model.pedidoParticipacao;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.ads.dac.model.Evento;
import br.edu.ifpb.mt.ads.dac.model.Organizador;
import br.edu.ifpb.mt.ads.dac.util.qualifiers.OrganizadorAutenticado;

@Named
@ViewScoped
public class ManagePedidoParticipacaoOrganizador extends ManagePedidoParticipacao {

	private static final long serialVersionUID = -8121238559528774569L;

	@Inject
	@OrganizadorAutenticado
	private Organizador organizador;
	
	private Evento evento;
	
	public void init() {
		criarFiltro();
	}

	@Override
	public void criarFiltro() {
		super.criarFiltro();

		pedidoParticipacaoFilter.setOrganizador(organizador);
		pedidoParticipacaoFilter.setEvento(evento);
	}
	
	public boolean isEventoPago() {
		return evento.getTaxaParticipacao() > 0;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}
}
