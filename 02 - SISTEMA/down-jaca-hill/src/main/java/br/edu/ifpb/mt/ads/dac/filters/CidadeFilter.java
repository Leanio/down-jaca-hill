package br.edu.ifpb.mt.ads.dac.filters;

import br.edu.ifpb.mt.ads.dac.model.Estado;

public class CidadeFilter extends Filter {

	private static final long serialVersionUID = -1032363526554974600L;
	
	private String nome;
	
	private Boolean isLiberadaEvento;
	
	private Estado estado;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Boolean getIsLiberadaEvento() {
		return isLiberadaEvento;
	}

	public void setIsLiberadaEvento(Boolean isLiberadaEvento) {
		this.isLiberadaEvento = isLiberadaEvento;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

}
