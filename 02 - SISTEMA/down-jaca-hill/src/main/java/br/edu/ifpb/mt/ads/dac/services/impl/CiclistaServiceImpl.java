package br.edu.ifpb.mt.ads.dac.services.impl;

import java.util.Date;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.edu.ifpb.mt.ads.dac.dao.CiclistaDAO;
import br.edu.ifpb.mt.ads.dac.dao.PersistenciaDacException;
import br.edu.ifpb.mt.ads.dac.model.Ciclista;
import br.edu.ifpb.mt.ads.dac.model.Conta;
import br.edu.ifpb.mt.ads.dac.services.CiclistaService;
import br.edu.ifpb.mt.ads.dac.services.RoleService;
import br.edu.ifpb.mt.ads.dac.services.ServiceDacException;
import br.edu.ifpb.mt.ads.dac.util.interceptors.TransacionalCdi;

@ApplicationScoped
public class CiclistaServiceImpl implements CiclistaService {

	private static final long serialVersionUID = 3478478206490250080L;
	
	@Inject
	private CiclistaDAO ciclistaDAO;
	
	@Inject
	private RoleService roleService;

	@TransacionalCdi
	public void salvar(Ciclista ciclista) throws ServiceDacException {
		try {
			Conta conta = ciclista.getPessoa().getConta();
			
			roleService.adicionarRoleDeGrupo(conta, "CICLISTA");
			
			Date dataCadastroPerfilCiclista = new Date();
			
			ciclista.setDataCadastroPerfilCiclista(dataCadastroPerfilCiclista);
			
			ciclistaDAO.salvar(ciclista);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	@TransacionalCdi
	public void remover(Long codigo) throws ServiceDacException {
		// TODO Auto-generated method stub
		
	}

	@TransacionalCdi
	public Ciclista atualizar(Ciclista ciclista) throws ServiceDacException {
		try {
			Conta conta = ciclista.getPessoa().getConta();
			
			roleService.adicionarRoleDeGrupo(conta, "CICLISTA");
			
			return ciclistaDAO.atualizar(ciclista);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	@Override
	public Ciclista buscarPeloCodigo(Long codigo) throws ServiceDacException {
		try {
			return ciclistaDAO.buscarPeloCodigo(codigo);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	@Override
	public List<Ciclista> listar() throws ServiceDacException {
		try {
			return ciclistaDAO.listar();
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}
	
	@Override
	public void desativar(Ciclista ciclista) throws ServiceDacException {
		Conta conta = ciclista.getPessoa().getConta();
		
		roleService.removerRoleDeGrupo(conta, "CICLISTA");
	}

	@Override
	public List<Object[]> ranking() throws ServiceDacException {
		try {
			return ciclistaDAO.ranking();
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

}
