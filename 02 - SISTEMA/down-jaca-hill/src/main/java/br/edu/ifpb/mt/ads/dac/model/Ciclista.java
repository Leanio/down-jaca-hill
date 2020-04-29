package br.edu.ifpb.mt.ads.dac.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Ciclista extends EntidadePersistente {

	private static final long serialVersionUID = -4131204139668859430L;

	private Boolean isProfissional;

	@Temporal(TemporalType.DATE)
	private Date dataCadastroPerfilCiclista;

	@OneToOne(optional = false)
	private Pessoa pessoa;

	public Boolean getIsProfissional() {
		return isProfissional;
	}

	public void setIsProfissional(Boolean isProfissional) {
		this.isProfissional = isProfissional;
	}

	public Date getDataCadastroPerfilCiclista() {
		return dataCadastroPerfilCiclista;
	}

	public void setDataCadastroPerfilCiclista(Date dataCadastroPerfilCiclista) {
		this.dataCadastroPerfilCiclista = dataCadastroPerfilCiclista;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((dataCadastroPerfilCiclista == null) ? 0 : dataCadastroPerfilCiclista.hashCode());
		result = prime * result + ((isProfissional == null) ? 0 : isProfissional.hashCode());
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
		Ciclista other = (Ciclista) obj;
		if (dataCadastroPerfilCiclista == null) {
			if (other.dataCadastroPerfilCiclista != null)
				return false;
		} else if (!dataCadastroPerfilCiclista.equals(other.dataCadastroPerfilCiclista))
			return false;
		if (isProfissional == null) {
			if (other.isProfissional != null)
				return false;
		} else if (!isProfissional.equals(other.isProfissional))
			return false;
		return true;
	}
	
}
