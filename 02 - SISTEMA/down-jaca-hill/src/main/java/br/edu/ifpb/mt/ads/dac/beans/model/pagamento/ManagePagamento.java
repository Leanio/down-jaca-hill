package br.edu.ifpb.mt.ads.dac.beans.model.pagamento;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.ads.dac.beans.AbstractBean;
import br.edu.ifpb.mt.ads.dac.beans.EnderecoPaginas;
import br.edu.ifpb.mt.ads.dac.model.Pagamento;
import br.edu.ifpb.mt.ads.dac.services.PagamentoService;
import br.edu.ifpb.mt.ads.dac.services.ServiceDacException;

@Named
@ViewScoped
public class ManagePagamento extends AbstractBean {

	private static final long serialVersionUID = -5434235132535766432L;
	
	@Inject
	private PagamentoService pagamentoService;
	
	public String remover(Pagamento pagamento) {
		try {
			Long codigo = pagamento.getCodigo();
			
			pagamentoService.remover(codigo);
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		
		reportarMensagemDeSucesso("Pagamento removido");
		return EnderecoPaginas.PAGINA_PRINCIPAL_PEDIDOS_EVENTO  + "&evento=" + pagamento.getPedidoParticipacao().getEvento().getCodigo();
	}

}
