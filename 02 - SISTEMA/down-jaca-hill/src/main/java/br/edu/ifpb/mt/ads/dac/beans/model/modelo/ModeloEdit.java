package br.edu.ifpb.mt.ads.dac.beans.model.modelo;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.ads.dac.beans.AbstractBean;
import br.edu.ifpb.mt.ads.dac.beans.EnderecoPaginas;
import br.edu.ifpb.mt.ads.dac.model.Marca;
import br.edu.ifpb.mt.ads.dac.model.Modelo;
import br.edu.ifpb.mt.ads.dac.services.MarcaService;
import br.edu.ifpb.mt.ads.dac.services.ModeloService;
import br.edu.ifpb.mt.ads.dac.services.ServiceDacException;

@Named
@ViewScoped
public class ModeloEdit extends AbstractBean {

	private static final long serialVersionUID = -262158727882258089L;

	@Inject
	private ModeloService modeloService;

	@Inject
	private MarcaService marcaService;

	private List<Marca> marcas;

	private Modelo modelo;

	public void init() {
		if (modelo == null) {
			modelo = new Modelo();
		}
	}

	@PostConstruct
	public void postConstruct() {
		carregarMarcas();
	}

	public void carregarMarcas() {
		try {
			marcas = marcaService.listar();
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
		}
	}

	public String salvar() {
		try {
			if (isEdicao()) {
				modeloService.atualizar(modelo);
			} else {
				modeloService.salvar(modelo);
			}
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}

		reportarMensagemDeSucesso(String.format("Modelo '%s' cadastrado!", modelo.getNome()));
		return EnderecoPaginas.PAGINA_PRINCIPAL_MODELO;
	}

	public boolean isEdicao() {
		return modelo != null && modelo.getCodigo() != null;
	}

	public List<Marca> getMarcas() {
		return marcas;
	}

	public void setMarcas(List<Marca> marcas) {
		this.marcas = marcas;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}
}
