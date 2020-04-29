package br.edu.ifpb.mt.ads.dac.beans.model.evento;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.ads.dac.beans.AbstractBean;
import br.edu.ifpb.mt.ads.dac.beans.EnderecoPaginas;
import br.edu.ifpb.mt.ads.dac.filters.CidadeFilter;
import br.edu.ifpb.mt.ads.dac.model.Cidade;
import br.edu.ifpb.mt.ads.dac.model.Endereco;
import br.edu.ifpb.mt.ads.dac.model.Estado;
import br.edu.ifpb.mt.ads.dac.model.Evento;
import br.edu.ifpb.mt.ads.dac.model.ItemRecurso;
import br.edu.ifpb.mt.ads.dac.model.Organizador;
import br.edu.ifpb.mt.ads.dac.model.Premiacao;
import br.edu.ifpb.mt.ads.dac.model.Recurso;
import br.edu.ifpb.mt.ads.dac.model.enumerations.EstadoEvento;
import br.edu.ifpb.mt.ads.dac.model.enumerations.TipoEvento;
import br.edu.ifpb.mt.ads.dac.services.CidadeService;
import br.edu.ifpb.mt.ads.dac.services.EstadoService;
import br.edu.ifpb.mt.ads.dac.services.EventoService;
import br.edu.ifpb.mt.ads.dac.services.RecursoService;
import br.edu.ifpb.mt.ads.dac.services.ServiceDacException;
import br.edu.ifpb.mt.ads.dac.util.qualifiers.OrganizadorAutenticado;

@Named
@ViewScoped
public class EventoEdit extends AbstractBean {

	private static final long serialVersionUID = -8895837337958959813L;

	@Inject
	private EventoService eventoService;

	@Inject
	private EstadoService estadoService;

	@Inject
	private CidadeService cidadeService;

	@Inject
	private RecursoService recursoService;

	@Inject
	@OrganizadorAutenticado
	private Organizador organizador;

	private Evento evento;

	private Estado estadoEventoSelecionado;

	private Estado estadoPagamentoSelecionado;

	private List<Estado> estados;

	private List<Cidade> cidadesEvento;
	
	private List<Cidade> cidadesPagamento;

	private List<Recurso> recursos;

	private ItemRecurso itemRecurso;

	private Premiacao premiacao;

	public String init() {
		if (evento == null) {
			evento = new Evento();

			Endereco enderecoEvento = new Endereco();
			
			List<ItemRecurso> itemRecurso = new ArrayList<ItemRecurso>();
			List<Premiacao> premiacao = new ArrayList<Premiacao>();

			evento.setEnderecoEvento(enderecoEvento);
			evento.setItemRecurso(itemRecurso);
			evento.setPremiacao(premiacao);
			evento.setOrganizador(organizador);
		} else {
			estadoEventoSelecionado = getEstadoEvento();
			estadoPagamentoSelecionado = getEstadoPagamento();
		}
		
		carregarCidadesEvento();
		carregarCidadesPagamento();
		carregarRecursos();
		
		if (organizador == null || !organizador.equals(evento.getOrganizador())) {
			reportarMensagemDeErro("Você não tem permissão para editar este evento");
			return EnderecoPaginas.PAGINA_PRINCIPAL_EVENTO;
		}
		
		return null;
	}
	
	@PostConstruct
	public void postConstruct() {
		carregarEstados();
	}

	public void carregarEstados() {
		try {
			estados = estadoService.listar();
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
		}
	}
	
	private List<Cidade> retornarCidades(Estado estado) {
		try {
			CidadeFilter cidadeFilter = new CidadeFilter();
			cidadeFilter.setEstado(estado);
			cidadeFilter.setIsLiberadaEvento(true);

			return cidadeService.filtrar(cidadeFilter);
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
		}
		
		return null;
	}

	public void carregarRecursos() {
		try {
			recursos = recursoService.listar();
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
		}
	}

	public void carregarCidadesEvento() {
		if (isEstadoEventoEstaSelecionado()) {
			cidadesEvento = retornarCidades(estadoEventoSelecionado);
		}
	}

	public void carregarCidadesPagamento() {
		if (isEstadoPagamentoEstaSelecionado()) {
			cidadesPagamento = retornarCidades(estadoPagamentoSelecionado);
		}
	}

	public String salvar() {
		try {
			if (isEdicao()) {
				eventoService.atualizar(evento);
			} else {
				eventoService.salvar(evento);
			}
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}

		reportarMensagemDeSucesso(String.format("Evento '%s' cadastrado", evento.getTitulo()));
		return EnderecoPaginas.PAGINA_PRINCIPAL_EVENTO;
	}

