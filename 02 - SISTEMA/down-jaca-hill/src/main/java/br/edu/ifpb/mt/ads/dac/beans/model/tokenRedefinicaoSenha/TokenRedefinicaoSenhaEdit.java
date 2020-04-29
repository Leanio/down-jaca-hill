package br.edu.ifpb.mt.ads.dac.beans.model.tokenRedefinicaoSenha;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.ads.dac.beans.AbstractBean;
import br.edu.ifpb.mt.ads.dac.beans.EnderecoPaginas;
import br.edu.ifpb.mt.ads.dac.model.Conta;
import br.edu.ifpb.mt.ads.dac.model.TokenRedefinicaoSenha;
import br.edu.ifpb.mt.ads.dac.services.ContaService;
import br.edu.ifpb.mt.ads.dac.services.EmailService;
import br.edu.ifpb.mt.ads.dac.services.ServiceDacException;
import br.edu.ifpb.mt.ads.dac.services.TokenRedefinicaoSenhaService;

@Named
@ViewScoped
public class TokenRedefinicaoSenhaEdit extends AbstractBean {

	private static final long serialVersionUID = -5934861069869456350L;

	@Inject
	private ContaService contaService;

	@Inject
	private TokenRedefinicaoSenhaService tokenRedefinicaoSenhaService;

	@Inject
	private EmailService emailService;

	private String login;

	public String gerarToken() {
		try {
			Conta conta = contaService.buscarPeloLogin(login);

			TokenRedefinicaoSenha tokenRedefinicaoSenhaAtual = tokenRedefinicaoSenhaService.buscarPelaConta(conta);

			if (tokenRedefinicaoSenhaAtual != null) {
				Long codigo = tokenRedefinicaoSenhaAtual.getCodigo();
				tokenRedefinicaoSenhaService.remover(codigo);
			}

			TokenRedefinicaoSenha tokenRedefinicaoSenha = new TokenRedefinicaoSenha();
			tokenRedefinicaoSenha.setConta(conta);

			tokenRedefinicaoSenhaService.salvar(tokenRedefinicaoSenha);

			emailService.enviarEmailTokenRedefinicaoSenha(conta);
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}

		reportarMensagemDeSucesso("Instrução para redefinição de senha enviado para seu e-mail");
		return EnderecoPaginas.PAGINA_PRINCIPAL_AUTENTICADO;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

}
