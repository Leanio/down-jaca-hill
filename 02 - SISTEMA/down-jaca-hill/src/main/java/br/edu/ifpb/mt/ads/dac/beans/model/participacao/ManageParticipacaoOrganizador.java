package br.edu.ifpb.mt.ads.dac.beans.model.participacao;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.ads.dac.beans.EnderecoPaginas;
import br.edu.ifpb.mt.ads.dac.model.Evento;
import br.edu.ifpb.mt.ads.dac.model.Organizador;
import br.edu.ifpb.mt.ads.dac.model.Participacao;
import br.edu.ifpb.mt.ads.dac.services.ServiceDacException;
import br.edu.ifpb.mt.ads.dac.util.qualifiers.OrganizadorAutenticado;

@Named
@ViewScoped
public class ManageParticipacaoOrganizador extends ManageParticipacao {

	private static final long serialVersionUID = 3519960276531579814L;
	
	@Inject
	@OrganizadorAutenticado
	private Organizador organizador;
	
	private Evento evento;
	
	public String remover(Participacao participacao) {
		try {
			Long codigo = participacao.getCodigo();
			
			participacaoService.remover(codigo);
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		
		reportarMensagemDeSucesso("Participação removida");
		return EnderecoPaginas.PAGINA_PRINCIPAL_PARTICIPACAO_EVENTO + "evento=" + participacao.getPedidoParticipacao().getEvento().getCodigo();
	}
	
	@Override
	public void criarFiltro() {
		super.criarFiltro();
		
		participacaoFilter.setOrganizador(organizador);
		participacaoFilter.setEvento(evento);
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

}
