package br.edu.ifpb.mt.ads.dac.services;

import java.util.List;

import br.edu.ifpb.mt.ads.dac.filters.RecursoFilter;
import br.edu.ifpb.mt.ads.dac.model.Recurso;

public interface RecursoService extends Service<Recurso, Long> {

	public List<Recurso> filtrar(RecursoFilter recursoFilter) throws ServiceDacException;

	public long total(RecursoFilter recursoFilter);

}
