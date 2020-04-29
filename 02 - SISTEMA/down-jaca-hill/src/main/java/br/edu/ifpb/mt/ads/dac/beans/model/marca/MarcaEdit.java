package br.edu.ifpb.mt.ads.dac.beans.model.marca;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.ads.dac.beans.AbstractBean;
import br.edu.ifpb.mt.ads.dac.beans.EnderecoPaginas;
import br.edu.ifpb.mt.ads.dac.model.Marca;
import br.edu.ifpb.mt.ads.dac.services.MarcaService;
import br.edu.ifpb.mt.ads.dac.services.ServiceDacException;

@Named
@ViewScoped
public class MarcaEdit extends AbstractBean {

	private static final long serialVersionUID = -2847376495916362949L;
	
	@Inject
	private MarcaService marcaService;
	
	private Marca marca;
	
	public void init() {
		if (marca == null) {
			marca = new Marca();
		}
	}
	
	public String salvar() {
		try {
			if(isEdicao()) {
				marcaService.atualizar(marca);
			} else {
				marcaService.salvar(marca);
			}
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		
		reportarMensagemDeSucesso(String.format("Marca '%s' cadastrada!", marca.getNome()));
		return EnderecoPaginas.PAGINA_PRINCIPAL_MARCA;
	}
	
	public boolean isEdicao() {
		return marca != null && marca.getCodigo() != null;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

}
