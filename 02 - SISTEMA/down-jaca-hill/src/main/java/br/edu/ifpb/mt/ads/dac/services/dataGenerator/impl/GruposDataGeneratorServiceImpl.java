package br.edu.ifpb.mt.ads.dac.services.dataGenerator.impl;

import javax.inject.Inject;

import br.edu.ifpb.mt.ads.dac.model.Grupo;
import br.edu.ifpb.mt.ads.dac.services.GrupoService;
import br.edu.ifpb.mt.ads.dac.services.ServiceDacException;
import br.edu.ifpb.mt.ads.dac.services.dataGenerator.GruposDataGeneratorService;

public class GruposDataGeneratorServiceImpl implements GruposDataGeneratorService {

	private static final long serialVersionUID = -9023229557380382974L;
	
	@Inject
	private GrupoService grupoService;

	@Override
	public void generateData() throws ServiceDacException {
		Grupo grupoConta = new Grupo();
		grupoConta.setNome("CONTA");
		
		Grupo grupoAdministrador = new Grupo();
		grupoAdministrador.setNome("ADMINISTRADOR");
		
		Grupo grupoPessoa = new Grupo();
		grupoPessoa.setNome("PESSOA");
		
		Grupo grupoCiclista = new Grupo();
		grupoCiclista.setNome("CICLISTA");
		
		Grupo grupoOrganizador = new Grupo();
		grupoOrganizador.setNome("ORGANIZADOR");
		
		grupoService.salvar(grupoConta);
		grupoService.salvar(grupoAdministrador);
		grupoService.salvar(grupoPessoa);
		grupoService.salvar(grupoCiclista);
		grupoService.salvar(grupoOrganizador);
		
	}

}
