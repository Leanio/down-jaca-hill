package br.edu.ifpb.mt.ads.dac.beans.model.ciclista;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.ads.dac.beans.AbstractBean;
import br.edu.ifpb.mt.ads.dac.beans.EnderecoPaginas;
import br.edu.ifpb.mt.ads.dac.model.Ciclista;
import br.edu.ifpb.mt.ads.dac.model.Pessoa;
import br.edu.ifpb.mt.ads.dac.services.CiclistaService;
import br.edu.ifpb.mt.ads.dac.services.ServiceDacException;
import br.edu.ifpb.mt.ads.dac.util.qualifiers.PessoaAutenticada;

@Named
@ViewScoped
public class CiclistaEdit extends AbstractBean {

	private static final long serialVersionUID = 936474778094156340L;
	
	@Inject
	private CiclistaService ciclistaService;
	
	@Inject
	@PessoaAutenticada
	private Pessoa pessoa;
	
	private Ciclista ciclista;
	
	public void init() {
		if (ciclista == null) {
			ciclista = pessoa.getCiclista();
			
			if (ciclista == null) {
				ciclista = new Ciclista();
				
				ciclista.setPessoa(pessoa);
				pessoa.setCiclista(ciclista);
			}
		}
	}
	
	public String salvar() {
		try {
			if (isEdicao()) {
				ciclistaService.atualizar(ciclista);
			} else {
				ciclistaService.salvar(ciclista);
			}
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		
		reportarMensagemDeSucesso("Ciclista cadastrado");
		return EnderecoPaginas.PAGINA_PRINCIPAL_CICLISTA;
	}
	
	public String desativar() {
		try {
			ciclistaService.desativar(ciclista);
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		reportarMensagemDeSucesso("Ciclista desativado");
		return EnderecoPaginas.PAGINA_PRINCIPAL_CICLISTA;
	}

	public boolean isEdicao() {
		return ciclista != null && ciclista.getCodigo() != null;
	}

	public Ciclista getCiclista() {
		return ciclista;
	}

	public void setCiclista(Ciclista ciclista) {
		this.ciclista = ciclista;
	}

}
