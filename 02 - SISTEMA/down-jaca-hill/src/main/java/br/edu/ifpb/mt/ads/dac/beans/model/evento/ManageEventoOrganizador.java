package br.edu.ifpb.mt.ads.dac.beans.model.evento;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.ads.dac.beans.EnderecoPaginas;
import br.edu.ifpb.mt.ads.dac.model.Evento;
import br.edu.ifpb.mt.ads.dac.model.Organizador;
import br.edu.ifpb.mt.ads.dac.services.ServiceDacException;
import br.edu.ifpb.mt.ads.dac.util.qualifiers.OrganizadorAutenticado;

@Named
@ViewScoped
public class ManageEventoOrganizador extends ManageEvento {

	private static final long serialVersionUID = 2787157525557634361L;

	@Inject
	@OrganizadorAutenticado
	private Organizador organizador;
	
	public String remover(Evento evento) {
		try {
			Long codigo = evento.getCodigo();
			
			eventoService.remover(codigo);
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		
		reportarMensagemDeSucesso(String.format("Evento '%s' removido", evento.getTitulo()));
		return EnderecoPaginas.PAGINA_PRINCIPAL_EVENTO;
	}
	
	public void atribuirVencedores(Evento evento) {
		try {
			eventoService.definirVencedores(evento);
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return;
		}
		
		reportarMensagemDeSucesso(String.format("Evento '%s' com vencedores definidos", evento.getTitulo()));
	}

	@Override
	public void criarFiltro() {
		super.criarFiltro();

		eventoFilter.setOrganizador(organizador);
	}

}
