package br.edu.ifpb.mt.ads.dac.util.qualifiers.impl;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import br.edu.ifpb.mt.ads.dac.model.Organizador;
import br.edu.ifpb.mt.ads.dac.model.Pessoa;
import br.edu.ifpb.mt.ads.dac.util.qualifiers.OrganizadorAutenticado;
import br.edu.ifpb.mt.ads.dac.util.qualifiers.PessoaAutenticada;

@ApplicationScoped
public class OrganizadorAutenticadoImpl {

	@Named
	@Produces
	@OrganizadorAutenticado
	public Organizador getOrganizadorAutenticado(@PessoaAutenticada Pessoa pessoa) {
		return pessoa.getOrganizador();
	}

}
