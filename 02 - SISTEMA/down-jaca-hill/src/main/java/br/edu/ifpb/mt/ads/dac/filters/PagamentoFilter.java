package br.edu.ifpb.mt.ads.dac.filters;

import java.util.Date;

import br.edu.ifpb.mt.ads.dac.model.Evento;
import br.edu.ifpb.mt.ads.dac.model.Organizador;

public class PagamentoFilter extends Filter {
	
	private static final long serialVersionUID = 6853500352055044686L;

	private Date dataPagamentoDe;

	private Date dataPagamentoAte;
	
	private Organizador organizador;
	
	private Evento evento;

	public Date getDataPagamentoDe() {
		return dataPagamentoDe;
	}

	public void setDataPagamentoDe(Date dataPagamentoDe) {
		this.dataPagamentoDe = dataPagamentoDe;
	}

	public Date getDataPagamentoAte() {
		return dataPagamentoAte;
	}

	public void setDataPagamentoAte(Date dataPagamentoAte) {
		this.dataPagamentoAte = dataPagamentoAte;
	}

	public Organizador getOrganizador() {
		return organizador;
	}

	public void setOrganizador(Organizador organizador) {
		this.organizador = organizador;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}
}
