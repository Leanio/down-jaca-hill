package br.edu.ifpb.mt.ads.dac.util.qualifiers.impl;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import br.edu.ifpb.mt.ads.dac.model.Conta;
import br.edu.ifpb.mt.ads.dac.model.Pessoa;
import br.edu.ifpb.mt.ads.dac.util.qualifiers.ContaAutenticada;
import br.edu.ifpb.mt.ads.dac.util.qualifiers.PessoaAutenticada;

@ApplicationScoped
public class PessoaAutenticadaImpl {

	@Named
	@Produces
	@PessoaAutenticada
	public Pessoa getPessoaAutenticada(@ContaAutenticada Conta conta) {
		return conta.getPessoa();
	}

}
