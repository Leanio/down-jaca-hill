package br.edu.ifpb.mt.ads.dac.services;

import java.io.Serializable;

import br.edu.ifpb.mt.ads.dac.model.Conta;

public interface RoleService extends Serializable {

	public void adicionarRoleDeGrupo(Conta conta, String nomeGrupo) throws ServiceDacException;

	public void removerRoleDeGrupo(Conta conta, String nomeGrupo) throws ServiceDacException;

}
