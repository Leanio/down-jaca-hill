package br.edu.ifpb.mt.ads.dac.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Participacao extends EntidadePersistente {

	private static final long serialVersionUID = -420336693042455115L;

	@Temporal(TemporalType.TIME)
	private Date tempoGasto;

	@OneToOne(mappedBy = Premiacao_.PARTICIPACAO)
	private Premiacao premiacao;

	@OneToOne(mappedBy = PedidoParticipacao_.PARTICIPACAO, cascade = {CascadeType.MERGE}, optional = false)
	private PedidoParticipacao pedidoParticipacao;

	public Date getTempoGasto() {
		return tempoGasto;
	}

	public void setTempoGasto(Date tempoGasto) {
		this.tempoGasto = tempoGasto;
	}

	public Premiacao getPremiacao() {
		return premiacao;
	}

	public void setPremiacao(Premiacao premiacao) {
		this.premiacao = premiacao;
	}

	public PedidoParticipacao getPedidoParticipacao() {
		return pedidoParticipacao;
	}

	public void setPedidoParticipacao(PedidoParticipacao pedidoParticipacao) {
		this.pedidoParticipacao = pedidoParticipacao;
	}
	
	@PrePersist
	public void prePersist() {
		pedidoParticipacao.setParticipacao(this);
	}
	
	@PreRemove
	public void preRemove() {
		pedidoParticipacao.setParticipacao(null);
		
		if (premiacao != null) {
			premiacao.setParticipacao(null);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((premiacao == null) ? 0 : premiacao.hashCode());
		result = prime * result + ((tempoGasto == null) ? 0 : tempoGasto.hashCode());
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
		Participacao other = (Participacao) obj;
		if (premiacao == null) {
			if (other.premiacao != null)
				return false;
		} else if (!premiacao.equals(other.premiacao))
			return false;
		if (tempoGasto == null) {
			if (other.tempoGasto != null)
				return false;
		} else if (!tempoGasto.equals(other.tempoGasto))
			return false;
		return true;
	}

}
