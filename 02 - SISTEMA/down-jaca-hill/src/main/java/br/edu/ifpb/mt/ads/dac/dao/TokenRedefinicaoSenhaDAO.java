package br.edu.ifpb.mt.ads.dac.dao;

import br.edu.ifpb.mt.ads.dac.model.Conta;
import br.edu.ifpb.mt.ads.dac.model.TokenRedefinicaoSenha;

public interface TokenRedefinicaoSenhaDAO extends DAO<TokenRedefinicaoSenha, Long> {
	
	public TokenRedefinicaoSenha buscarPelaConta(Conta conta) throws PersistenciaDacException;
	
	public TokenRedefinicaoSenha buscarPeloToken(String token) throws PersistenciaDacException;
	
}
