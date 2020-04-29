package br.edu.ifpb.mt.ads.dac.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.edu.ifpb.mt.ads.dac.model.enumerations.EstadoEvento;
import br.edu.ifpb.mt.ads.dac.model.enumerations.TipoEvento;

import java.util.Date;

@Entity
public class Evento extends EntidadePersistente {

	private static final long serialVersionUID = -6389371537610454318L;

	private String titulo;

	@Column(length = 10000)
	private String descricao;

	private Float taxaParticipacao;

	private Integer quantidadeVagas;
	
	private Integer distanciaEmMetros;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date pedidoParticipacaoDe;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date pedidoParticipacaoAte;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataEvento;

	@Temporal(TemporalType.DATE)
	private Date dataCadastroEvento;
	
	@Enumerated(EnumType.ORDINAL)
	private TipoEvento tipoEvento;

	@Enumerated(EnumType.ORDINAL)
	private EstadoEvento estadoEvento;

	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, optional = false, orphanRemoval = true)
	private Endereco enderecoEvento;

	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
	private Endereco enderecoPagamento;

	@ManyToOne
	private Organizador organizador;

	@OneToMany(mappedBy = Premiacao_.EVENTO, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
	private List<Premiacao> premiacao;

	@OneToMany(mappedBy = ItemRecurso_.EVENTO, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
	private List<ItemRecurso> itemRecurso;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Float getTaxaParticipacao() {
		return taxaParticipacao;
	}

	public void setTaxaParticipacao(Float taxaParticipacao) {
		this.taxaParticipacao = taxaParticipacao;
	}

	public Integer getQuantidadeVagas() {
		return quantidadeVagas;
	}

	public void setQuantidadeVagas(Integer quantidadeVagas) {
		this.quantidadeVagas = quantidadeVagas;
	}

	public Integer getDistanciaEmMetros() {
		return distanciaEmMetros;
	}

	public void setDistanciaEmMetros(Integer distanciaEmMetros) {
		this.distanciaEmMetros = distanciaEmMetros;
	}

	public Date getPedidoParticipacaoDe() {
		return pedidoParticipacaoDe;
	}

	public void setPedidoParticipacaoDe(Date pedidoParticipacaoDe) {
		this.pedidoParticipacaoDe = pedidoParticipacaoDe;
	}

	public Date getPedidoParticipacaoAte() {
		return pedidoParticipacaoAte;
	}

	public void setPedidoParticipacaoAte(Date pedidoParticipacaoAte) {
		this.pedidoParticipacaoAte = pedidoParticipacaoAte;
	}

	public Date getDataEvento() {
		return dataEvento;
	}

	public void setDataEvento(Date dataEvento) {
		this.dataEvento = dataEvento;
	}

	public Date getDataCadastroEvento() {
		return dataCadastroEvento;
	}

	public void setDataCadastroEvento(Date dataCadastroEvento) {
		this.dataCadastroEvento = dataCadastroEvento;
	}

	public TipoEvento getTipoEvento() {
		return tipoEvento;
	}

	public void setTipoEvento(TipoEvento tipoEvento) {
		this.tipoEvento = tipoEvento;
	}

	public EstadoEvento getEstadoEvento() {
		return estadoEvento;
	}

	public void setEstadoEvento(EstadoEvento estadoEvento) {
		this.estadoEvento = estadoEvento;
	}

	public Endereco getEnderecoEvento() {
		return enderecoEvento;
	}

	public void setEnderecoEvento(Endereco enderecoEvento) {
		this.enderecoEvento = enderecoEvento;
	}

	public Endereco getEnderecoPagamento() {
		return enderecoPagamento;
	}

	public void setEnderecoPagamento(Endereco enderecoPagamento) {
		this.enderecoPagamento = enderecoPagamento;
	}

	public Organizador getOrganizador() {
		return organizador;
	}

	public void setOrganizador(Organizador organizador) {
		this.organizador = organizador;
	}

	public List<Premiacao> getPremiacao() {
		return premiacao;
	}

	public void setPremiacao(List<Premiacao> premiacao) {
		this.premiacao = premiacao;
	}

	public List<ItemRecurso> getItemRecurso() {
		return itemRecurso;
	}

	public void setItemRecurso(List<ItemRecurso> itemRecurso) {
		this.itemRecurso = itemRecurso;
	}
	
	public void adicionarItemRecurso(ItemRecurso itemRecurso) {
		this.itemRecurso.add(itemRecurso);
	}
	
	public void adicionarPremiacao(Premiacao premiacao) {
		this.premiacao.add(premiacao);
	}
	
	public void removerItemRecurso(ItemRecurso itemRecurso) {
		this.itemRecurso.remove(itemRecurso);
	}
	
	public void removerPremiacao(Premiacao premiacao) {
		this.premiacao.remove(premiacao);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((dataCadastroEvento == null) ? 0 : dataCadastroEvento.hashCode());
		result = prime * result + ((dataEvento == null) ? 0 : dataEvento.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((distanciaEmMetros == null) ? 0 : distanciaEmMetros.hashCode());
		result = prime * result + ((enderecoEvento == null) ? 0 : enderecoEvento.hashCode());
		result = prime * result + ((enderecoPagamento == null) ? 0 : enderecoPagamento.hashCode());
		result = prime * result + ((estadoEvento == null) ? 0 : estadoEvento.hashCode());
		result = prime * result + ((itemRecurso == null) ? 0 : itemRecurso.hashCode());
		result = prime * result + ((organizador == null) ? 0 : organizador.hashCode());
		result = prime * result + ((pedidoParticipacaoAte == null) ? 0 : pedidoParticipacaoAte.hashCode());
		result = prime * result + ((pedidoParticipacaoDe == null) ? 0 : pedidoParticipacaoDe.hashCode());
		result = prime * result + ((premiacao == null) ? 0 : premiacao.hashCode());
		result = prime * result + ((quantidadeVagas == null) ? 0 : quantidadeVagas.hashCode());
		result = prime * result + ((taxaParticipacao == null) ? 0 : taxaParticipacao.hashCode());
		result = prime * result + ((tipoEvento == null) ? 0 : tipoEvento.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Evento other = (Evento) obj;
		if (dataCadastroEvento == null) {
			if (other.dataCadastroEvento != null)
				return false;
		} else if (!dataCadastroEvento.equals(other.dataCadastroEvento))
			return false;
		if (dataEvento == null) {
			if (other.dataEvento != null)
				return false;
		} else if (!dataEvento.equals(other.dataEvento))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (distanciaEmMetros == null) {
			if (other.distanciaEmMetros != null)
				return false;
		} else if (!distanciaEmMetros.equals(other.distanciaEmMetros))
			return false;
		if (enderecoEvento == null) {
			if (other.enderecoEvento != null)
				return false;
		} else if (!enderecoEvento.equals(other.enderecoEvento))
			return false;
		if (enderecoPagamento == null) {
			if (other.enderecoPagamento != null)
				return false;
		} else if (!enderecoPagamento.equals(other.enderecoPagamento))
			return false;
		if (estadoEvento != other.estadoEvento)
			return false;
		if (itemRecurso == null) {
			if (other.itemRecurso != null)
				return false;
		} else if (!itemRecurso.equals(other.itemRecurso))
			return false;
		if (organizador == null) {
			if (other.organizador != null)
				return false;
		} else if (!organizador.equals(other.organizador))
			return false;
		if (pedidoParticipacaoAte == null) {
			if (other.pedidoParticipacaoAte != null)
				return false;
		} else if (!pedidoParticipacaoAte.equals(other.pedidoParticipacaoAte))
			return false;
		if (pedidoParticipacaoDe == null) {
			if (other.pedidoParticipacaoDe != null)
				return false;
		} else if (!pedidoParticipacaoDe.equals(other.pedidoParticipacaoDe))
			return false;
		if (premiacao == null) {
			if (other.premiacao != null)
				return false;
		} else if (!premiacao.equals(other.premiacao))
			return false;
		if (quantidadeVagas == null) {
			if (other.quantidadeVagas != null)
				return false;
		} else if (!quantidadeVagas.equals(other.quantidadeVagas))
			return false;
		if (taxaParticipacao == null) {
			if (other.taxaParticipacao != null)
				return false;
		} else if (!taxaParticipacao.equals(other.taxaParticipacao))
			return false;
		if (tipoEvento != other.tipoEvento)
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}
	
}
