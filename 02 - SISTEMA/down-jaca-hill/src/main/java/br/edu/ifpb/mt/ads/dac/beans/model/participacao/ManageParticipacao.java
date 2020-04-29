package br.edu.ifpb.mt.ads.dac.beans.model.participacao;

import java.util.List;
import java.util.Map;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.edu.ifpb.mt.ads.dac.beans.AbstractBean;
import br.edu.ifpb.mt.ads.dac.filters.ParticipacaoFilter;
import br.edu.ifpb.mt.ads.dac.model.Participacao;
import br.edu.ifpb.mt.ads.dac.services.ParticipacaoService;
import br.edu.ifpb.mt.ads.dac.services.ServiceDacException;

@Named
@ViewScoped
public class ManageParticipacao extends AbstractBean {

	private static final long serialVersionUID = -8842264901047007129L;
	
	@Inject
	protected ParticipacaoService participacaoService;
	
	protected LazyDataModel<Participacao> participacoes;
	
	protected ParticipacaoFilter participacaoFilter;
	
	public void init() {
		criarFiltro();
		filtrar();
	}
	
	public void filtrar() {
		participacoes = new LazyDataModel<Participacao>() {

			private static final long serialVersionUID = 1L;

			@Override
			public List<Participacao> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {
				try {
					int total = (int) participacaoService.total(participacaoFilter);

					setRowCount(total);

					participacaoFilter.setProximoItem(first);
					participacaoFilter.setQuantidadeResultadosPorPagina(pageSize);
					return participacaoService.filtrar(participacaoFilter);
				} catch (ServiceDacException e) {
					reportarMensagemDeErro(e.getMessage());
				}

				return null;
			}
		};
	}
	
	public void criarFiltro() {
		participacaoFilter = new ParticipacaoFilter();
	}

	public LazyDataModel<Participacao> getParticipacoes() {
		return participacoes;
	}

	public void setParticipacoes(LazyDataModel<Participacao> participacoes) {
		this.participacoes = participacoes;
	}

	public ParticipacaoFilter getParticipacaoFilter() {
		return participacaoFilter;
	}

	public void setParticipacaoFilter(ParticipacaoFilter participacaoFilter) {
		this.participacaoFilter = participacaoFilter;
	}

}
