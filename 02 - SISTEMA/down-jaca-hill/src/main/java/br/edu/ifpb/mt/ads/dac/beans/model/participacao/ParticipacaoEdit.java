package br.edu.ifpb.mt.ads.dac.beans.model.participacao;

import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.ads.dac.beans.AbstractBean;
import br.edu.ifpb.mt.ads.dac.beans.EnderecoPaginas;
import br.edu.ifpb.mt.ads.dac.filters.PedidoParticipacaoFilter;
import br.edu.ifpb.mt.ads.dac.model.Evento;
import br.edu.ifpb.mt.ads.dac.model.Participacao;
import br.edu.ifpb.mt.ads.dac.model.PedidoParticipacao;
import br.edu.ifpb.mt.ads.dac.model.enumerations.EstadoPedido;
import br.edu.ifpb.mt.ads.dac.services.ParticipacaoService;
import br.edu.ifpb.mt.ads.dac.services.PedidoParticipacaoService;
import br.edu.ifpb.mt.ads.dac.services.ServiceDacException;

@Named
@ViewScoped
public class ParticipacaoEdit extends AbstractBean {

	private static final long serialVersionUID = -2504812009931835186L;
	
	@Inject
	private ParticipacaoService participacaoService;
	
	@Inject
	private PedidoParticipacaoService pedidoParticipacaoService;
	
	private Participacao participacao;
	
	private List<PedidoParticipacao> pedidosParticipacao;
	
	private Evento evento;
	
	public void init() {
		if (participacao == null) {
			participacao = new Participacao();
		}
		
		carregarPedidosParticipacao();
	}
	
	public String salvar() {
		try {
			if (isEdicao()) {
				participacaoService.atualizar(participacao);
			} else {
				participacaoService.salvar(participacao);
			}
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		
		reportarMensagemDeSucesso("Salvo");
		return EnderecoPaginas.PAGINA_PRINCIPAL_PARTICIPACAO_EVENTO + "&evento=" + evento.getCodigo();
	}
	
	public void carregarPedidosParticipacao() {
		try {
			PedidoParticipacaoFilter pedidoParticipacaoFilter = new PedidoParticipacaoFilter();
			pedidoParticipacaoFilter.setEvento(evento);
			pedidoParticipacaoFilter.setEstadoPedidoParticipacao(EstadoPedido.ACEITO);
			pedidoParticipacaoFilter.setParticipouDoEvento(false);
			
			pedidosParticipacao = pedidoParticipacaoService.filtrar(pedidoParticipacaoFilter);
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
		}
	}
	
	public boolean isEdicao() {
		return participacao != null && participacao.getCodigo() != null;
	}

	public Participacao getParticipacao() {
		return participacao;
	}

	public void setParticipacao(Participacao participacao) {
		this.participacao = participacao;
	}

	public List<PedidoParticipacao> getPedidosParticipacao() {
		return pedidosParticipacao;
	}

	public void setPedidosParticipacao(List<PedidoParticipacao> pedidosParticipacao) {
		this.pedidosParticipacao = pedidosParticipacao;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	
}
