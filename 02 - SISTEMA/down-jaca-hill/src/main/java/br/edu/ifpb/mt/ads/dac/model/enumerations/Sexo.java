package br.edu.ifpb.mt.ads.dac.model.enumerations;

public enum Sexo {

	FEMININO("Feminino"), MASCULINO("Masculino");
	
	private String nome;
	
	private Sexo(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}

}
