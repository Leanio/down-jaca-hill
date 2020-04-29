package br.edu.ifpb.mt.ads.dac.services;

import java.util.List;

import br.edu.ifpb.mt.ads.dac.model.Ciclista;

public interface CiclistaService extends Service<Ciclista, Long>  {

	public List<Object[]> ranking() throws ServiceDacException;
	
	public void desativar(Ciclista ciclista) throws ServiceDacException;
	
}
