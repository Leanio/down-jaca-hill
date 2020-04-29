package br.edu.ifpb.mt.ads.dac.services.impl;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.edu.ifpb.mt.ads.dac.dao.ContaDAO;
import br.edu.ifpb.mt.ads.dac.dao.GrupoDAO;
import br.edu.ifpb.mt.ads.dac.dao.PersistenciaDacException;
import br.edu.ifpb.mt.ads.dac.model.Conta;
import br.edu.ifpb.mt.ads.dac.model.Grupo;
import br.edu.ifpb.mt.ads.dac.services.RoleService;
import br.edu.ifpb.mt.ads.dac.services.ServiceDacException;
import br.edu.ifpb.mt.ads.dac.util.interceptors.FlushCacheCdi;
import br.edu.ifpb.mt.ads.dac.util.interceptors.TransacionalCdi;

@ApplicationScoped
public class RoleServiceImpl implements RoleService {

	private static final long serialVersionUID = 3127215128900614443L;

	@Inject
	private ContaDAO contaDAO;

	@Inject
	private GrupoDAO grupoDAO;

	@FlushCacheCdi
	@TransacionalCdi
	public void adicionarRoleDeGrupo(Conta conta, String nomeGrupo) throws ServiceDacException {
		try {
			Long codigo = conta.getCodigo();
			Conta contaAtual = contaDAO.buscarPeloCodigo(codigo);

			List<Grupo> gruposDaConta = contaAtual.getGrupo();

			Grupo grupo = grupoDAO.buscarPeloNome(nomeGrupo);

			if (gruposDaConta.contains(grupo)) {
				return;
			}

			gruposDaConta.add(grupo);

			contaDAO.atualizar(contaAtual);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}

	}

	@FlushCacheCdi
	@TransacionalCdi
	public void removerRoleDeGrupo(Conta conta, String nomeGrupo) throws ServiceDacException {
		try {
			Long codigo = conta.getCodigo();
			Conta contaAtual = contaDAO.buscarPeloCodigo(codigo);

			List<Grupo> gruposDaConta = contaAtual.getGrupo();

			Grupo grupo = grupoDAO.buscarPeloNome(nomeGrupo);

			gruposDaConta.remove(grupo);

			contaDAO.atualizar(contaAtual);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}

	}

}
