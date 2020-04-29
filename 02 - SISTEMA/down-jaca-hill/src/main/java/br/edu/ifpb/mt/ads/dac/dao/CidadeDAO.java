package br.edu.ifpb.mt.ads.dac.dao;

import java.util.List;

import br.edu.ifpb.mt.ads.dac.filters.CidadeFilter;
import br.edu.ifpb.mt.ads.dac.model.Cidade;

public interface CidadeDAO extends DAO<Cidade, Long> {
	
	public List<Cidade> filtrar(CidadeFilter cidadeFilter) throws PersistenciaDacException;

	public boolean existeOutraCidadeComMesmoNomeEEstado(Cidade cidade);
	
	public boolean existeAssociacaoComEndereco(Long codigo);
	
	public long total(CidadeFilter cidadeFilter);
	
}
