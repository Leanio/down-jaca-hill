package br.edu.ifpb.mt.ads.dac.services.impl;

import java.util.Date;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.edu.ifpb.mt.ads.dac.dao.ContaDAO;
import br.edu.ifpb.mt.ads.dac.dao.PersistenciaDacException;
import br.edu.ifpb.mt.ads.dac.model.Conta;
import br.edu.ifpb.mt.ads.dac.model.TokenRedefinicaoSenha;
import br.edu.ifpb.mt.ads.dac.services.ContaService;
import br.edu.ifpb.mt.ads.dac.services.RoleService;
import br.edu.ifpb.mt.ads.dac.services.ServiceDacException;
import br.edu.ifpb.mt.ads.dac.util.interceptors.TransacionalCdi;

import static br.edu.ifpb.mt.ads.dac.util.criptografia.CriptografiaSHA256.getStringCriptografada;

@ApplicationScoped
public class ContaServiceImpl implements ContaService {

	private static final long serialVersionUID = -5735334402745508744L;

	@Inject
	private ContaDAO contaDAO;
	
	@Inject
	private RoleService roleService;

	@TransacionalCdi
	public void salvar(Conta conta) throws ServiceDacException {
		try {
			validarLoginUnico(conta);
			validarEmailUnico(conta);

			String senhaSemHash = conta.getSenha();
			String senhaComHash = getStringCriptografada(senhaSemHash);

			Date dataCadastroContaAcesso = new Date();

			conta.setAtiva(true);
			conta.setSenha(senhaComHash);
			conta.setDataCadastroContaAcesso(dataCadastroContaAcesso);
			
			contaDAO.salvar(conta);
			roleService.adicionarRoleDeGrupo(conta, "CONTA");
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	@TransacionalCdi
	public void remover(Long codigo) throws ServiceDacException {
		try {
			Conta contaAtual = contaDAO.buscarPeloCodigo(codigo);
			contaAtual.setAtiva(false);

			contaDAO.atualizar(contaAtual);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	@TransacionalCdi
	public Conta atualizar(Conta conta) throws ServiceDacException {
		try {
			validarLoginUnico(conta);
			validarEmailUnico(conta);

			Long codigo = conta.getCodigo();
			Conta contaAtual = contaDAO.buscarPeloCodigo(codigo);

			String login = conta.getLogin();
			String email = conta.getEmail();

			contaAtual.setLogin(login);
			contaAtual.setEmail(email);

			return contaDAO.atualizar(contaAtual);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	@Override
	public Conta buscarPeloCodigo(Long codigo) throws ServiceDacException {
		try {
			return contaDAO.buscarPeloCodigo(codigo);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	@Override
	public List<Conta> listar() throws ServiceDacException {
		try {
			return contaDAO.listar();
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	@Override
	public Conta buscarPeloLogin(String login) throws ServiceDacException {
		try {
			return contaDAO.buscarPeloLogin(login);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	@TransacionalCdi
	public Conta atualizarSenha(Conta conta, String supostaSenhaSemHash, String novaSenhaSemHash)
			throws ServiceDacException {
		try {
			Long codigo = conta.getCodigo();
			Conta contaAtual = contaDAO.buscarPeloCodigo(codigo);

			String senhaAtualComHash = contaAtual.getSenha();

			if (!senhaConfere(senhaAtualComHash, supostaSenhaSemHash)) {
				throw new ServiceDacException("Suposta senha incorreta");
			}

			String novaSenhaComHash = getStringCriptografada(novaSenhaSemHash);

			contaAtual.setSenha(novaSenhaComHash);

			return contaDAO.atualizar(contaAtual);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	@TransacionalCdi
	public Conta atualizarSenhaComToken(Conta conta, String novaSenhaSemHash) throws ServiceDacException {
		try {
			Long codigo = conta.getCodigo();
			Conta contaAtual = contaDAO.buscarPeloCodigo(codigo);
			
			TokenRedefinicaoSenha tokenRedefinicaoSenha = conta.getTokenRedefinicaoSenha();
			TokenRedefinicaoSenha tokenRedefinicaoSenhaAtual = contaAtual.getTokenRedefinicaoSenha();
			
			if (!tokenRedefinicaoSenha.equals(tokenRedefinicaoSenhaAtual)) {
				throw new ServiceDacException("Token inválido");
			}
			
			Date dataVencimento = tokenRedefinicaoSenhaAtual.getDataVencimento();
			Date hoje = new Date();
			
			if (dataVencimento.before(hoje)) {
				throw new ServiceDacException("Token expirado");
			}

			String novaSenhaComHash = getStringCriptografada(novaSenhaSemHash);

			contaAtual.setSenha(novaSenhaComHash);
			contaAtual.setTokenRedefinicaoSenha(null);

			return contaDAO.atualizar(contaAtual);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	private boolean senhaConfere(String senhaHash, String supostaSenha) throws ServiceDacException {
		if (senhaHash == null && supostaSenha == null) {
			return true;
		}

		if (senhaHash == null || supostaSenha == null) {
			return false;
		}

		String supostaSenhaHash = getStringCriptografada(supostaSenha);

		return senhaHash.equals(supostaSenhaHash);
	}

	private void validarLoginUnico(Conta conta) throws ServiceDacException {
		if (contaDAO.existeOutraContaComMesmoLogin(conta)) {
			throw new ServiceDacException("Login em uso! escolha outro!");
		}
	}

	private void validarEmailUnico(Conta conta) throws ServiceDacException {
		if (contaDAO.existeOutraContaComMesmoEmail(conta)) {
			throw new ServiceDacException("Email em uso! escolha outro!");
		}
	}

}
