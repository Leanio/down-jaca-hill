package br.edu.ifpb.mt.ads.dac.services.impl;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.edu.ifpb.mt.ads.dac.dao.PersistenciaDacException;
import br.edu.ifpb.mt.ads.dac.dao.PessoaDAO;
import br.edu.ifpb.mt.ads.dac.model.Conta;
import br.edu.ifpb.mt.ads.dac.model.Pessoa;
import br.edu.ifpb.mt.ads.dac.services.RoleService;
import br.edu.ifpb.mt.ads.dac.services.PessoaService;
import br.edu.ifpb.mt.ads.dac.services.ServiceDacException;
import br.edu.ifpb.mt.ads.dac.util.interceptors.TransacionalCdi;

@ApplicationScoped
public class PessoaServiceImpl implements PessoaService {

	private static final long serialVersionUID = -4349798455278372380L;
	
	@Inject
	private PessoaDAO pessoaDAO;
	
	@Inject
	private RoleService roleService;

	@TransacionalCdi
	public void salvar(Pessoa pessoa) throws ServiceDacException {		
		try {
			validarCpfUnico(pessoa);
			
			Conta conta = pessoa.getConta();
			roleService.adicionarRoleDeGrupo(conta, "PESSOA");
			
			pessoaDAO.salvar(pessoa);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	@TransacionalCdi
	public void remover(Long codigo) throws ServiceDacException {
		// TODO Auto-generated method stub
		
	}

	@TransacionalCdi
	public Pessoa atualizar(Pessoa pessoa) throws ServiceDacException {
		try {
			validarCpfUnico(pessoa);
			
			return pessoaDAO.atualizar(pessoa);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	@Override
	public Pessoa buscarPeloCodigo(Long codigo) throws ServiceDacException {
		try {
			return pessoaDAO.buscarPeloCodigo(codigo);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	@Override
	public List<Pessoa> listar() throws ServiceDacException {
		try {
			return pessoaDAO.listar();
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	private void validarCpfUnico(Pessoa pessoa) throws ServiceDacException {
		if (pessoaDAO.existeOutraPessoaComMesmoCpf(pessoa)) {
			throw new ServiceDacException("CPF em uso!");
		}
	}
	
}
