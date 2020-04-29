package br.edu.ifpb.mt.ads.dac.beans.model.estado;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.ads.dac.beans.AbstractBean;
import br.edu.ifpb.mt.ads.dac.beans.EnderecoPaginas;
import br.edu.ifpb.mt.ads.dac.model.Estado;
import br.edu.ifpb.mt.ads.dac.services.EstadoService;
import br.edu.ifpb.mt.ads.dac.services.ServiceDacException;

@Named
@ViewScoped
public class EstadoEdit extends AbstractBean {

	private static final long serialVersionUID = -1287661728540904816L;
	
	@Inject
	private EstadoService estadoService;
	
	private Estado estado;
	
	public void init() {
		if (estado == null) {
			estado = new Estado();
		}
	}
	
	public String salvar() {
		try {
			if (isEdicao()) {
				estadoService.atualizar(estado);
			} else {
				estadoService.salvar(estado);
			}
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		
		reportarMensagemDeSucesso(String.format("Estado '%s' cadastrado!", estado.getNome()));
		return EnderecoPaginas.PAGINA_PRINCIPAL_ESTADO;
	}
	
	public boolean isEdicao() {
		return estado != null && estado.getCodigo() != null;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
}
