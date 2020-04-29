package br.edu.ifpb.mt.ads.dac.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Premiacao extends EntidadePersistente implements Comparable<Premiacao> {

	private static final long serialVersionUID = 1740238025656903779L;

	private Integer colocacao;

	private Float valor;

	@ManyToOne(optional = false)
	private Evento evento;

	@OneToOne
	private Participacao participacao;

	public Integer getColocacao() {
		return colocacao;
	}

	public void setColocacao(Integer colocacao) {
		this.colocacao = colocacao;
	}

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Participacao getParticipacao() {
		return participacao;
	}

	public void setParticipacao(Participacao participacao) {
		this.participacao = participacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((colocacao == null) ? 0 : colocacao.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
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
		Premiacao other = (Premiacao) obj;
		if (colocacao == null) {
			if (other.colocacao != null)
				return false;
		} else if (!colocacao.equals(other.colocacao))
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}

	@Override
	public int compareTo(Premiacao o) {
		if (colocacao > o.getColocacao()) {
			return 1;
		}
		
		if (colocacao < o.getColocacao()) {
			return -1;
		}
		
		return 0;
	}
	
}
