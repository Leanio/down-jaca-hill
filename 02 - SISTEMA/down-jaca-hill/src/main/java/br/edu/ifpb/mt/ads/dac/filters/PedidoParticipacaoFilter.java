package br.edu.ifpb.mt.ads.dac.filters;

import java.util.Date;

import br.edu.ifpb.mt.ads.dac.model.Ciclista;
import br.edu.ifpb.mt.ads.dac.model.Evento;
import br.edu.ifpb.mt.ads.dac.model.Organizador;
import br.edu.ifpb.mt.ads.dac.model.enumerations.EstadoPedido;

public class PedidoParticipacaoFilter extends Filter {
	
	private static final long serialVersionUID = 8832885253843650693L;

	private EstadoPedido estadoPedidoParticipacao;
	
	private Date dataAtualizacaoEstadoDe;
	
	private Date dataAtualizacaoEstadoAte;
	
	private Date dataCadastroPedidoParticipacaoDe;
	
	private Date dataCadastroPedidoParticipacaoAte;
	
	private Boolean taxaParticipacaoPaga;
	
	private Boolean participouDoEvento;
	
	private Evento evento;
	
	private Ciclista ciclista;
	
	private Organizador organizador;

	public EstadoPedido getEstadoPedidoParticipacao() {
		return estadoPedidoParticipacao;
	}

	public void setEstadoPedidoParticipacao(EstadoPedido estadoPedidoParticipacao) {
		this.estadoPedidoParticipacao = estadoPedidoParticipacao;
	}

	public Date getDataAtualizacaoEstadoDe() {
		return dataAtualizacaoEstadoDe;
	}

	public void setDataAtualizacaoEstadoDe(Date dataAtualizacaoEstadoDe) {
		this.dataAtualizacaoEstadoDe = dataAtualizacaoEstadoDe;
	}

	public Date getDataAtualizacaoEstadoAte() {
		return dataAtualizacaoEstadoAte;
	}

	public void setDataAtualizacaoEstadoAte(Date dataAtualizacaoEstadoAte) {
		this.dataAtualizacaoEstadoAte = dataAtualizacaoEstadoAte;
	}

	public Date getDataCadastroPedidoParticipacaoDe() {
		return dataCadastroPedidoParticipacaoDe;
	}

	public void setDataCadastroPedidoParticipacaoDe(Date dataCadastroPedidoParticipacaoDe) {
		this.dataCadastroPedidoParticipacaoDe = dataCadastroPedidoParticipacaoDe;
	}

	public Date getDataCadastroPedidoParticipacaoAte() {
		return dataCadastroPedidoParticipacaoAte;
	}

	public void setDataCadastroPedidoParticipacaoAte(Date dataCadastroPedidoParticipacaoAte) {
		this.dataCadastroPedidoParticipacaoAte = dataCadastroPedidoParticipacaoAte;
	}

	public Boolean getTaxaParticipacaoPaga() {
		return taxaParticipacaoPaga;
	}

	public void setTaxaParticipacaoPaga(Boolean taxaParticipacaoPaga) {
		this.taxaParticipacaoPaga = taxaParticipacaoPaga;
	}

	public Boolean getParticipouDoEvento() {
		return participouDoEvento;
	}

	public void setParticipouDoEvento(Boolean participouDoEvento) {
		this.participouDoEvento = participouDoEvento;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Ciclista getCiclista() {
		return ciclista;
	}

	public void setCiclista(Ciclista ciclista) {
		this.ciclista = ciclista;
	}

	public Organizador getOrganizador() {
		return organizador;
	}

	public void setOrganizador(Organizador organizador) {
		this.organizador = organizador;
	}
	
}
