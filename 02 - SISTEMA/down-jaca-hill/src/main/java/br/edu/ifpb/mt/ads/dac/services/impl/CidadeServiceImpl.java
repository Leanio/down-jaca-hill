package br.edu.ifpb.mt.ads.dac.services.impl;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.edu.ifpb.mt.ads.dac.dao.CidadeDAO;
import br.edu.ifpb.mt.ads.dac.dao.PersistenciaDacException;
import br.edu.ifpb.mt.ads.dac.filters.CidadeFilter;
import br.edu.ifpb.mt.ads.dac.model.Cidade;
import br.edu.ifpb.mt.ads.dac.services.CidadeService;
import br.edu.ifpb.mt.ads.dac.services.ServiceDacException;
import br.edu.ifpb.mt.ads.dac.util.interceptors.TransacionalCdi;

@ApplicationScoped
public class CidadeServiceImpl implements CidadeService {

	private static final long serialVersionUID = -9211497680140572666L;
	
	@Inject
	private CidadeDAO cidadeDAO;

	@TransacionalCdi
	public void salvar(Cidade cidade) throws ServiceDacException {
		try {
			validarCidadeUnica(cidade);
			
			cidadeDAO.salvar(cidade);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	@TransacionalCdi
	public void remover(Long codigo) throws ServiceDacException {
		try {
			verificarAssociacaoComEndereco(codigo);
			
			cidadeDAO.remover(codigo);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	@TransacionalCdi
	public Cidade atualizar(Cidade cidade) throws ServiceDacException {		
		try {
			validarCidadeUnica(cidade);
			
			return cidadeDAO.atualizar(cidade);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	@Override
	public Cidade buscarPeloCodigo(Long codigo) throws ServiceDacException {
		try {
			return cidadeDAO.buscarPeloCodigo(codigo);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	@Override
	public List<Cidade> listar() throws ServiceDacException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cidade> filtrar(CidadeFilter cidadeFilter) throws ServiceDacException {
		try {
			return cidadeDAO.filtrar(cidadeFilter);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}
	
	@Override
	public long total(CidadeFilter cidadeFilter) {
		return cidadeDAO.total(cidadeFilter);
	}

	private void validarCidadeUnica(Cidade cidade) throws ServiceDacException {
		if (cidadeDAO.existeOutraCidadeComMesmoNomeEEstado(cidade)) {
			throw new ServiceDacException("Cidade já foi cadastrada");
		}
	}
	
	private void verificarAssociacaoComEndereco(Long codigo) throws ServiceDacException {
		if (cidadeDAO.existeAssociacaoComEndereco(codigo)) {
			throw new ServiceDacException("Existe associação com endereço");
		}
	}
	
}
