package br.edu.ifpb.mt.ads.dac.services.impl;

import java.util.Date;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.edu.ifpb.mt.ads.dac.dao.PersistenciaDacException;
import br.edu.ifpb.mt.ads.dac.dao.TokenRedefinicaoSenhaDAO;
import br.edu.ifpb.mt.ads.dac.model.Conta;
import br.edu.ifpb.mt.ads.dac.model.TokenRedefinicaoSenha;
import br.edu.ifpb.mt.ads.dac.services.ServiceDacException;
import br.edu.ifpb.mt.ads.dac.services.TokenRedefinicaoSenhaService;
import br.edu.ifpb.mt.ads.dac.util.date.DateUtil;
import br.edu.ifpb.mt.ads.dac.util.interceptors.TransacionalCdi;

import static br.edu.ifpb.mt.ads.dac.util.date.DateUtil.somar;
import static br.edu.ifpb.mt.ads.dac.util.criptografia.CriptografiaSHA256.getStringCriptografada;

@ApplicationScoped
public class TokenRedefinicaoSenhaServiceImpl implements TokenRedefinicaoSenhaService {

	private static final long serialVersionUID = 2561884628071023512L;
	
	@Inject
	private TokenRedefinicaoSenhaDAO tokenRedefinicaoSenhaDAO;

	@TransacionalCdi
	public void salvar(TokenRedefinicaoSenha tokenRedefinicaoSenha) throws ServiceDacException {
		try {
			configurarTokenRedefinicaoSenha(tokenRedefinicaoSenha);
			
			tokenRedefinicaoSenhaDAO.salvar(tokenRedefinicaoSenha);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
		
	}

	@TransacionalCdi
	public void remover(Long codigo) throws ServiceDacException {
		try {
			tokenRedefinicaoSenhaDAO.remover(codigo);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	@TransacionalCdi
	public TokenRedefinicaoSenha atualizar(TokenRedefinicaoSenha tokenRedefinicaoSenha) throws ServiceDacException {
		try {
			configurarTokenRedefinicaoSenha(tokenRedefinicaoSenha);
			
			return tokenRedefinicaoSenhaDAO.atualizar(tokenRedefinicaoSenha);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	@Override
	public TokenRedefinicaoSenha buscarPeloCodigo(Long codigo) throws ServiceDacException {
		try {
			return tokenRedefinicaoSenhaDAO.buscarPeloCodigo(codigo);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}

	@Override
	public List<TokenRedefinicaoSenha> listar() throws ServiceDacException {
		try {
			return tokenRedefinicaoSenhaDAO.listar();
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}
	
	@Override
	public TokenRedefinicaoSenha buscarPelaConta(Conta conta) throws ServiceDacException {
		try {
			return tokenRedefinicaoSenhaDAO.buscarPelaConta(conta);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}
	
	@Override
	public TokenRedefinicaoSenha buscarPeloToken(String token) throws ServiceDacException {
		try {
			return tokenRedefinicaoSenhaDAO.buscarPeloToken(token);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage());
		}
	}
	
	private String gerarToken(TokenRedefinicaoSenha tokenRedefinicaoSenha) {
		Long nanossegundos = System.nanoTime();
		String login = tokenRedefinicaoSenha.getConta().getLogin();
		
		return getStringCriptografada(login + nanossegundos).replace("+", "");
	}
	
	private void configurarTokenRedefinicaoSenha(TokenRedefinicaoSenha tokenRedefinicaoSenha) {
		Date dataVencimento = new Date();
		Integer diasParaVencimento = 5;
		
		dataVencimento = somar(dataVencimento, diasParaVencimento, DateUtil.DIA);
		
		String token = gerarToken(tokenRedefinicaoSenha);
		
		tokenRedefinicaoSenha.setToken(token);
		tokenRedefinicaoSenha.setDataVencimento(dataVencimento);
	}

}
