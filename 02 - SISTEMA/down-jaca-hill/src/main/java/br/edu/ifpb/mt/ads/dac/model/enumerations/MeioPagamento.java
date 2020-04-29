package br.edu.ifpb.mt.ads.dac.model.enumerations;

public enum MeioPagamento {

	A_VISTA("A Vista"), CREDITO("Crédito"), DEBITO("Débito");
	
	private String nome;
	
	private MeioPagamento(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}

}
