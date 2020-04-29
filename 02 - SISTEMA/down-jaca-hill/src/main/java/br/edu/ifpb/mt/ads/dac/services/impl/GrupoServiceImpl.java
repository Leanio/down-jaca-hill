package br.edu.ifpb.mt.ads.dac.services.impl;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.edu.ifpb.mt.ads.dac.dao.GrupoDAO;
import br.edu.ifpb.mt.ads.dac.dao.PersistenciaDacException;
import br.edu.ifpb.mt.ads.dac.model.Grupo;
import br.edu.ifpb.mt.ads.dac.services.GrupoService;
import br.edu.ifpb.mt.ads.dac.services.ServiceDacException;
import br.edu.ifpb.mt.ads.dac.util.interceptors.TransacionalCdi;

@ApplicationScoped
public class GrupoServiceImpl implements GrupoService {

	private static final long serialVersionUID = -4130937020226268438L;
	
	@Inject
	private GrupoDAO grupoDAO;

	@TransacionalCdi
	public void salvar(Grupo grupo) throws ServiceDacException {
		try {
			validarGrupoUnico(grupo);
			
			grupoDAO.salvar(grupo);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	@TransacionalCdi
	public void remover(Long codigo) throws ServiceDacException {
		// TODO Auto-generated method stub
		
	}

	@TransacionalCdi
	public Grupo atualizar(Grupo grupo) throws ServiceDacException {
		try {
			validarGrupoUnico(grupo);
			
			return grupoDAO.atualizar(grupo);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	@Override
	public Grupo buscarPeloCodigo(Long codigo) throws ServiceDacException {
		try {
			return grupoDAO.buscarPeloCodigo(codigo);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	@Override
	public List<Grupo> listar() throws ServiceDacException {
		try {
			return grupoDAO.listar();
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}
	
	private void validarGrupoUnico(Grupo grupo) throws ServiceDacException {
		if (grupoDAO.existeOutroGrupoComMesmoNome(grupo)) {
			throw new ServiceDacException("Já existe um grupo cadastrado com esse nome");
		}
	}

}
