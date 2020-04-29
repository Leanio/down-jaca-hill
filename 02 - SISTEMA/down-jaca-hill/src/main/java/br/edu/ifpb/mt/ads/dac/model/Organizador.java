package br.edu.ifpb.mt.ads.dac.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Organizador extends EntidadePersistente {

	private static final long serialVersionUID = -5028488151337163326L;

	private String rg;

	@Temporal(TemporalType.DATE)
	private Date dataCadastroPerfilOrganizador;

	@OneToOne(optional = false)
	private Pessoa pessoa;

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public Date getDataCadastroPerfilOrganizador() {
		return dataCadastroPerfilOrganizador;
	}

	public void setDataCadastroPerfilOrganizador(Date dataCadastroPerfilOrganizador) {
		this.dataCadastroPerfilOrganizador = dataCadastroPerfilOrganizador;
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
		result = prime * result
				+ ((dataCadastroPerfilOrganizador == null) ? 0 : dataCadastroPerfilOrganizador.hashCode());
		result = prime * result + ((rg == null) ? 0 : rg.hashCode());
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
		Organizador other = (Organizador) obj;
		if (dataCadastroPerfilOrganizador == null) {
			if (other.dataCadastroPerfilOrganizador != null)
				return false;
		} else if (!dataCadastroPerfilOrganizador.equals(other.dataCadastroPerfilOrganizador))
			return false;
		if (rg == null) {
			if (other.rg != null)
				return false;
		} else if (!rg.equals(other.rg))
			return false;
		return true;
	}

}
