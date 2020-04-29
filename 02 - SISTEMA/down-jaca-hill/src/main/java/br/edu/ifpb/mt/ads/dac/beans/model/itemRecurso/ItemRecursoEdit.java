package br.edu.ifpb.mt.ads.dac.beans.model.itemRecurso;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.ads.dac.beans.AbstractBean;
import br.edu.ifpb.mt.ads.dac.model.Evento;
import br.edu.ifpb.mt.ads.dac.model.ItemRecurso;
import br.edu.ifpb.mt.ads.dac.model.Recurso;
import br.edu.ifpb.mt.ads.dac.services.ItemRecursoService;
import br.edu.ifpb.mt.ads.dac.services.RecursoService;
import br.edu.ifpb.mt.ads.dac.services.ServiceDacException;

@Named
@ViewScoped
public class ItemRecursoEdit extends AbstractBean {

	private static final long serialVersionUID = -5265997205446491973L;
	
	@Inject
	private ItemRecursoService itemRecursoService;
	
	@Inject
	private RecursoService recursoService;
	
	private ItemRecurso itemRecurso;
	
	private Evento evento;
	
	private List<Recurso> recursos;
	
	public void init() {
		if (itemRecurso == null) {
			itemRecurso = new ItemRecurso();
			
			itemRecurso.setEvento(evento);
			evento.adicionarItemRecurso(itemRecurso);
		}
		
	}
	
	@PostConstruct
	public void postConstruct() {
		carregarRecursos();
	}
	
	public String salvar() {
		try {
			if (isEdicao()) {
				itemRecursoService.atualizar(itemRecurso);
			} else {
				itemRecursoService.salvar(itemRecurso);
			}
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		
		reportarMensagemDeSucesso("Recurso adicionado ao evento!");
		return "/paginas/protegido/index.xhtml";
	}
	
	public void carregarRecursos() {
		try {
			recursos = recursoService.listar();
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
		}
	}
	
	public boolean isEdicao() {
		return itemRecurso != null && itemRecurso.getCodigo() != null;
	}

	public ItemRecurso getItemRecurso() {
		return itemRecurso;
	}

	public void setItemRecurso(ItemRecurso itemRecurso) {
		this.itemRecurso = itemRecurso;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public List<Recurso> getRecursos() {
		return recursos;
	}

	public void setRecursos(List<Recurso> recursos) {
		this.recursos = recursos;
	}

}
