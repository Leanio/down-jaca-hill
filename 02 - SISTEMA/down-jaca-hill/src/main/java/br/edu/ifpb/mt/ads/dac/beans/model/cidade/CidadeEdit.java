package br.edu.ifpb.mt.ads.dac.beans.model.cidade;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.ads.dac.beans.AbstractBean;
import br.edu.ifpb.mt.ads.dac.beans.EnderecoPaginas;
import br.edu.ifpb.mt.ads.dac.model.Cidade;
import br.edu.ifpb.mt.ads.dac.model.Estado;
import br.edu.ifpb.mt.ads.dac.services.CidadeService;
import br.edu.ifpb.mt.ads.dac.services.EstadoService;
import br.edu.ifpb.mt.ads.dac.services.ServiceDacException;

@Named
@ViewScoped
public class CidadeEdit extends AbstractBean {

	private static final long serialVersionUID = 8733275730555638010L;

	@Inject
	private CidadeService cidadeService;
	
	@Inject
	private EstadoService estadoService;
	
	private Cidade cidade;
	
	private List<Estado> estados;
	
	public void init() {
		if (cidade == null) {
			cidade = new Cidade();
		}
	}
	
	@PostConstruct
	public void postConstruct() {
		carregarEstados();
	}
	
	public String salvar() {
		try {
			if (isEdicao()) {
				cidadeService.atualizar(cidade);
			} else {
				cidadeService.salvar(cidade);
			}
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		
		reportarMensagemDeSucesso(String.format("Cidade '%s' cadastrada!", cidade.getNome()));
		return EnderecoPaginas.PAGINA_PRINCIPAL_CIDADE;
	}
	
	
	public void carregarEstados() {
		try {
			estados = estadoService.listar();
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
		}
	}
	
	public boolean isEdicao() {
		return cidade != null && cidade.getCodigo() != null;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}
}
