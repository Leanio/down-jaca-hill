package br.edu.ifpb.mt.ads.dac.beans.model.recurso;

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
import br.edu.ifpb.mt.ads.dac.filters.RecursoFilter;
import br.edu.ifpb.mt.ads.dac.model.Recurso;
import br.edu.ifpb.mt.ads.dac.services.RecursoService;
import br.edu.ifpb.mt.ads.dac.services.ServiceDacException;

@Named
@ViewScoped
public class ManageRecurso extends AbstractBean {

	private static final long serialVersionUID = -3987710667871478427L;
	
	@Inject
	private RecursoService recursoService;
	
	private LazyDataModel<Recurso> recursos;
	
	private RecursoFilter recursoFilter;
	
	@PostConstruct
	public void postConstruct() {
		criarFiltro();
		filtrar();
	}
	
	public void filtrar() {
		recursos = new LazyDataModel<Recurso>() {

			private static final long serialVersionUID = 1L;

			@Override
			public List<Recurso> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {
				try {
					int total = (int) recursoService.total(recursoFilter);

					setRowCount(total);

					recursoFilter.setProximoItem(first);
					recursoFilter.setQuantidadeResultadosPorPagina(pageSize);
					return recursoService.filtrar(recursoFilter);
				} catch (ServiceDacException e) {
					reportarMensagemDeErro(e.getMessage());
				}

				return null;
			}
		};
	}
	
	public void criarFiltro() {
		recursoFilter = new RecursoFilter();
	}
	
	public String remover(Recurso recurso) {
		try {
			Long codigo = recurso.getCodigo();
			
			recursoService.remover(codigo);
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		
		reportarMensagemDeSucesso(String.format("Recurso '%s' removido", recurso.getNome()));
		return EnderecoPaginas.PAGINA_PRINCIPAL_RECURSO;
	}

	public LazyDataModel<Recurso> getRecursos() {
		return recursos;
	}

	public void setRecursos(LazyDataModel<Recurso> recursos) {
		this.recursos = recursos;
	}

	public RecursoFilter getRecursoFilter() {
		return recursoFilter;
	}

	public void setRecursoFilter(RecursoFilter recursoFilter) {
		this.recursoFilter = recursoFilter;
	}

}
