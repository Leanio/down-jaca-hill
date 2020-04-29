package br.edu.ifpb.mt.ads.dac.services.impl;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.edu.ifpb.mt.ads.dac.dao.ModeloDAO;
import br.edu.ifpb.mt.ads.dac.dao.PersistenciaDacException;
import br.edu.ifpb.mt.ads.dac.filters.ModeloFilter;
import br.edu.ifpb.mt.ads.dac.model.Modelo;
import br.edu.ifpb.mt.ads.dac.services.ModeloService;
import br.edu.ifpb.mt.ads.dac.services.ServiceDacException;
import br.edu.ifpb.mt.ads.dac.util.interceptors.TransacionalCdi;

@ApplicationScoped
public class ModeloServiceImpl implements ModeloService {

	private static final long serialVersionUID = 3308247879949613445L;
	
	@Inject
	private ModeloDAO modeloDAO;

	@TransacionalCdi
	public void salvar(Modelo modelo) throws ServiceDacException {
		try {
			validarNomeEMarcaUnico(modelo);
			
			modeloDAO.salvar(modelo);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	@TransacionalCdi
	public void remover(Long codigo) throws ServiceDacException {
		try {
			verificarAssociacaoComPedidoParticipacao(codigo);
			
			modeloDAO.remover(codigo);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	@TransacionalCdi
	public Modelo atualizar(Modelo modelo) throws ServiceDacException {
		try {
			validarNomeEMarcaUnico(modelo);
			
			return modeloDAO.atualizar(modelo);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	@Override
	public Modelo buscarPeloCodigo(Long codigo) throws ServiceDacException {
		try {
			return modeloDAO.buscarPeloCodigo(codigo);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	@Override
	public List<Modelo> listar() throws ServiceDacException {
		try {
			return modeloDAO.listar();
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}
	
	@Override
	public List<Modelo> filtrar(ModeloFilter modeloFilter) throws ServiceDacException {
		return modeloDAO.filtrar(modeloFilter);
	}
	
	@Override
	public long total(ModeloFilter modeloFilter) {
		return modeloDAO.total(modeloFilter);
	}
	
	private void validarNomeEMarcaUnico(Modelo modelo) throws ServiceDacException {
		if (modeloDAO.existeOutroModeloComMesmoNomeEMarca(modelo)) {
			throw new ServiceDacException("Já existe um modelo com esse nome e marca");
		}
	}
	
	private void verificarAssociacaoComPedidoParticipacao(Long codigo) throws ServiceDacException {
		if (modeloDAO.existeAssociacaoComPedidoParticipacao(codigo)) {
			throw new ServiceDacException("Existe associação com pedido de participção");
		}
	}
	
}
