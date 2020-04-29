package br.edu.ifpb.mt.ads.dac.services.impl;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.edu.ifpb.mt.ads.dac.dao.MarcaDAO;
import br.edu.ifpb.mt.ads.dac.dao.PersistenciaDacException;
import br.edu.ifpb.mt.ads.dac.filters.MarcaFilter;
import br.edu.ifpb.mt.ads.dac.model.Marca;
import br.edu.ifpb.mt.ads.dac.services.MarcaService;
import br.edu.ifpb.mt.ads.dac.services.ServiceDacException;
import br.edu.ifpb.mt.ads.dac.util.interceptors.TransacionalCdi;

@ApplicationScoped
public class MarcaServiceImpl implements MarcaService {

	private static final long serialVersionUID = -8929704463158507908L;
	
	@Inject
	private MarcaDAO marcaDAO;

	@TransacionalCdi
	public void salvar(Marca marca) throws ServiceDacException {
		try {
			validarNomeUnico(marca);
			
			marcaDAO.salvar(marca);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	@TransacionalCdi
	public void remover(Long codigo) throws ServiceDacException {
		try {
			verificarAssociacaoComModelo(codigo);
			
			marcaDAO.remover(codigo);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	@TransacionalCdi
	public Marca atualizar(Marca marca) throws ServiceDacException {
		try {
			validarNomeUnico(marca);
			
			return marcaDAO.atualizar(marca);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	@Override
	public Marca buscarPeloCodigo(Long codigo) throws ServiceDacException {
		try {
			return marcaDAO.buscarPeloCodigo(codigo);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	@Override
	public List<Marca> listar() throws ServiceDacException {
		try {
			return marcaDAO.listar();
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}
	
	@Override
	public List<Marca> filtrar(MarcaFilter marcaFilter) throws ServiceDacException {
		return marcaDAO.filtrar(marcaFilter);
	}
	
	@Override
	public long total(MarcaFilter marcaFilter) {
		return marcaDAO.total(marcaFilter);
	}
	
	private void validarNomeUnico(Marca marca) throws ServiceDacException {
		if (marcaDAO.existeOutraMarcaComMesmoNome(marca)) {
			throw new ServiceDacException("Já existe uma marca com esse nome");
		}
	}
	
	private void verificarAssociacaoComModelo(Long codigo) throws ServiceDacException {
		if (marcaDAO.existeAssociacaoComModelo(codigo)) {
			throw new ServiceDacException("Existe associação com modelo");
		}
	}
	
}
