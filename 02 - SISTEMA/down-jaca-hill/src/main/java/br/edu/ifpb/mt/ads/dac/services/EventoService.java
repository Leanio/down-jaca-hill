package br.edu.ifpb.mt.ads.dac.services;

import java.util.List;

import br.edu.ifpb.mt.ads.dac.filters.EventoFilter;
import br.edu.ifpb.mt.ads.dac.model.Evento;

public interface EventoService extends Service<Evento, Long> {

	public List<Evento> filtrar(EventoFilter eventoFilter) throws ServiceDacException;

	public long total(EventoFilter eventoFilter);
	
	public void definirVencedores(Evento evento) throws ServiceDacException;

	void decrementarVaga(Evento evento) throws ServiceDacException;

	void incrementarVaga(Evento evento) throws ServiceDacException;

	void validarEventoComVaga(Evento evento) throws ServiceDacException;

}
