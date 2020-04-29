package br.edu.ifpb.mt.ads.dac.beans.model.conta;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;

import br.edu.ifpb.mt.ads.dac.beans.AbstractBean;
import br.edu.ifpb.mt.ads.dac.beans.EnderecoPaginas;
import br.edu.ifpb.mt.ads.dac.model.Conta;
import br.edu.ifpb.mt.ads.dac.model.Grupo;
import br.edu.ifpb.mt.ads.dac.services.ContaService;
import br.edu.ifpb.mt.ads.dac.services.ServiceDacException;
import br.edu.ifpb.mt.ads.dac.util.qualifiers.ContaAutenticada;

import static br.edu.ifpb.mt.ads.dac.beans.autenticacao.Logout.efetuarLogout;

@Named
@ViewScoped
public class ContaEdit extends AbstractBean {

	private static final long serialVersionUID = 1138726408646756939L;
	
	@Inject
	private ContaService contaService;

	@Inject
	@ContaAutenticada
	private Conta conta;
	
	public void init() {
		if (conta == null) {
			conta = new Conta();
			
			List<Grupo> grupos = new ArrayList<>();
			conta.setGrupo(grupos);
		}
	}
	
	public String salvar() {
		try {
			if (isEdicao()) {
				contaService.atualizar(conta);
			} else {
				contaService.salvar(conta);
			}
		} catch(ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		
		reportarMensagemDeSucesso(String.format("Conta '%s' cadastrada", conta.getLogin()));
		return EnderecoPaginas.PAGINA_PRINCIPAL_CONTA;
	}
	
	public String desativar() throws IOException, ServletException {
		try {
			Long codigo = conta.getCodigo();
			contaService.remover(codigo);
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		
		efetuarLogout();
		
		reportarMensagemDeSucesso(String.format("Conta '%s' desativada", conta.getLogin()));
		return EnderecoPaginas.PAGINA_PRINCIPAL_AUTENTICADO;
	}
	
	public boolean isEdicao() {
		return conta != null && conta.getCodigo() != null;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}
	
}
