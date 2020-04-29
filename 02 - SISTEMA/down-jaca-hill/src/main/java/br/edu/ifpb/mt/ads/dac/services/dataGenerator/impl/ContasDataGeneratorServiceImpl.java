package br.edu.ifpb.mt.ads.dac.services.dataGenerator.impl;

import java.util.ArrayList;

import javax.inject.Inject;

import br.edu.ifpb.mt.ads.dac.model.Conta;
import br.edu.ifpb.mt.ads.dac.model.Grupo;
import br.edu.ifpb.mt.ads.dac.services.ContaService;
import br.edu.ifpb.mt.ads.dac.services.RoleService;
import br.edu.ifpb.mt.ads.dac.services.ServiceDacException;
import br.edu.ifpb.mt.ads.dac.services.dataGenerator.ContasDataGeneratorService;

public class ContasDataGeneratorServiceImpl implements ContasDataGeneratorService {

	private static final long serialVersionUID = 6738439385837209527L;
	
	@Inject
	private ContaService contaService;
	
	@Inject
	private RoleService roleService;

	@Override
	public void generateData() throws ServiceDacException {
		Conta conta = new Conta();
		conta.setLogin("admin");
		conta.setSenha("123");
		conta.setEmail("leanio.ramon@gmail.com");
		conta.setAtiva(true);
		conta.setGrupo(new ArrayList<Grupo>());
		
		contaService.salvar(conta);
		
		roleService.adicionarRoleDeGrupo(conta, "ADMINISTRADOR");
	}

}
