package br.edu.ifpb.mt.ads.dac.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Cidade extends EntidadePersistente {

	private static final long serialVersionUID = 2330223366254079187L;

	private String nome;

	private Boolean isLiberadaEvento;

	@ManyToOne(optional = false)
	private Estado estado;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Boolean getIsLiberadaEvento() {
		return isLiberadaEvento;
	}

	public void setIsLiberadaEvento(Boolean isLiberadaEvento) {
		this.isLiberadaEvento = isLiberadaEvento;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((isLiberadaEvento == null) ? 0 : isLiberadaEvento.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Cidade other = (Cidade) obj;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (isLiberadaEvento == null) {
			if (other.isLiberadaEvento != null)
				return false;
		} else if (!isLiberadaEvento.equals(other.isLiberadaEvento))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

}
