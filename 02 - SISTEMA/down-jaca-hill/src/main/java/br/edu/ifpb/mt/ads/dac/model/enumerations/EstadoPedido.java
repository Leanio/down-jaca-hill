package br.edu.ifpb.mt.ads.dac.model.enumerations;

public enum EstadoPedido {

	AGUARDANDO("Aguardando"), ACEITO("Aceito"), RECUSADO("Recusado");

	private String nome;
	
	private EstadoPedido(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
}
