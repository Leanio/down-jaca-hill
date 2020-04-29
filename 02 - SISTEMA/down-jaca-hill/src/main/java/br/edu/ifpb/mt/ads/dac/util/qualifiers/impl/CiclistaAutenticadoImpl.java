package br.edu.ifpb.mt.ads.dac.util.qualifiers.impl;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import br.edu.ifpb.mt.ads.dac.model.Ciclista;
import br.edu.ifpb.mt.ads.dac.model.Pessoa;
import br.edu.ifpb.mt.ads.dac.util.qualifiers.CiclistaAutenticado;
import br.edu.ifpb.mt.ads.dac.util.qualifiers.PessoaAutenticada;

@ApplicationScoped
public class CiclistaAutenticadoImpl {

	@Named
	@Produces
	@CiclistaAutenticado
	public Ciclista getCiclistaAutenticado(@PessoaAutenticada Pessoa pessoa) {
		return pessoa.getCiclista();
	}

}