	public void criarItemRecurso() {
		itemRecurso = new ItemRecurso();
		itemRecurso.setEvento(evento);
	}

	public void adicionarItemRecurso() {
		if (!evento.getItemRecurso().contains(itemRecurso)) {
			evento.adicionarItemRecurso(itemRecurso);
		}

		cancelarNovoRecurso();
	}

	public void removerItemRecurso(ItemRecurso itemRecurso) {
		evento.removerItemRecurso(itemRecurso);
	}

	public void cancelarNovoRecurso() {
		itemRecurso = null;
	}
	
	public void editarRecurso(ItemRecurso itemRecurso) {
		setItemRecurso(itemRecurso);
	}
	
	public void criarPremiacao() {
		premiacao = new Premiacao();
		premiacao.setEvento(evento);
	}
	
	public void adicionarPremiacao() {
		if (!evento.getPremiacao().contains(premiacao)) {
			evento.adicionarPremiacao(premiacao);
		}
		
		cancelarNovaPremiacao();
	}

	public void removerPremiacao(Premiacao premiacao) {
		evento.removerPremiacao(premiacao);
	}

	public void cancelarNovaPremiacao() {
		premiacao = null;
	}
	
	public void editarPremiacao(Premiacao premiacao) {
		setPremiacao(premiacao);
	}

	private Estado getEstado(Endereco endereco) {
		if (endereco == null) {
			return null;
		}
		
		Cidade cidade = endereco.getCidade();

		if (cidade == null) {
			return null;
		}

		return cidade.getEstado();
	}
	
	public Estado getEstadoEvento() {
		Endereco endereco = evento.getEnderecoEvento();
		return getEstado(endereco);
	}

	public Estado getEstadoPagamento() {
		Endereco endereco = evento.getEnderecoPagamento();
		return getEstado(endereco);
	}
	
	public boolean mostrarAdicaoRecursos() {
		return itemRecurso != null;
	}

	public boolean mostrarAdicaoPremiacao() {
		return premiacao != null;
	}

	public boolean isEdicao() {
		return evento != null && evento.getCodigo() != null;
	}

	public boolean isEstadoEventoEstaSelecionado() {
		return estadoEventoSelecionado != null;
	}

	public boolean isEstadoPagamentoEstaSelecionado() {
		return estadoPagamentoSelecionado != null;
	}

	public TipoEvento[] getTipoEventos() {
		return TipoEvento.values();
	}

	public boolean isEventoPago() {
		return evento.getTaxaParticipacao() != null && evento.getTaxaParticipacao() > 0;
	}

	public void atribuirEnderecoPagamento() {
		Endereco enderecoPagamento = evento.getEnderecoPagamento();

		if (isEventoPago() && enderecoPagamento == null) {
			enderecoPagamento = new Endereco();
			evento.setEnderecoPagamento(enderecoPagamento);
		} else if (!isEventoPago()) {
			evento.setEnderecoPagamento(null);
		}

	}
	
	public EstadoEvento[] getStatusEvento() {
		return EstadoEvento.values();
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public List<Cidade> getCidadesEvento() {
		return cidadesEvento;
	}

	public void setCidadesEvento(List<Cidade> cidadesEvento) {
		this.cidadesEvento = cidadesEvento;
	}

	public List<Cidade> getCidadesPagamento() {
		return cidadesPagamento;
	}

	public void setCidadesPagamento(List<Cidade> cidadesPagamento) {
		this.cidadesPagamento = cidadesPagamento;
	}

	public Estado getEstadoEventoSelecionado() {
		return estadoEventoSelecionado;
	}

	public void setEstadoEventoSelecionado(Estado estadoEventoSelecionado) {
		this.estadoEventoSelecionado = estadoEventoSelecionado;
	}

	public Estado getEstadoPagamentoSelecionado() {
		return estadoPagamentoSelecionado;
	}

	public void setEstadoPagamentoSelecionado(Estado estadoPagamentoSelecionado) {
		this.estadoPagamentoSelecionado = estadoPagamentoSelecionado;
	}

	public List<Recurso> getRecursos() {
		return recursos;
	}

	public void setRecursos(List<Recurso> recursos) {
		this.recursos = recursos;
	}

	public ItemRecurso getItemRecurso() {
		return itemRecurso;
	}

	public void setItemRecurso(ItemRecurso itemRecurso) {
		this.itemRecurso = itemRecurso;
	}

	public Premiacao getPremiacao() {
		return premiacao;
	}

	public void setPremiacao(Premiacao premiacao) {
		this.premiacao = premiacao;
	}
}
