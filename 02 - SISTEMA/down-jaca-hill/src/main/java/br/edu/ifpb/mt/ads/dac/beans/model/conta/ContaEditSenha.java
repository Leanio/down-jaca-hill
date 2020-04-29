package br.edu.ifpb.mt.ads.dac.beans.model.conta;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.ads.dac.beans.AbstractBean;
import br.edu.ifpb.mt.ads.dac.beans.EnderecoPaginas;
import br.edu.ifpb.mt.ads.dac.model.Conta;
import br.edu.ifpb.mt.ads.dac.services.ContaService;
import br.edu.ifpb.mt.ads.dac.services.ServiceDacException;
import br.edu.ifpb.mt.ads.dac.util.qualifiers.ContaAutenticada;

@Named
@ViewScoped
public class ContaEditSenha extends AbstractBean {

	private static final long serialVersionUID = 2887503404660812827L;

	@Inject
	private ContaService contaService;

	@Inject
	@ContaAutenticada
	private Conta conta;

	private String supostaSenhaSemHash;

	private String novaSenhaSemHash;

	public String atualizar() {
		try {
			contaService.atualizarSenha(conta, supostaSenhaSemHash, novaSenhaSemHash);
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getLocalizedMessage());
			return null;
		}

		reportarMensagemDeSucesso("Senha atualizada");
		return EnderecoPaginas.PAGINA_PRINCIPAL_SENHA;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public String getSupostaSenhaSemHash() {
		return supostaSenhaSemHash;
	}

	public void setSupostaSenhaSemHash(String supostaSenhaSemHash) {
		this.supostaSenhaSemHash = supostaSenhaSemHash;
	}

	public String getNovaSenhaSemHash() {
		return novaSenhaSemHash;
	}

	public void setNovaSenhaSemHash(String novaSenhaSemHash) {
		this.novaSenhaSemHash = novaSenhaSemHash;
	}
}
