package br.edu.ifpb.mt.ads.dac.model.enumerations;

public enum EstadoEvento {
	
	CARTAZ("Cartaz"), ANDAMENTO("Andamento"), ENCERRADO("Encerrado"), CANCELADO("Cancelado");
	
	private String nome;
	
	private EstadoEvento(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}

}
