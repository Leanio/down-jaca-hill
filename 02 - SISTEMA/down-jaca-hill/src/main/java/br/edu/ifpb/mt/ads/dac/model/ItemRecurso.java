package br.edu.ifpb.mt.ads.dac.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class ItemRecurso extends EntidadePersistente {

	private static final long serialVersionUID = -3748154607113468500L;
	
	private String observacao;

	private Integer quantidade;

	private Float gastoUnitario;

	@ManyToOne
	private Recurso recurso;

	@ManyToOne
	private Evento evento;

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Float getGastoUnitario() {
		return gastoUnitario;
	}

	public void setGastoUnitario(Float gastoUnitario) {
		this.gastoUnitario = gastoUnitario;
	}

	public Recurso getRecurso() {
		return recurso;
	}

	public void setRecurso(Recurso recurso) {
		this.recurso = recurso;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((evento == null) ? 0 : evento.hashCode());
		result = prime * result + ((gastoUnitario == null) ? 0 : gastoUnitario.hashCode());
		result = prime * result + ((observacao == null) ? 0 : observacao.hashCode());
		result = prime * result + ((quantidade == null) ? 0 : quantidade.hashCode());
		result = prime * result + ((recurso == null) ? 0 : recurso.hashCode());
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
		ItemRecurso other = (ItemRecurso) obj;
		if (evento == null) {
			if (other.evento != null)
				return false;
		} else if (!evento.equals(other.evento))
			return false;
		if (gastoUnitario == null) {
			if (other.gastoUnitario != null)
				return false;
		} else if (!gastoUnitario.equals(other.gastoUnitario))
			return false;
		if (observacao == null) {
			if (other.observacao != null)
				return false;
		} else if (!observacao.equals(other.observacao))
			return false;
		if (quantidade == null) {
			if (other.quantidade != null)
				return false;
		} else if (!quantidade.equals(other.quantidade))
			return false;
		if (recurso == null) {
			if (other.recurso != null)
				return false;
		} else if (!recurso.equals(other.recurso))
			return false;
		return true;
	}
	
}
