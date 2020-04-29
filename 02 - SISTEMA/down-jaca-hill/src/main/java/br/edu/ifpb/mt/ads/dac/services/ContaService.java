package br.edu.ifpb.mt.ads.dac.services;

import br.edu.ifpb.mt.ads.dac.model.Conta;

public interface ContaService extends Service<Conta, Long> {

	public Conta buscarPeloLogin(String login) throws ServiceDacException;;

	public Conta atualizarSenha(Conta conta, String supostaSenhaSemHash, String novaSenhaSemHash) throws ServiceDacException;

	public Conta atualizarSenhaComToken(Conta conta, String novaSenhaSemHash) throws ServiceDacException;

}
