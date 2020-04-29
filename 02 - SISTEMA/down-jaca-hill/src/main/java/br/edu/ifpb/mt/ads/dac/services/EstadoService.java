package br.edu.ifpb.mt.ads.dac.services;

import java.util.List;

import br.edu.ifpb.mt.ads.dac.filters.EstadoFilter;
import br.edu.ifpb.mt.ads.dac.model.Estado;

public interface EstadoService extends Service<Estado, Long> {

	public List<Estado> filtrar(EstadoFilter filter) throws ServiceDacException;

	public long total(EstadoFilter estadoFilter);

}
