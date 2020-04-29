package br.edu.ifpb.mt.ads.dac.services.impl;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.edu.ifpb.mt.ads.dac.dao.PersistenciaDacException;
import br.edu.ifpb.mt.ads.dac.dao.RecursoDAO;
import br.edu.ifpb.mt.ads.dac.filters.RecursoFilter;
import br.edu.ifpb.mt.ads.dac.model.Recurso;
import br.edu.ifpb.mt.ads.dac.services.RecursoService;
import br.edu.ifpb.mt.ads.dac.services.ServiceDacException;
import br.edu.ifpb.mt.ads.dac.util.interceptors.TransacionalCdi;

@ApplicationScoped
public class RecursoServiceImpl implements RecursoService {

	private static final long serialVersionUID = -6996237929631021150L;
	
	@Inject
	private RecursoDAO recursoDAO;

	@TransacionalCdi
	public void salvar(Recurso recurso) throws ServiceDacException {
		try {
			validarRecursoUnico(recurso);
			
			recursoDAO.salvar(recurso);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	@TransacionalCdi
	public void remover(Long codigo) throws ServiceDacException {
		try {
			verificarAssociacaoComItemRecurso(codigo);
			
			recursoDAO.remover(codigo);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	@TransacionalCdi
	public Recurso atualizar(Recurso recurso) throws ServiceDacException {
		try {
			validarRecursoUnico(recurso);
			
			return recursoDAO.atualizar(recurso);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	@Override
	public Recurso buscarPeloCodigo(Long codigo) throws ServiceDacException {
		try {
			return recursoDAO.buscarPeloCodigo(codigo);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	@Override
	public List<Recurso> listar() throws ServiceDacException {
		try {
			return recursoDAO.listar();
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}
	
	@Override
	public List<Recurso> filtrar(RecursoFilter recursoFilter) throws ServiceDacException {
		return recursoDAO.filtrar(recursoFilter);
	}
	
	@Override
	public long total(RecursoFilter recursoFilter) {
		return recursoDAO.total(recursoFilter);
	}
	
	private void validarRecursoUnico(Recurso recurso) throws ServiceDacException {
		if (recursoDAO.existeOutroRecursoComMesmoNome(recurso)) {
			throw new ServiceDacException("Já existe um recurso cadastrado com esse nome");
		}
	}
	
	private void verificarAssociacaoComItemRecurso(Long codigo) throws ServiceDacException {
		if (recursoDAO.existeAssociacaoComItemRecurso(codigo)) {
			throw new ServiceDacException("Existe associação com item de recurso");
		}
	}

}
