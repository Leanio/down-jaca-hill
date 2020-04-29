package br.edu.ifpb.mt.ads.dac.services.impl;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.edu.ifpb.mt.ads.dac.dao.EstadoDAO;
import br.edu.ifpb.mt.ads.dac.dao.PersistenciaDacException;
import br.edu.ifpb.mt.ads.dac.filters.EstadoFilter;
import br.edu.ifpb.mt.ads.dac.model.Estado;
import br.edu.ifpb.mt.ads.dac.services.EstadoService;
import br.edu.ifpb.mt.ads.dac.services.ServiceDacException;
import br.edu.ifpb.mt.ads.dac.util.interceptors.TransacionalCdi;

@ApplicationScoped
public class EstadoServiceImpl implements EstadoService {

	private static final long serialVersionUID = -1282618445971169946L;
	
	@Inject
	private EstadoDAO estadoDAO;

	@TransacionalCdi
	public void salvar(Estado estado) throws ServiceDacException {
		try {
			validarEstadoUnico(estado);
			
			estadoDAO.salvar(estado);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	@TransacionalCdi
	public void remover(Long codigo) throws ServiceDacException {
		try {
			verificarAssociacaoComCidade(codigo);
			
			estadoDAO.remover(codigo);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	@TransacionalCdi
	public Estado atualizar(Estado estado) throws ServiceDacException {
		try {
			validarEstadoUnico(estado);
			
			return estadoDAO.atualizar(estado);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	@Override
	public Estado buscarPeloCodigo(Long codigo) throws ServiceDacException {
		try {
			return estadoDAO.buscarPeloCodigo(codigo);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	@Override
	public List<Estado> listar() throws ServiceDacException {
		try {
			return estadoDAO.listar();
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	@Override
	public List<Estado> filtrar(EstadoFilter estadoFilter) throws ServiceDacException {
		try {
			return estadoDAO.filtrar(estadoFilter);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}
	
	@Override
	public long total(EstadoFilter estadoFilter) {
		return estadoDAO.total(estadoFilter);
	}
	
	private void validarEstadoUnico(Estado estado) throws ServiceDacException {
		if (estadoDAO.existeOutroEstadoComMesmoNome(estado)) {
			throw new ServiceDacException("Estado já foi cadastrado");
		}
	}
	
	private void verificarAssociacaoComCidade(Long codigo) throws ServiceDacException {
		if (estadoDAO.existeAssociacaoComCidade(codigo)) {
			throw new ServiceDacException("O estado está associado com cidades");
		}
	}
}
