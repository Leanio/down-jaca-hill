package br.edu.ifpb.mt.ads.dac.beans.model.marca;

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
import br.edu.ifpb.mt.ads.dac.filters.MarcaFilter;
import br.edu.ifpb.mt.ads.dac.model.Marca;
import br.edu.ifpb.mt.ads.dac.services.MarcaService;
import br.edu.ifpb.mt.ads.dac.services.ServiceDacException;

@Named
@ViewScoped
public class ManageMarca extends AbstractBean {

	private static final long serialVersionUID = 5310489468161626255L;
	
	@Inject
	private MarcaService marcaService;
	
	private LazyDataModel<Marca> marcas;
	
	private MarcaFilter marcaFilter;
	
	@PostConstruct
	public void postConstruct() {
		criarFiltro();
		filtrar();
	}
	
	public void filtrar() {
		marcas = new LazyDataModel<Marca>() {

			private static final long serialVersionUID = 1L;

			@Override
			public List<Marca> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {
				try {
					int total = (int) marcaService.total(marcaFilter);

					setRowCount(total);

					marcaFilter.setProximoItem(first);
					marcaFilter.setQuantidadeResultadosPorPagina(pageSize);
					return marcaService.filtrar(marcaFilter);
				} catch (ServiceDacException e) {
					reportarMensagemDeErro(e.getMessage());
				}

				return null;
			}
		};
	}
	
	public String remover(Marca marca) {
		try {
			Long codigo = marca.getCodigo();
			
			marcaService.remover(codigo);
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		
		reportarMensagemDeSucesso(String.format("Marca '%s' removida", marca.getNome()));
		return EnderecoPaginas.PAGINA_PRINCIPAL_MARCA;
	}
	
	public void criarFiltro() {
		marcaFilter = new MarcaFilter();
	}

	public LazyDataModel<Marca> getMarcas() {
		return marcas;
	}

	public void setMarcas(LazyDataModel<Marca> marcas) {
		this.marcas = marcas;
	}

	public MarcaFilter getMarcaFilter() {
		return marcaFilter;
	}

	public void setMarcaFilter(MarcaFilter marcaFilter) {
		this.marcaFilter = marcaFilter;
	}
}
