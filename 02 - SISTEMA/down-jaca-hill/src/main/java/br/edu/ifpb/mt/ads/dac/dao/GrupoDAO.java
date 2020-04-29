package br.edu.ifpb.mt.ads.dac.dao;

import br.edu.ifpb.mt.ads.dac.model.Grupo;

public interface GrupoDAO extends DAO<Grupo, Long> {
	
	public Grupo buscarPeloNome(String nome) throws PersistenciaDacException;
	
	public boolean existeOutroGrupoComMesmoNome(Grupo grupo);

}
