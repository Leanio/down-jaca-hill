package br.edu.ifpb.mt.ads.dac.beans.model.evento;

import java.util.Date;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class ManageEventoVisitante extends ManageEvento {

	private static final long serialVersionUID = 5814244854069400308L;
	
	@Override
	public void criarFiltro() {
		super.criarFiltro();
		
		Date dataEventoDe = new Date();
	
		eventoFilter.setDataEventoDe(dataEventoDe);
	}

}
