package br.edu.ifpb.mt.ads.dac.services.impl;

import java.util.Date;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.edu.ifpb.mt.ads.dac.dao.OrganizadorDAO;
import br.edu.ifpb.mt.ads.dac.dao.PersistenciaDacException;
import br.edu.ifpb.mt.ads.dac.model.Conta;
import br.edu.ifpb.mt.ads.dac.model.Organizador;
import br.edu.ifpb.mt.ads.dac.services.OrganizadorService;
import br.edu.ifpb.mt.ads.dac.services.RoleService;
import br.edu.ifpb.mt.ads.dac.services.ServiceDacException;
import br.edu.ifpb.mt.ads.dac.util.interceptors.TransacionalCdi;

@ApplicationScoped
public class OrganizadorServiceImpl implements OrganizadorService {

	private static final long serialVersionUID = 2405704853756574832L;
	
	@Inject
	private OrganizadorDAO organizadorDAO;
	
	@Inject
	private RoleService roleService;

	@TransacionalCdi
	public void salvar(Organizador organizador) throws ServiceDacException {
		try {
			validarRgUnico(organizador);
			
			Conta conta = organizador.getPessoa().getConta();
			
			roleService.adicionarRoleDeGrupo(conta, "ORGANIZADOR");
			
			Date dataCadastroPerfilOrganizador = new Date();
			
			organizador.setDataCadastroPerfilOrganizador(dataCadastroPerfilOrganizador);
			
			organizadorDAO.salvar(organizador);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
		
	}

	@TransacionalCdi
	public void remover(Long codigo) throws ServiceDacException {
		// TODO Auto-generated method stub
		
	}

	@TransacionalCdi
	public Organizador atualizar(Organizador organizador) throws ServiceDacException {
		try {
			validarRgUnico(organizador);
			Conta conta= organizador.getPessoa().getConta();
			
			roleService.adicionarRoleDeGrupo(conta, "ORGANIZADOR");
			
			return organizadorDAO.atualizar(organizador);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	@Override
	public Organizador buscarPeloCodigo(Long codigo) throws ServiceDacException {
		try {
			return organizadorDAO.buscarPeloCodigo(codigo);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	@Override
	public List<Organizador> listar() throws ServiceDacException {
		try {
			return organizadorDAO.listar();
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}
	
	@TransacionalCdi
	public void desativar(Organizador organizador) throws ServiceDacException {
		Conta conta= organizador.getPessoa().getConta();
		
		roleService.removerRoleDeGrupo(conta, "ORGANIZADOR");
	}
	
	private void validarRgUnico(Organizador organizador) throws ServiceDacException {
		if (organizadorDAO.existeOutroOrganizadorComMesmoRg(organizador)) {
			throw new ServiceDacException("Já existe um organizador cadastrado com esse rg");
		}
	}

}
