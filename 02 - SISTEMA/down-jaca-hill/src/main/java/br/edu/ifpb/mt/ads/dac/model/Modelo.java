package br.edu.ifpb.mt.ads.dac.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Modelo extends EntidadePersistente {

	private static final long serialVersionUID = -3215189432099410047L;

	private String nome;

	@Temporal(TemporalType.DATE)
	private Date dataCadastroModelo;

	@ManyToOne(optional = false)
	private Marca marca;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataCadastroModelo() {
		return dataCadastroModelo;
	}

	public void setDataCadastroModelo(Date dataCadastroModelo) {
		this.dataCadastroModelo = dataCadastroModelo;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((dataCadastroModelo == null) ? 0 : dataCadastroModelo.hashCode());
		result = prime * result + ((marca == null) ? 0 : marca.hashCode());
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
		Modelo other = (Modelo) obj;
		if (dataCadastroModelo == null) {
			if (other.dataCadastroModelo != null)
				return false;
		} else if (!dataCadastroModelo.equals(other.dataCadastroModelo))
			return false;
		if (marca == null) {
			if (other.marca != null)
				return false;
		} else if (!marca.equals(other.marca))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

}
