package br.edu.ifpb.mt.ads.dac.filters;

import br.edu.ifpb.mt.ads.dac.model.Marca;

public class ModeloFilter extends Filter {
	
	private static final long serialVersionUID = -4582955553060271929L;
	
	private String nome;
	
	private Marca marca;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

}
