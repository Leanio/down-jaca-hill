package br.edu.ifpb.mt.ads.dac.beans.model.pedidoParticipacao;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.ads.dac.beans.EnderecoPaginas;
import br.edu.ifpb.mt.ads.dac.model.Ciclista;
import br.edu.ifpb.mt.ads.dac.model.PedidoParticipacao;
import br.edu.ifpb.mt.ads.dac.services.ServiceDacException;
import br.edu.ifpb.mt.ads.dac.util.qualifiers.CiclistaAutenticado;

@Named
@ViewScoped
public class ManagePedidoParticipacaoCiclista extends ManagePedidoParticipacao {

	private static final long serialVersionUID = 3458438377172813094L;
	
	@Inject
	@CiclistaAutenticado
	private Ciclista ciclista;
	
	@Override
	public void criarFiltro() {
		super.criarFiltro();
	
		pedidoParticipacaoFilter.setCiclista(ciclista);
	}
	
	public String remover(PedidoParticipacao pedidoParticipacao) {
		try {
			Long codigo = pedidoParticipacao.getCodigo();
			
			pedidoParticipacaoService.remover(codigo);
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		
		reportarMensagemDeSucesso("Removido");
		return EnderecoPaginas.PAGINA_PRINCIPAL_PEDIDOS;
	}
	
}
