package br.edu.ifpb.mt.ads.dac.beans.model.conta;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.ads.dac.beans.AbstractBean;
import br.edu.ifpb.mt.ads.dac.beans.EnderecoPaginas;
import br.edu.ifpb.mt.ads.dac.model.Conta;
import br.edu.ifpb.mt.ads.dac.model.TokenRedefinicaoSenha;
import br.edu.ifpb.mt.ads.dac.services.ContaService;
import br.edu.ifpb.mt.ads.dac.services.ServiceDacException;
import br.edu.ifpb.mt.ads.dac.services.TokenRedefinicaoSenhaService;

@Named
@ViewScoped
public class ContaEditSenhaToken extends AbstractBean {

	private static final long serialVersionUID = -5934861069869456350L;
	
	@Inject
	private ContaService contaService;
	
	@Inject
	private TokenRedefinicaoSenhaService tokenRedefinicaoSenhaService;
	
	private TokenRedefinicaoSenha tokenRedefinicaoSenha;
	
	private Conta conta;
	
	private String token;

	private String novaSenhaSemHash;
	
	public String init() {
		try {
			tokenRedefinicaoSenha = tokenRedefinicaoSenhaService.buscarPeloToken(token);
			
			conta = tokenRedefinicaoSenha.getConta();
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return EnderecoPaginas.PAGINA_PRINCIPAL_AUTENTICADO;
		}
		
		return null;
	}
	
	public String atualizar() {
		try {
			contaService.atualizarSenhaComToken(conta, novaSenhaSemHash);
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		
		reportarMensagemDeSucesso("Senha atualizada");
		return EnderecoPaginas.PAGINA_PRINCIPAL_AUTENTICADO;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getNovaSenhaSemHash() {
		return novaSenhaSemHash;
	}

	public void setNovaSenhaSemHash(String novaSenhaSemHash) {
		this.novaSenhaSemHash = novaSenhaSemHash;
	}

}
