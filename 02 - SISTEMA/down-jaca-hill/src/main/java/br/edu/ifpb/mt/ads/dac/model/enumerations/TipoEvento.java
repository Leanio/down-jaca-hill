package br.edu.ifpb.mt.ads.dac.model.enumerations;

public enum TipoEvento {

	DOWNHILL("Downhill"), PASSEIO("Passeio");
	
	private String nome;
	
	private TipoEvento(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}

}
