package br.edu.ifpb.mt.ads.dac.dao;

import br.edu.ifpb.mt.ads.dac.model.Conta;

public interface ContaDAO extends DAO<Conta, Long> {
	
	public Conta buscarPeloLogin(String login) throws PersistenciaDacException;
	
	public boolean existeOutraContaComMesmoLogin(Conta conta);
	
	public boolean existeOutraContaComMesmoEmail(Conta conta);
	
}
