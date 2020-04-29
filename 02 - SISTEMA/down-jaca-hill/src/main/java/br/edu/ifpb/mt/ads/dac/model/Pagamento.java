package br.edu.ifpb.mt.ads.dac.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.edu.ifpb.mt.ads.dac.model.enumerations.MeioPagamento;

@Entity
public class Pagamento extends EntidadePersistente {

	private static final long serialVersionUID = 6484487455593302439L;

	private Integer quantidadeParcelas;

	@Temporal(TemporalType.DATE)
	private Date dataPagamento;

	@Enumerated(EnumType.ORDINAL)
	private MeioPagamento meioPagamento;

	@OneToOne(mappedBy = PedidoParticipacao_.PAGAMENTO, cascade = {CascadeType.MERGE}, optional = false)
	private PedidoParticipacao pedidoParticipacao;

	public Integer getQuantidadeParcelas() {
		return quantidadeParcelas;
	}

	public void setQuantidadeParcelas(Integer quantidadeParcelas) {
		this.quantidadeParcelas = quantidadeParcelas;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public MeioPagamento getMeioPagamento() {
		return meioPagamento;
	}

	public void setMeioPagamento(MeioPagamento meioPagamento) {
		this.meioPagamento = meioPagamento;
	}

	public PedidoParticipacao getPedidoParticipacao() {
		return pedidoParticipacao;
	}

	public void setPedidoParticipacao(PedidoParticipacao pedidoParticipacao) {
		this.pedidoParticipacao = pedidoParticipacao;
	}
	
	@PrePersist
	public void prePersist() {
		pedidoParticipacao.setPagamento(this);
	}
	
	@PreRemove
	public void preRemove() {
		pedidoParticipacao.setPagamento(null);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((dataPagamento == null) ? 0 : dataPagamento.hashCode());
		result = prime * result + ((meioPagamento == null) ? 0 : meioPagamento.hashCode());
		result = prime * result + ((quantidadeParcelas == null) ? 0 : quantidadeParcelas.hashCode());
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
		Pagamento other = (Pagamento) obj;
		if (dataPagamento == null) {
			if (other.dataPagamento != null)
				return false;
		} else if (!dataPagamento.equals(other.dataPagamento))
			return false;
		if (meioPagamento != other.meioPagamento)
			return false;
		if (quantidadeParcelas == null) {
			if (other.quantidadeParcelas != null)
				return false;
		} else if (!quantidadeParcelas.equals(other.quantidadeParcelas))
			return false;
		return true;
	}



}
