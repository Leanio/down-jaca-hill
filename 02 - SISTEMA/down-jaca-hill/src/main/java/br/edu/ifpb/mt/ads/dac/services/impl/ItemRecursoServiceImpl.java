package br.edu.ifpb.mt.ads.dac.services.impl;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import br.edu.ifpb.mt.ads.dac.dao.ItemRecursoDAO;
import br.edu.ifpb.mt.ads.dac.dao.PersistenciaDacException;
import br.edu.ifpb.mt.ads.dac.model.ItemRecurso;
import br.edu.ifpb.mt.ads.dac.services.ItemRecursoService;
import br.edu.ifpb.mt.ads.dac.services.ServiceDacException;
import br.edu.ifpb.mt.ads.dac.util.interceptors.TransacionalCdi;

@ApplicationScoped
public class ItemRecursoServiceImpl implements ItemRecursoService {

	private static final long serialVersionUID = 4099162855805075537L;
	
	private ItemRecursoDAO itemRecursoDAO;

	@TransacionalCdi
	public void salvar(ItemRecurso itemRecurso) throws ServiceDacException {
		try {
			itemRecursoDAO.salvar(itemRecurso);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	@TransacionalCdi
	public void remover(Long codigo) throws ServiceDacException {
		// TODO Auto-generated method stub
		
	}

	@TransacionalCdi
	public ItemRecurso atualizar(ItemRecurso itemRecurso) throws ServiceDacException {
		try {
			return itemRecursoDAO.atualizar(itemRecurso);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	@Override
	public ItemRecurso buscarPeloCodigo(Long codigo) throws ServiceDacException {
		try {
			return itemRecursoDAO.buscarPeloCodigo(codigo);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	@Override
	public List<ItemRecurso> listar() throws ServiceDacException {
		try {
			return itemRecursoDAO.listar();
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

}
