package br.edu.ifpb.mt.ads.dac.filters;

import java.io.Serializable;

public abstract class Filter implements Serializable {
	
	private static final long serialVersionUID = 7817428161904317660L;

	private Integer proximoItem;
	
	private Integer quantidadeResultadosPorPagina;

	public Integer getProximoItem() {
		return proximoItem;
	}

	public void setProximoItem(Integer proximoItem) {
		this.proximoItem = proximoItem;
	}

	public Integer getQuantidadeResultadosPorPagina() {
		return quantidadeResultadosPorPagina;
	}

	public void setQuantidadeResultadosPorPagina(Integer quantidadeResultadosPorPagina) {
		this.quantidadeResultadosPorPagina = quantidadeResultadosPorPagina;
	}
	
}
