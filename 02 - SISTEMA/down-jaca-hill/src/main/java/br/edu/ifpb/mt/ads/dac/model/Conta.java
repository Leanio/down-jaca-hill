package br.edu.ifpb.mt.ads.dac.model;

import java.util.List;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Conta extends EntidadePersistente {

	private static final long serialVersionUID = -8541809985079002702L;

	private String login;

	private String senha;

	private String email;

	private Boolean ativa;

	@Temporal(TemporalType.DATE)
	private Date dataCadastroContaAcesso;

	@OneToOne(mappedBy = Pessoa_.CONTA)
	private Pessoa pessoa;

	@ManyToMany
	private List<Grupo> grupo;

	@OneToOne(mappedBy = TokenRedefinicaoSenha_.CONTA, orphanRemoval = true)
	private TokenRedefinicaoSenha tokenRedefinicaoSenha;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getAtiva() {
		return ativa;
	}

	public void setAtiva(Boolean ativa) {
		this.ativa = ativa;
	}

	public Date getDataCadastroContaAcesso() {
		return dataCadastroContaAcesso;
	}

	public void setDataCadastroContaAcesso(Date dataCadastroContaAcesso) {
		this.dataCadastroContaAcesso = dataCadastroContaAcesso;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Grupo> getGrupo() {
		return grupo;
	}

	public void setGrupo(List<Grupo> grupo) {
		this.grupo = grupo;
	}

	public TokenRedefinicaoSenha getTokenRedefinicaoSenha() {
		return tokenRedefinicaoSenha;
	}

	public void setTokenRedefinicaoSenha(TokenRedefinicaoSenha tokenRedefinicaoSenha) {
		this.tokenRedefinicaoSenha = tokenRedefinicaoSenha;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((ativa == null) ? 0 : ativa.hashCode());
		result = prime * result + ((dataCadastroContaAcesso == null) ? 0 : dataCadastroContaAcesso.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((grupo == null) ? 0 : grupo.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((pessoa == null) ? 0 : pessoa.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		result = prime * result + ((tokenRedefinicaoSenha == null) ? 0 : tokenRedefinicaoSenha.hashCode());
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
		Conta other = (Conta) obj;
		if (ativa == null) {
			if (other.ativa != null)
				return false;
		} else if (!ativa.equals(other.ativa))
			return false;
		if (dataCadastroContaAcesso == null) {
			if (other.dataCadastroContaAcesso != null)
				return false;
		} else if (!dataCadastroContaAcesso.equals(other.dataCadastroContaAcesso))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (grupo == null) {
			if (other.grupo != null)
				return false;
		} else if (!grupo.equals(other.grupo))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (pessoa == null) {
			if (other.pessoa != null)
				return false;
		} else if (!pessoa.equals(other.pessoa))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		if (tokenRedefinicaoSenha == null) {
			if (other.tokenRedefinicaoSenha != null)
				return false;
		} else if (!tokenRedefinicaoSenha.equals(other.tokenRedefinicaoSenha))
			return false;
		return true;
	}
	
}
