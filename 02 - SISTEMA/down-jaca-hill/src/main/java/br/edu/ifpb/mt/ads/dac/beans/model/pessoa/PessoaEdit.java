package br.edu.ifpb.mt.ads.dac.beans.model.pessoa;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.ads.dac.beans.AbstractBean;
import br.edu.ifpb.mt.ads.dac.beans.EnderecoPaginas;
import br.edu.ifpb.mt.ads.dac.filters.CidadeFilter;
import br.edu.ifpb.mt.ads.dac.model.Cidade;
import br.edu.ifpb.mt.ads.dac.model.Conta;
import br.edu.ifpb.mt.ads.dac.model.Endereco;
import br.edu.ifpb.mt.ads.dac.model.Estado;
import br.edu.ifpb.mt.ads.dac.model.Pessoa;
import br.edu.ifpb.mt.ads.dac.model.enumerations.Sexo;
import br.edu.ifpb.mt.ads.dac.services.CidadeService;
import br.edu.ifpb.mt.ads.dac.services.EstadoService;
import br.edu.ifpb.mt.ads.dac.services.PessoaService;
import br.edu.ifpb.mt.ads.dac.services.ServiceDacException;
import br.edu.ifpb.mt.ads.dac.util.qualifiers.ContaAutenticada;

@Named
@ViewScoped
public class PessoaEdit extends AbstractBean {

	private static final long serialVersionUID = -269295237781481793L;

	@Inject
	private PessoaService pessoaService;

	@Inject
	private CidadeService cidadeService;

	@Inject
	private EstadoService estadoService;

	private List<Estado> estados;

	private List<Cidade> cidades;

	private Estado estadoSelecionado;

	@Inject
	@ContaAutenticada
	private Conta conta;

	private Pessoa pessoa;

	public void init() {
		if (pessoa == null) {
			pessoa = conta.getPessoa();

			if (pessoa == null) {
				pessoa = new Pessoa();

				Endereco endereco = new Endereco();

				pessoa.setConta(conta);
				pessoa.setEndereco(endereco);

				conta.setPessoa(pessoa);
			} 
		} 
		
		estadoSelecionado = getEstadoPessoa();
		carregarCidades();
	}
	
	@PostConstruct
	public void postConstruct() {
		carregarEstados();
	}

	public String salvar() {
		try {
			if (isEdicao()) {
				pessoaService.atualizar(pessoa);
			} else {
				pessoaService.salvar(pessoa);
			}
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}

		reportarMensagemDeSucesso(String.format("Pessoa '%s' com CPF '%s' cadastrada!", pessoa.getNome(), pessoa.getCpf()));
		return EnderecoPaginas.PAGINA_PRINCIPAL_PESSOA;
	}

	public void carregarEstados() {
		try {
			estados = estadoService.listar();
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
		}
	}

	public void carregarCidades() {
		try {
			if (isEstadoEstaSelecionado()) {
				CidadeFilter cidadeFilter = new CidadeFilter();
				cidadeFilter.setEstado(estadoSelecionado);
	
				cidades = cidadeService.filtrar(cidadeFilter);
			}
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
		}
	}

	public boolean isEdicao() {
		return pessoa != null && pessoa.getCodigo() != null;
	}

	public boolean isEstadoEstaSelecionado() {
		return estadoSelecionado != null;
	}

	public Estado getEstadoPessoa() {
		Endereco enderecoPessoa = pessoa.getEndereco();
		Cidade cidadePessoa = enderecoPessoa.getCidade();

		if (cidadePessoa == null) {
			return null;
		}

		return cidadePessoa.getEstado();
	}
	
	public Sexo[] getSexo() {
		return Sexo.values();
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public Estado getEstadoSelecionado() {
		return estadoSelecionado;
	}

	public void setEstadoSelecionado(Estado estadoSelecionado) {
		this.estadoSelecionado = estadoSelecionado;
	}

}
