package br.edu.ifpb.mt.ads.dac.dao;

import java.util.List;

import br.edu.ifpb.mt.ads.dac.filters.RecursoFilter;
import br.edu.ifpb.mt.ads.dac.model.Recurso;

public interface RecursoDAO extends DAO<Recurso, Long> {
	
	public List<Recurso> filtrar(RecursoFilter recursoFilter);

	public boolean existeOutroRecursoComMesmoNome(Recurso recurso);
	
	public boolean existeAssociacaoComItemRecurso(Long codigo);
	
	public long total(RecursoFilter recursoFilter);
	
}
