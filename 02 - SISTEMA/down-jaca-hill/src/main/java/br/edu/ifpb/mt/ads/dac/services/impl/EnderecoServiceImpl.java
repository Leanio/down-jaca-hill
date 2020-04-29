package br.edu.ifpb.mt.ads.dac.services.impl;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.edu.ifpb.mt.ads.dac.dao.EnderecoDAO;
import br.edu.ifpb.mt.ads.dac.dao.PersistenciaDacException;
import br.edu.ifpb.mt.ads.dac.model.Endereco;
import br.edu.ifpb.mt.ads.dac.services.EnderecoService;
import br.edu.ifpb.mt.ads.dac.services.ServiceDacException;
import br.edu.ifpb.mt.ads.dac.util.interceptors.TransacionalCdi;

@ApplicationScoped
public class EnderecoServiceImpl implements EnderecoService {

	private static final long serialVersionUID = 2606475034037748830L;
	
	@Inject
	private EnderecoDAO enderecoDAO;

	@TransacionalCdi
	public void salvar(Endereco endereco) throws ServiceDacException {
		try {
			enderecoDAO.salvar(endereco);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
		
	}

	@TransacionalCdi
	public void remover(Long codigo) throws ServiceDacException {
		// TODO Auto-generated method stub
		
	}

	@TransacionalCdi
	public Endereco atualizar(Endereco endereco) throws ServiceDacException {
		try {
			return enderecoDAO.atualizar(endereco);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	@Override
	public Endereco buscarPeloCodigo(Long codigo) throws ServiceDacException {
		try {
			return enderecoDAO.buscarPeloCodigo(codigo);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	@Override
	public List<Endereco> listar() throws ServiceDacException {
		try {
			return enderecoDAO.listar();
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

}
