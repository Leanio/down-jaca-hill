package br.edu.ifpb.mt.ads.dac.services.impl;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.edu.ifpb.mt.ads.dac.dao.ParticipacaoDAO;
import br.edu.ifpb.mt.ads.dac.dao.PersistenciaDacException;
import br.edu.ifpb.mt.ads.dac.filters.ParticipacaoFilter;
import br.edu.ifpb.mt.ads.dac.model.Evento;
import br.edu.ifpb.mt.ads.dac.model.Participacao;
import br.edu.ifpb.mt.ads.dac.services.ParticipacaoService;
import br.edu.ifpb.mt.ads.dac.services.ServiceDacException;
import br.edu.ifpb.mt.ads.dac.util.interceptors.TransacionalCdi;

@ApplicationScoped
public class ParticipacaoServiceImpl implements ParticipacaoService {

	private static final long serialVersionUID = 4822604385275325048L;
	
	@Inject
	private ParticipacaoDAO participacaoDAO;

	@TransacionalCdi
	public void salvar(Participacao participacao) throws ServiceDacException {
		try {
			participacaoDAO.salvar(participacao);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
		
	}

	@TransacionalCdi
	public void remover(Long codigo) throws ServiceDacException {
		try {
			participacaoDAO.remover(codigo);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
		
	}

	@TransacionalCdi
	public Participacao atualizar(Participacao participacao) throws ServiceDacException {
		try {
			return participacaoDAO.atualizar(participacao);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	@Override
	public Participacao buscarPeloCodigo(Long codigo) throws ServiceDacException {
		try {
			return participacaoDAO.buscarPeloCodigo(codigo);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	@Override
	public List<Participacao> listar() throws ServiceDacException {
		try {
			return participacaoDAO.listar();
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	@Override
	public List<Participacao> filtrar(ParticipacaoFilter participacaoFilter) throws ServiceDacException {
		try {
			return participacaoDAO.filtrar(participacaoFilter);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}
	
	@Override
	public List<Participacao> listaOrdenadaPeloTempoGasto(Evento evento) throws ServiceDacException {
		try {
			return participacaoDAO.listaOrdenadaPeloTempoGasto(evento);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	@Override
	public long total(ParticipacaoFilter participacaoFilter) throws ServiceDacException {
		try {
			return participacaoDAO.total(participacaoFilter);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

}
