package br.edu.ifpb.mt.ads.dac.beans.model.recurso;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.ads.dac.beans.AbstractBean;
import br.edu.ifpb.mt.ads.dac.beans.EnderecoPaginas;
import br.edu.ifpb.mt.ads.dac.model.Recurso;
import br.edu.ifpb.mt.ads.dac.services.RecursoService;
import br.edu.ifpb.mt.ads.dac.services.ServiceDacException;

@Named
@ViewScoped
public class RecursoEdit extends AbstractBean {

	private static final long serialVersionUID = -1287661728540904816L;
	
	@Inject
	private RecursoService recursoService;
	
	private Recurso recurso;
	
	public void init() {
		if (recurso == null) {
			recurso = new Recurso();
		}
	}
	
	public String salvar() {
		try {
			if (isEdicao()) {
				recursoService.atualizar(recurso);
			} else {
				recursoService.salvar(recurso);
			}
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		
		reportarMensagemDeSucesso(String.format("Recurso '%s' cadastrado!", recurso.getNome()));
		return EnderecoPaginas.PAGINA_PRINCIPAL_RECURSO;
	}
	
	public boolean isEdicao() {
		return recurso != null && recurso.getCodigo() != null;
	}

	public Recurso getRecurso() {
		return recurso;
	}

	public void setRecurso(Recurso recurso) {
		this.recurso = recurso;
	}
	
}
