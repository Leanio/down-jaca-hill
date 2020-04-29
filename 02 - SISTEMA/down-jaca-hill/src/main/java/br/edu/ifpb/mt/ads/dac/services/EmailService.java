package br.edu.ifpb.mt.ads.dac.services;

import java.io.Serializable;

import br.edu.ifpb.mt.ads.dac.model.Conta;

public interface EmailService extends Serializable {

	public void enviarEmailTokenRedefinicaoSenha(Conta conta);

}
