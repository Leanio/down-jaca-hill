package br.edu.ifpb.mt.ads.dac.dao;

import java.util.List;

import br.edu.ifpb.mt.ads.dac.filters.EventoFilter;
import br.edu.ifpb.mt.ads.dac.model.Evento;

public interface EventoDAO extends DAO<Evento, Long> {

	public List<Evento> filtrar(EventoFilter eventoFilter);
	
	public boolean eventoPossuiVagaDisponivel(Evento evento);
	
	public boolean existeAssociacaoComPedidoParticipacao(Long codigo);
	
	public long total(EventoFilter eventoFilter);
	
}
