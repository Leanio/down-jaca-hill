package br.edu.ifpb.mt.ads.dac.dao;

import java.util.List;

import br.edu.ifpb.mt.ads.dac.filters.ParticipacaoFilter;
import br.edu.ifpb.mt.ads.dac.model.Evento;
import br.edu.ifpb.mt.ads.dac.model.Participacao;

public interface ParticipacaoDAO extends DAO<Participacao, Long> {

	public List<Participacao> filtrar(ParticipacaoFilter participacaoFilter) throws PersistenciaDacException;
	
	public List<Participacao> listaOrdenadaPeloTempoGasto(Evento evento) throws PersistenciaDacException;
	
	public long total(ParticipacaoFilter participacaoFilter) throws PersistenciaDacException;
	
}
