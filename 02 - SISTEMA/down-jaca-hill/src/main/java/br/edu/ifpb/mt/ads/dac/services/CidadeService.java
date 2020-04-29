package br.edu.ifpb.mt.ads.dac.services;

import java.util.List;

import br.edu.ifpb.mt.ads.dac.filters.CidadeFilter;
import br.edu.ifpb.mt.ads.dac.model.Cidade;

public interface CidadeService extends Service<Cidade, Long> {

	public List<Cidade> filtrar(CidadeFilter cidadeFilter) throws ServiceDacException;

	public long total(CidadeFilter cidadeFilter);

}
