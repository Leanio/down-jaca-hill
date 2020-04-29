package br.edu.ifpb.mt.ads.dac.beans.model.ciclista;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.ads.dac.beans.AbstractBean;
import br.edu.ifpb.mt.ads.dac.services.CiclistaService;
import br.edu.ifpb.mt.ads.dac.services.ServiceDacException;

@Named
@ViewScoped
public class Ranking extends AbstractBean {

	private static final long serialVersionUID = 5781940583004792041L;
	
	@Inject
	private CiclistaService ciclistaService;
	
	private List<Object[]> ciclistas;
	
	@PostConstruct
	public void ranquear() {
		try {
			ciclistas = ciclistaService.ranking();
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
		}
	}

	public List<Object[]> getCiclistas() {
		return ciclistas;
	}

	public void setCiclistas(List<Object[]> ciclistas) {
		this.ciclistas = ciclistas;
	}

}
