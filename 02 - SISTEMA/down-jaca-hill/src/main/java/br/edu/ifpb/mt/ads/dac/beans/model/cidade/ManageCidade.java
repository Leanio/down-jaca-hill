package br.edu.ifpb.mt.ads.dac.beans.model.cidade;

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
import br.edu.ifpb.mt.ads.dac.filters.CidadeFilter;
import br.edu.ifpb.mt.ads.dac.model.Cidade;
import br.edu.ifpb.mt.ads.dac.model.Estado;
import br.edu.ifpb.mt.ads.dac.services.CidadeService;
import br.edu.ifpb.mt.ads.dac.services.EstadoService;
import br.edu.ifpb.mt.ads.dac.services.ServiceDacException;

@Named
@ViewScoped
public class ManageCidade extends AbstractBean {

	private static final long serialVersionUID = 2242894654588623521L;
	
	@Inject
	private CidadeService cidadeService;
	
	@Inject
	private EstadoService estadoService;
	
	private LazyDataModel<Cidade> cidades;
	
	private List<Estado> estados;
	
	private CidadeFilter cidadeFilter;
	
	@PostConstruct
	public void postConstruct() {
		criarFiltro();
		carregarEstados();
		filtrar();
	}
	
	public void filtrar() {
		cidades = new LazyDataModel<Cidade>() {

			private static final long serialVersionUID = 1L;

			@Override
			public List<Cidade> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {
				try {
					int total = (int) cidadeService.total(cidadeFilter);

					setRowCount(total);

					cidadeFilter.setProximoItem(first);
					cidadeFilter.setQuantidadeResultadosPorPagina(pageSize);
					return cidadeService.filtrar(cidadeFilter);
				} catch (ServiceDacException e) {
					reportarMensagemDeErro(e.getMessage());
				}

				return null;
			}
		};
	}
	
	public void carregarEstados() {
		try {
			estados = estadoService.listar();
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
		}
	}
	
	public void criarFiltro() {
		cidadeFilter = new CidadeFilter();
	}

	public String remover(Cidade cidade) {
		try {
			Long codigo = cidade.getCodigo();
			
			cidadeService.remover(codigo);
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		
		reportarMensagemDeSucesso(String.format("Cidade '%s' removido", cidade.getNome()));
		return EnderecoPaginas.PAGINA_PRINCIPAL_ESTADO;
	}
	
	public LazyDataModel<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(LazyDataModel<Cidade> cidades) {
		this.cidades = cidades;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public CidadeFilter getCidadeFilter() {
		return cidadeFilter;
	}

	public void setCidadeFilter(CidadeFilter cidadeFilter) {
		this.cidadeFilter = cidadeFilter;
	}

}
