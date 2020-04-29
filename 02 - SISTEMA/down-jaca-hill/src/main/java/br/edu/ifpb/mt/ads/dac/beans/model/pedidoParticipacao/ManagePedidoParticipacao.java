package br.edu.ifpb.mt.ads.dac.beans.model.pedidoParticipacao;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.edu.ifpb.mt.ads.dac.beans.AbstractBean;
import br.edu.ifpb.mt.ads.dac.filters.PedidoParticipacaoFilter;
import br.edu.ifpb.mt.ads.dac.model.PedidoParticipacao;
import br.edu.ifpb.mt.ads.dac.model.enumerations.EstadoPedido;
import br.edu.ifpb.mt.ads.dac.services.PedidoParticipacaoService;
import br.edu.ifpb.mt.ads.dac.services.ServiceDacException;

@Named
@ViewScoped
public class ManagePedidoParticipacao extends AbstractBean {

	private static final long serialVersionUID = 8975226000598109765L;

	@Inject
	protected PedidoParticipacaoService pedidoParticipacaoService;

	protected PedidoParticipacaoFilter pedidoParticipacaoFilter;

	protected LazyDataModel<PedidoParticipacao> pedidosParticipacao;

	@PostConstruct
	private void postConstruct() {
		criarFiltro();
		filtrar();
	}

	public void filtrar() {
		pedidosParticipacao = new LazyDataModel<PedidoParticipacao>() {

			private static final long serialVersionUID = 1L;

			@Override
			public List<PedidoParticipacao> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {
				try {
					int total = (int) pedidoParticipacaoService.total(pedidoParticipacaoFilter);

					setRowCount(total);

					pedidoParticipacaoFilter.setProximoItem(first);
					pedidoParticipacaoFilter.setQuantidadeResultadosPorPagina(pageSize);
					return pedidoParticipacaoService.filtrar(pedidoParticipacaoFilter);
				} catch (ServiceDacException e) {
					reportarMensagemDeErro(e.getMessage());
				}

				return null;
			}
		};
	}

	public void criarFiltro() {
		pedidoParticipacaoFilter = new PedidoParticipacaoFilter();
	}
	
	public EstadoPedido[] getEstadosPedido() {
		return EstadoPedido.values();
	}

	public PedidoParticipacaoFilter getPedidoParticipacaoFilter() {
		return pedidoParticipacaoFilter;
	}

	public void setPedidoParticipacaoFilter(PedidoParticipacaoFilter pedidoParticipacaoFilter) {
		this.pedidoParticipacaoFilter = pedidoParticipacaoFilter;
	}

	public LazyDataModel<PedidoParticipacao> getPedidosParticipacao() {
		return pedidosParticipacao;
	}

	public void setPedidosParticipacao(LazyDataModel<PedidoParticipacao> pedidosParticipacao) {
		this.pedidosParticipacao = pedidosParticipacao;
	}

}
