package br.edu.ifpb.mt.ads.dac.services.impl;

import javax.enterprise.context.ApplicationScoped;

import br.edu.ifpb.mt.ads.dac.beans.EnderecoPaginas;
import br.edu.ifpb.mt.ads.dac.model.Conta;
import br.edu.ifpb.mt.ads.dac.services.EmailService;

import static br.edu.ifpb.mt.ads.dac.util.email.Email.enviar;

@ApplicationScoped
public class EmailServiceImpl implements EmailService {

	private static final long serialVersionUID = 6422678042251953604L;

	@Override
	public void enviarEmailTokenRedefinicaoSenha(Conta conta) {
		String assunto = "Recuperação de conta";
		String mensagem = EnderecoPaginas.LINK_APLICACAO + EnderecoPaginas.PAGINA_PRINCIPAL_REDEFINICAO_SENHA_TOKEN
				+ conta.getTokenRedefinicaoSenha().getToken();
		String destinatario = conta.getEmail();

		enviar(assunto, mensagem, destinatario);
	}

}
