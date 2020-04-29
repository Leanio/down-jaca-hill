package br.edu.ifpb.mt.ads.dac.beans.model.participacao;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.ads.dac.model.Ciclista;
import br.edu.ifpb.mt.ads.dac.util.qualifiers.CiclistaAutenticado;

@Named
@ViewScoped
public class ManageParticipacaoCiclista extends ManageParticipacao {

	private static final long serialVersionUID = -7825040487846870806L;
	
	@Inject
	@CiclistaAutenticado
	private Ciclista ciclista;
	
	@Override
	public void criarFiltro() {
		super.criarFiltro();
		
		participacaoFilter.setCiclista(ciclista);
	}

}
