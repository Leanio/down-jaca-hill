package br.edu.ifpb.mt.ads.dac.beans.model.pedidoParticipacao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.ads.dac.beans.AbstractBean;
import br.edu.ifpb.mt.ads.dac.beans.EnderecoPaginas;
import br.edu.ifpb.mt.ads.dac.filters.ModeloFilter;
import br.edu.ifpb.mt.ads.dac.model.Ciclista;
import br.edu.ifpb.mt.ads.dac.model.Evento;
import br.edu.ifpb.mt.ads.dac.model.Marca;
import br.edu.ifpb.mt.ads.dac.model.Modelo;
import br.edu.ifpb.mt.ads.dac.model.PedidoParticipacao;
import br.edu.ifpb.mt.ads.dac.model.enumerations.EstadoPedido;
import br.edu.ifpb.mt.ads.dac.services.MarcaService;
import br.edu.ifpb.mt.ads.dac.services.ModeloService;
import br.edu.ifpb.mt.ads.dac.services.PedidoParticipacaoService;
import br.edu.ifpb.mt.ads.dac.services.ServiceDacException;
import br.edu.ifpb.mt.ads.dac.util.qualifiers.CiclistaAutenticado;

@Named
@ViewScoped
public class PedidoParticipacaoEdit extends AbstractBean {

	private static final long serialVersionUID = 1134886027050653829L;
	
	@Inject
	private PedidoParticipacaoService pedidoParticipacaoService;
	
	@Inject
	private MarcaService marcaService;
	
	@Inject
	private ModeloService modeloService;
	
	@Inject
	@CiclistaAutenticado
	private Ciclista ciclista;
	
	private PedidoParticipacao pedidoParticipacao;
	
	private List<Marca> marcas;
	
	private List<Modelo> modelos;
	
	private Marca marcaSelecionada;
	
	private Evento evento;
	
	public void init() {
		if (pedidoParticipacao == null) {
			pedidoParticipacao = new PedidoParticipacao();
			
			pedidoParticipacao.setCiclista(ciclista);
			pedidoParticipacao.setEvento(evento);
		} else {
			marcaSelecionada = getMarca();
			carregarModelos();
		}
	}
	
	@PostConstruct
	public void postConstruct() {
		carregarMarcas();
	}
	
	public String salvar() {
		boolean foiEdicao = false;
		try {
			if (isEdicao()) {
				pedidoParticipacaoService.atualizar(pedidoParticipacao);
				foiEdicao = true;
			} else {
				pedidoParticipacaoService.salvar(pedidoParticipacao);
			}
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		
		reportarMensagemDeSucesso("Pedido de participação realizado");
		
		if (foiEdicao) {
			return EnderecoPaginas.PAGINA_PRINCIPAL_PEDIDOS_EVENTO  + "&evento=" + pedidoParticipacao.getEvento().getCodigo();
		}
		
		return EnderecoPaginas.PAGINA_PRINCIPAL_AUTENTICADO;
	}
	
	public void carregarMarcas() {
		try {
			marcas = marcaService.listar();
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
		}
	}
	
	public void carregarModelos() {
		try {
			if (isMarcaEstaSelecionada()) {
				ModeloFilter modeloFilter = new ModeloFilter();
				modeloFilter.setMarca(marcaSelecionada);
				
				modelos = modeloService.filtrar(modeloFilter);
			}
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
		}
	}
	
	public boolean isEdicao() {
		return pedidoParticipacao != null && pedidoParticipacao.getCodigo() != null;
	}
	
	public boolean isMarcaEstaSelecionada() {
		return marcaSelecionada != null;
	}
	
	public boolean isEventoPago() {
		return evento.getTaxaParticipacao() > 0;
	}
	
	public Marca getMarca() {
		Modelo modelo = pedidoParticipacao.getModelo();
		
		if (modelo == null) {
			return null;
		}
		
		return modelo.getMarca();
	}
	
	public EstadoPedido[] estadoPedido() {
		return EstadoPedido.values();
	}

	public PedidoParticipacao getPedidoParticipacao() {
		return pedidoParticipacao;
	}

	public void setPedidoParticipacao(PedidoParticipacao pedidoParticipacao) {
		this.pedidoParticipacao = pedidoParticipacao;
	}

	public List<Marca> getMarcas() {
		return marcas;
	}

	public void setMarcas(List<Marca> marcas) {
		this.marcas = marcas;
	}

	public List<Modelo> getModelos() {
		return modelos;
	}

	public void setModelos(List<Modelo> modelos) {
		this.modelos = modelos;
	}

	public Marca getMarcaSelecionada() {
		return marcaSelecionada;
	}

	public void setMarcaSelecionada(Marca marcaSelecionada) {
		this.marcaSelecionada = marcaSelecionada;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

}
