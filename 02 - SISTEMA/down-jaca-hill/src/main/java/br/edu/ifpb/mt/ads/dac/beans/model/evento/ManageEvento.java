package br.edu.ifpb.mt.ads.dac.beans.model.evento;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.edu.ifpb.mt.ads.dac.beans.AbstractBean;
import br.edu.ifpb.mt.ads.dac.filters.CidadeFilter;
import br.edu.ifpb.mt.ads.dac.filters.EventoFilter;
import br.edu.ifpb.mt.ads.dac.model.Cidade;
import br.edu.ifpb.mt.ads.dac.model.Estado;
import br.edu.ifpb.mt.ads.dac.model.Evento;
import br.edu.ifpb.mt.ads.dac.services.CidadeService;
import br.edu.ifpb.mt.ads.dac.services.EstadoService;
import br.edu.ifpb.mt.ads.dac.services.EventoService;
import br.edu.ifpb.mt.ads.dac.services.ServiceDacException;

@Named
@ViewScoped
public class ManageEvento extends AbstractBean {

	private static final long serialVersionUID = 6959145671631926257L;
	
	@Inject
	protected EventoService eventoService;
	
	@Inject
	protected EstadoService estadoService;
	
	@Inject
	protected CidadeService cidadeService;
	
	protected EventoFilter eventoFilter;
	
	protected List<Estado> estados;
	
	protected List<Cidade> cidades;
	
	protected LazyDataModel<Evento> eventos;
	
	@PostConstruct
	public void postConstruct() {
		criarFiltro();
		carregarEstados();
		filtrar();
	}
	
	public void filtrar() {
		eventos = new LazyDataModel<Evento>() {

			private static final long serialVersionUID = 1L;

			@Override
			public List<Evento> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {
				try {
					int total = (int) eventoService.total(eventoFilter);

					setRowCount(total);

					eventoFilter.setProximoItem(first);
					eventoFilter.setQuantidadeResultadosPorPagina(pageSize);
					return eventoService.filtrar(eventoFilter);
				} catch (ServiceDacException e) {
					reportarMensagemDeErro(e.getMessage());
				}

				return null;
			}
		};
	}
	
	public void criarFiltro() {
		eventoFilter = new EventoFilter();
	}
	
	public void carregarEstados() {
		try {
			estados = estadoService.listar();
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
		}
	}
	
	public void carregarCidades() {
		try {
			CidadeFilter cidadeFilter = new CidadeFilter();
			cidadeFilter.setEstado(eventoFilter.getEstado());
			cidadeFilter.setIsLiberadaEvento(true);
			
			cidades = cidadeService.filtrar(cidadeFilter);
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
		}
	}
	
	public boolean isEstadoSelecionado() {
		return eventoFilter.getEstado() != null;
	}
	
	public void configurarFilter() {
		if (!isEstadoSelecionado()) {
			eventoFilter.setCidade(null);
		}
	}

	public EventoFilter getEventoFilter() {
		return eventoFilter;
	}

	public void setEventoFilter(EventoFilter eventoFilter) {
		this.eventoFilter = eventoFilter;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public LazyDataModel<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(LazyDataModel<Evento> eventos) {
		this.eventos = eventos;
	}
}
