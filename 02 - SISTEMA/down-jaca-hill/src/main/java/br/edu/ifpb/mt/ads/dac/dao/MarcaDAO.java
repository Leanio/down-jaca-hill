package br.edu.ifpb.mt.ads.dac.dao;

import java.util.List;

import br.edu.ifpb.mt.ads.dac.filters.MarcaFilter;
import br.edu.ifpb.mt.ads.dac.model.Marca;

public interface MarcaDAO extends DAO<Marca, Long> {

	public List<Marca> filtrar(MarcaFilter marcaFilter);
	
	public boolean existeOutraMarcaComMesmoNome(Marca marca);
	
	public boolean existeAssociacaoComModelo(Long codigo);
	
	public long total(MarcaFilter marcaFilter);
	
}
