package br.edu.ifpb.mt.ads.dac.services;

import br.edu.ifpb.mt.ads.dac.model.Organizador;

public interface OrganizadorService extends Service<Organizador, Long> {

	public void desativar(Organizador organizador) throws ServiceDacException;
	
}
