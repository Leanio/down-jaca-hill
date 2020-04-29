package br.edu.ifpb.mt.ads.dac.beans.model.modelo;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.edu.ifpb.mt.ads.dac.beans.AbstractBean;
import br.edu.ifpb.mt.ads.dac.beans.EnderecoPaginas;
import br.edu.ifpb.mt.ads.dac.filters.ModeloFilter;
import br.edu.ifpb.mt.ads.dac.model.Marca;
import br.edu.ifpb.mt.ads.dac.model.Modelo;
import br.edu.ifpb.mt.ads.dac.services.MarcaService;
import br.edu.ifpb.mt.ads.dac.services.ModeloService;
import br.edu.ifpb.mt.ads.dac.services.ServiceDacException;

@Named
@ViewScoped
public class ManageModelo extends AbstractBean {

	private static final long serialVersionUID = -5319225660653257989L;
	
	@Inject
	private ModeloService modeloService;
	
	@Inject
	private MarcaService marcaService;
	
	private List<Marca> marcas;
	
	private LazyDataModel<Modelo> modelos;
	
	private ModeloFilter modeloFilter;
	
	@PostConstruct
	public void postConstruct() {
		criarFiltro();
		carregarMarcas();
		filtrar();
	}
	
	public void filtrar() {
		modelos = new LazyDataModel<Modelo>() {

			private static final long serialVersionUID = 1L;

			@Override
			public List<Modelo> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {
				try {
					int total = (int) modeloService.total(modeloFilter);

					setRowCount(total);

					modeloFilter.setProximoItem(first);
					modeloFilter.setQuantidadeResultadosPorPagina(pageSize);
					return modeloService.filtrar(modeloFilter);
				} catch (ServiceDacException e) {
					reportarMensagemDeErro(e.getMessage());
				}

				return null;
			}
		};
	}
	
	public void carregarMarcas() {
		try {
			marcas = marcaService.listar();
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
		}
	}
	
	public String remover(Modelo modelo) {
		try {
			Long codigo = modelo.getCodigo();
			
			modeloService.remover(codigo);
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		
		reportarMensagemDeSucesso(String.format("Modelo '%s' removido", modelo.getNome()));
		return EnderecoPaginas.PAGINA_PRINCIPAL_CIDADE;
	}
	
	public void criarFiltro() {
		modeloFilter = new ModeloFilter();
	}

	public List<Marca> getMarcas() {
		return marcas;
	}

	public void setMarcas(List<Marca> marcas) {
		this.marcas = marcas;
	}

	public LazyDataModel<Modelo> getModelos() {
		return modelos;
	}

	public void setModelos(LazyDataModel<Modelo> modelos) {
		this.modelos = modelos;
	}

	public ModeloFilter getModeloFilter() {
		return modeloFilter;
	}

	public void setModeloFilter(ModeloFilter modeloFilter) {
		this.modeloFilter = modeloFilter;
	}

}
