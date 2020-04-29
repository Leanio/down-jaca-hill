package br.edu.ifpb.mt.ads.dac.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Marca extends EntidadePersistente {

	private static final long serialVersionUID = 6941469678226949327L;

	private String nome;

	@Temporal(TemporalType.DATE)
	private Date dataCadastroMarca;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataCadastroMarca() {
		return dataCadastroMarca;
	}

	public void setDataCadastroMarca(Date dataCadastroMarca) {
		this.dataCadastroMarca = dataCadastroMarca;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((dataCadastroMarca == null) ? 0 : dataCadastroMarca.hashCode());
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
		Marca other = (Marca) obj;
		if (dataCadastroMarca == null) {
			if (other.dataCadastroMarca != null)
				return false;
		} else if (!dataCadastroMarca.equals(other.dataCadastroMarca))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
}
