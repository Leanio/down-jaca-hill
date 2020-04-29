package br.edu.ifpb.mt.ads.dac.services.impl;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.edu.ifpb.mt.ads.dac.dao.PersistenciaDacException;
import br.edu.ifpb.mt.ads.dac.dao.PremiacaoDAO;
import br.edu.ifpb.mt.ads.dac.model.Premiacao;
import br.edu.ifpb.mt.ads.dac.services.PremiacaoService;
import br.edu.ifpb.mt.ads.dac.services.ServiceDacException;
import br.edu.ifpb.mt.ads.dac.util.interceptors.TransacionalCdi;

@ApplicationScoped
public class PremiacaoServiceImpl implements PremiacaoService {

	private static final long serialVersionUID = 6343024020925849327L;
	
	@Inject
	private PremiacaoDAO premiacaoDAO;

	@TransacionalCdi
	public void salvar(Premiacao premiacao) throws ServiceDacException {
		try {
			premiacaoDAO.salvar(premiacao);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	@TransacionalCdi
	public void remover(Long codigo) throws ServiceDacException {
		// TODO Auto-generated method stub
		
	}

	@TransacionalCdi
	public Premiacao atualizar(Premiacao premiacao) throws ServiceDacException {
		try {
			return premiacaoDAO.atualizar(premiacao);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	@Override
	public Premiacao buscarPeloCodigo(Long codigo) throws ServiceDacException {
		try {
			return premiacaoDAO.buscarPeloCodigo(codigo);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	@Override
	public List<Premiacao> listar() throws ServiceDacException {
		try {
			return premiacaoDAO.listar();
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

}
