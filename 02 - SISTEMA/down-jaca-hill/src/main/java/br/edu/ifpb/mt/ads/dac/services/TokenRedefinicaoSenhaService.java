package br.edu.ifpb.mt.ads.dac.services;

import br.edu.ifpb.mt.ads.dac.model.Conta;
import br.edu.ifpb.mt.ads.dac.model.TokenRedefinicaoSenha;

public interface TokenRedefinicaoSenhaService extends Service<TokenRedefinicaoSenha, Long> {

	public TokenRedefinicaoSenha buscarPelaConta(Conta conta) throws ServiceDacException;

	public TokenRedefinicaoSenha buscarPeloToken(String token) throws ServiceDacException;

}
