package br.edu.ifpb.mt.ads.dac.filters;

import java.util.Date;

import br.edu.ifpb.mt.ads.dac.model.Cidade;
import br.edu.ifpb.mt.ads.dac.model.Estado;
import br.edu.ifpb.mt.ads.dac.model.Organizador;
import br.edu.ifpb.mt.ads.dac.model.enumerations.EstadoEvento;

public class EventoFilter extends Filter {
	
	private static final long serialVersionUID = -5208197022418557567L;

	private String titulo;
	
	private Integer quantidadeVagasMinima;
	
	private Date dataEventoDe;
	
	private Date dataEventoAte;
	
	private Float taxaParticipacaoDe;
	
	private Float taxaParticipacaoAte;
	
//	private Boolean gratis;
	
	private EstadoEvento estadoEvento;
	
	private Estado estado;
	
	private Cidade cidade;
	
	private Organizador organizador;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getQuantidadeVagasMinima() {
		return quantidadeVagasMinima;
	}

	public void setQuantidadeVagasMinima(Integer quantidadeVagasMinima) {
		this.quantidadeVagasMinima = quantidadeVagasMinima;
	}

	public Date getDataEventoDe() {
		return dataEventoDe;
	}

	public void setDataEventoDe(Date dataEventoDe) {
		this.dataEventoDe = dataEventoDe;
	}

	public Date getDataEventoAte() {
		return dataEventoAte;
	}

	public void setDataEventoAte(Date dataEventoAte) {
		this.dataEventoAte = dataEventoAte;
	}

	public Float getTaxaParticipacaoDe() {
		return taxaParticipacaoDe;
	}

	public void setTaxaParticipacaoDe(Float taxaParticipacaoDe) {
		this.taxaParticipacaoDe = taxaParticipacaoDe;
	}

	public Float getTaxaParticipacaoAte() {
		return taxaParticipacaoAte;
	}

	public void setTaxaParticipacaoAte(Float taxaParticipacaoAte) {
		this.taxaParticipacaoAte = taxaParticipacaoAte;
	}

//	public Boolean getGratis() {
//		return gratis;
//	}
//
//	public void setGratis(Boolean gratis) {
//		this.gratis = gratis;
//	}

	public EstadoEvento getEstadoEvento() {
		return estadoEvento;
	}

	public void setEstadoEvento(EstadoEvento estadoEvento) {
		this.estadoEvento = estadoEvento;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Organizador getOrganizador() {
		return organizador;
	}

	public void setOrganizador(Organizador organizador) {
		this.organizador = organizador;
	}
	
}
