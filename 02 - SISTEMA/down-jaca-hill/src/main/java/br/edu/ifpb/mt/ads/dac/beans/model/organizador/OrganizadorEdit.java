package br.edu.ifpb.mt.ads.dac.beans.model.organizador;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.ads.dac.beans.AbstractBean;
import br.edu.ifpb.mt.ads.dac.beans.EnderecoPaginas;
import br.edu.ifpb.mt.ads.dac.model.Organizador;
import br.edu.ifpb.mt.ads.dac.model.Pessoa;
import br.edu.ifpb.mt.ads.dac.services.OrganizadorService;
import br.edu.ifpb.mt.ads.dac.services.ServiceDacException;
import br.edu.ifpb.mt.ads.dac.util.qualifiers.PessoaAutenticada;

@Named
@ViewScoped
public class OrganizadorEdit extends AbstractBean {

	private static final long serialVersionUID = 907782947831092921L;

	@Inject
	private OrganizadorService organizadorService;

	@Inject
	@PessoaAutenticada
	private Pessoa pessoa;

	private Organizador organizador;

	public void init() {
		if (organizador == null) {
			organizador = pessoa.getOrganizador();

			if (organizador == null) {
				organizador = new Organizador();

				organizador.setPessoa(pessoa);
				pessoa.setOrganizador(organizador);
			}
		}
	}

	public String salvar() {
		try {
			if (isEdicao()) {
				organizadorService.atualizar(organizador);
			} else {
				organizadorService.salvar(organizador);
			}
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		
		reportarMensagemDeSucesso(String.format("Organizador '%s' cadastrado!", organizador.getRg()));
		return EnderecoPaginas.PAGINA_PRINCIPAL_ORGANIZADOR;
	}
	
	public String desativar() {
		try {
			organizadorService.desativar(organizador);
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		reportarMensagemDeSucesso(String.format("Organizador '%s' desativado!", organizador.getRg()));
		
		return EnderecoPaginas.PAGINA_PRINCIPAL_ORGANIZADOR;
	}

	public boolean isEdicao() {
		return organizador != null && organizador.getCodigo() != null;
	}

	public Organizador getOrganizador() {
		return organizador;
	}

	public void setOrganizador(Organizador organizador) {
		this.organizador = organizador;
	}

}
