package br.edu.ifpb.mt.ads.dac.beans.model.pagamento;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.ads.dac.beans.AbstractBean;
import br.edu.ifpb.mt.ads.dac.beans.EnderecoPaginas;
import br.edu.ifpb.mt.ads.dac.model.Pagamento;
import br.edu.ifpb.mt.ads.dac.model.PedidoParticipacao;
import br.edu.ifpb.mt.ads.dac.model.enumerations.MeioPagamento;
import br.edu.ifpb.mt.ads.dac.services.PagamentoService;
import br.edu.ifpb.mt.ads.dac.services.ServiceDacException;

@Named
@ViewScoped
public class PagamentoEdit extends AbstractBean {

	private static final long serialVersionUID = 6175230724787350965L;
	
	@Inject
	private PagamentoService pagamentoService;
	
	private Pagamento pagamento;
	
	private PedidoParticipacao pedidoParticipacao;
	
	public void init() {
		if (pagamento == null) {
			pagamento = new Pagamento();
			
			pedidoParticipacao.setPagamento(pagamento);
			pagamento.setPedidoParticipacao(pedidoParticipacao);
		}
	}
	
	public String salvar() {
		try {
			if (isEdicao()) {
				pagamentoService.atualizar(pagamento);
			} else {
				pagamentoService.salvar(pagamento);
			}
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}

		reportarMensagemDeSucesso("Feito");
		return EnderecoPaginas.PAGINA_PRINCIPAL_PEDIDOS_EVENTO + "&evento=" + pagamento.getPedidoParticipacao().getEvento().getCodigo();
	}
	
	public boolean isEdicao() {
		return pagamento != null && pagamento.getCodigo() != null;
	}
	
	public MeioPagamento[] meiosPagamento() {
		return MeioPagamento.values();
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public PedidoParticipacao getPedidoParticipacao() {
		return pedidoParticipacao;
	}

	public void setPedidoParticipacao(PedidoParticipacao pedidoParticipacao) {
		this.pedidoParticipacao = pedidoParticipacao;
	}

}
