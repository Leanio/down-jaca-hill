package br.edu.ifpb.mt.ads.dac.dao;

import java.util.List;

import br.edu.ifpb.mt.ads.dac.model.Ciclista;

public interface CiclistaDAO extends DAO<Ciclista, Long> {
	
	public List<Object[]> ranking() throws PersistenciaDacException;

}
