package br.edu.ifpb.mt.ads.dac.services;

import java.util.List;

import br.edu.ifpb.mt.ads.dac.filters.ParticipacaoFilter;
import br.edu.ifpb.mt.ads.dac.model.Evento;
import br.edu.ifpb.mt.ads.dac.model.Participacao;

public interface ParticipacaoService extends Service<Participacao, Long> {

	public List<Participacao> filtrar(ParticipacaoFilter participacaoFilter) throws ServiceDacException;
	
	public List<Participacao> listaOrdenadaPeloTempoGasto(Evento evento) throws ServiceDacException;

	public long total(ParticipacaoFilter participacaoFilter) throws ServiceDacException;
}
