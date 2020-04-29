package br.edu.ifpb.mt.ads.dac.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.edu.ifpb.mt.ads.dac.model.enumerations.EstadoPedido;

@Entity
public class PedidoParticipacao extends EntidadePersistente {

	private static final long serialVersionUID = 2980784987742642828L;

	private Float taxaParticipacao;

	@Temporal(TemporalType.DATE)
	private Date dataAtualizacaoEstado;

	@Temporal(TemporalType.DATE)
	private Date dataCadastroPedidoParticipacao;

	@Enumerated(EnumType.ORDINAL)
	private EstadoPedido estadoPedidoParticipacao;

	@ManyToOne(optional = false)
	private Ciclista ciclista;

	@ManyToOne(optional = false)
	private Evento evento;

	@ManyToOne(optional = false)
	private Modelo modelo;

	@OneToOne
	private Participacao participacao;

	@OneToOne
	private Pagamento pagamento;

	public Float getTaxaParticipacao() {
		return taxaParticipacao;
	}

	public void setTaxaParticipacao(Float taxaParticipacao) {
		this.taxaParticipacao = taxaParticipacao;
	}

	public Date getDataAtualizacaoEstado() {
		return dataAtualizacaoEstado;
	}

	public void setDataAtualizacaoEstado(Date dataAtualizacaoEstado) {
		this.dataAtualizacaoEstado = dataAtualizacaoEstado;
	}

	public Date getDataCadastroPedidoParticipacao() {
		return dataCadastroPedidoParticipacao;
	}

	public void setDataCadastroPedidoParticipacao(Date dataCadastroPedidoParticipacao) {
		this.dataCadastroPedidoParticipacao = dataCadastroPedidoParticipacao;
	}

	public EstadoPedido getEstadoPedidoParticipacao() {
		return estadoPedidoParticipacao;
	}

	public void setEstadoPedidoParticipacao(EstadoPedido estadoPedidoParticipacao) {
		this.estadoPedidoParticipacao = estadoPedidoParticipacao;
	}

	public Ciclista getCiclista() {
		return ciclista;
	}

	public void setCiclista(Ciclista ciclista) {
		this.ciclista = ciclista;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public Participacao getParticipacao() {
		return participacao;
	}

	public void setParticipacao(Participacao participacao) {
		this.participacao = participacao;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((ciclista == null) ? 0 : ciclista.hashCode());
		result = prime * result + ((dataAtualizacaoEstado == null) ? 0 : dataAtualizacaoEstado.hashCode());
		result = prime * result
				+ ((dataCadastroPedidoParticipacao == null) ? 0 : dataCadastroPedidoParticipacao.hashCode());
		result = prime * result + ((estadoPedidoParticipacao == null) ? 0 : estadoPedidoParticipacao.hashCode());
		result = prime * result + ((modelo == null) ? 0 : modelo.hashCode());
		result = prime * result + ((taxaParticipacao == null) ? 0 : taxaParticipacao.hashCode());
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
		PedidoParticipacao other = (PedidoParticipacao) obj;
		if (ciclista == null) {
			if (other.ciclista != null)
				return false;
		} else if (!ciclista.equals(other.ciclista))
			return false;
		if (dataAtualizacaoEstado == null) {
			if (other.dataAtualizacaoEstado != null)
				return false;
		} else if (!dataAtualizacaoEstado.equals(other.dataAtualizacaoEstado))
			return false;
		if (dataCadastroPedidoParticipacao == null) {
			if (other.dataCadastroPedidoParticipacao != null)
				return false;
		} else if (!dataCadastroPedidoParticipacao.equals(other.dataCadastroPedidoParticipacao))
			return false;
		if (estadoPedidoParticipacao != other.estadoPedidoParticipacao)
			return false;
		if (modelo == null) {
			if (other.modelo != null)
				return false;
		} else if (!modelo.equals(other.modelo))
			return false;
		if (taxaParticipacao == null) {
			if (other.taxaParticipacao != null)
				return false;
		} else if (!taxaParticipacao.equals(other.taxaParticipacao))
			return false;
		return true;
	}

}
