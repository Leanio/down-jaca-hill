package br.edu.ifpb.mt.ads.dac.services;

import java.util.List;

import br.edu.ifpb.mt.ads.dac.filters.ModeloFilter;
import br.edu.ifpb.mt.ads.dac.model.Modelo;

public interface ModeloService extends Service<Modelo, Long> {

	public List<Modelo> filtrar(ModeloFilter modeloFilter) throws ServiceDacException;

	public long total(ModeloFilter modeloFilter);

}
