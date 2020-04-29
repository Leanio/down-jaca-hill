package br.edu.ifpb.mt.ads.dac.dao;

import br.edu.ifpb.mt.ads.dac.model.Organizador;

public interface OrganizadorDAO extends DAO<Organizador, Long> {

	public boolean existeOutroOrganizadorComMesmoRg(Organizador organizador);
	
}
