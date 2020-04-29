package br.edu.ifpb.mt.ads.dac.dao;

import java.util.List;

import br.edu.ifpb.mt.ads.dac.filters.ModeloFilter;
import br.edu.ifpb.mt.ads.dac.model.Modelo;

public interface ModeloDAO extends DAO<Modelo, Long> {
	
	public List<Modelo> filtrar(ModeloFilter modeloFilter);
	
	public boolean existeOutroModeloComMesmoNomeEMarca(Modelo modelo);
	
	public boolean existeAssociacaoComPedidoParticipacao(Long codigo);
	
	public long total(ModeloFilter modeloFilter);

}
