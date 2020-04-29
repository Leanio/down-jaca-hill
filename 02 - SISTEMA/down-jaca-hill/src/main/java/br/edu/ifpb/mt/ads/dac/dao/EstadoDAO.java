package br.edu.ifpb.mt.ads.dac.dao;

import java.util.List;

import br.edu.ifpb.mt.ads.dac.filters.EstadoFilter;
import br.edu.ifpb.mt.ads.dac.model.Estado;

public interface EstadoDAO extends DAO<Estado, Long> {

	public List<Estado> filtrar(EstadoFilter estadoFilter) throws PersistenciaDacException;
	
	public boolean existeOutroEstadoComMesmoNome(Estado estado);
	
	public boolean existeAssociacaoComCidade(Long codigo);
	
	public long total(EstadoFilter estadoFilter);
	
}
