package br.edu.ifpb.mt.ads.dac.beans.model.estado;

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
import br.edu.ifpb.mt.ads.dac.filters.EstadoFilter;
import br.edu.ifpb.mt.ads.dac.model.Estado;
import br.edu.ifpb.mt.ads.dac.services.EstadoService;
import br.edu.ifpb.mt.ads.dac.services.ServiceDacException;

@Named
@ViewScoped
public class ManageEstado extends AbstractBean {

	private static final long serialVersionUID = 363459990538972055L;

	@Inject
	private EstadoService estadoService;

	private LazyDataModel<Estado> estados;

	private EstadoFilter estadoFilter;

	@PostConstruct
	public void postConstruct() {
		criarFiltro();
		filtrar();
	}

	public void filtrar() {
		estados = new LazyDataModel<Estado>() {

			private static final long serialVersionUID = 1L;

			@Override
			public List<Estado> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {
				try {
					int total = (int) estadoService.total(estadoFilter);

					setRowCount(total);

					estadoFilter.setProximoItem(first);
					estadoFilter.setQuantidadeResultadosPorPagina(pageSize);
					return estadoService.filtrar(estadoFilter);
				} catch (ServiceDacException e) {
					reportarMensagemDeErro(e.getMessage());
				}

				return null;
			}
		};
	}
	
	public String remover(Estado estado) {
		try {
			Long codigo = estado.getCodigo();
			
			estadoService.remover(codigo);
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		
		reportarMensagemDeSucesso(String.format("Estado '%s' removido", estado.getNome()));
		return EnderecoPaginas.PAGINA_PRINCIPAL_CIDADE;
	}

	public void criarFiltro() {
		estadoFilter = new EstadoFilter();
	}

	public LazyDataModel<Estado> getEstados() {
		return estados;
	}

	public void setEstados(LazyDataModel<Estado> estados) {
		this.estados = estados;
	}

	public EstadoFilter getEstadoFilter() {
		return estadoFilter;
	}

	public void setEstadoFilter(EstadoFilter estadoFilter) {
		this.estadoFilter = estadoFilter;
	}

}
