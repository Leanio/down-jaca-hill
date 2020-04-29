package br.edu.ifpb.mt.ads.dac.services;

import java.util.List;

import br.edu.ifpb.mt.ads.dac.filters.MarcaFilter;
import br.edu.ifpb.mt.ads.dac.model.Marca;

public interface MarcaService extends Service<Marca, Long> {

	public List<Marca> filtrar(MarcaFilter marcaFilter) throws ServiceDacException;

	public long total(MarcaFilter marcaFilter);

}
