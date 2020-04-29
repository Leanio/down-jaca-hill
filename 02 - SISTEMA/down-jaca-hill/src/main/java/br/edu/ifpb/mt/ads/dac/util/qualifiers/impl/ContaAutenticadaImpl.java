package br.edu.ifpb.mt.ads.dac.util.qualifiers.impl;

import java.io.Serializable;
import java.security.Principal;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.ads.dac.beans.AbstractBean;
import br.edu.ifpb.mt.ads.dac.model.Conta;
import br.edu.ifpb.mt.ads.dac.services.ContaService;
import br.edu.ifpb.mt.ads.dac.services.ServiceDacException;
import br.edu.ifpb.mt.ads.dac.util.qualifiers.ContaAutenticada;

@ApplicationScoped
public class ContaAutenticadaImpl implements Serializable {

	private static final long serialVersionUID = 4761064556054093078L;

	@Inject
	private ContaService contaService;

	@Named
	@Produces
	@SessionScoped
	@ContaAutenticada
	public WrapperLong getCodigoContaAutenticada() {
		WrapperLong codigoConta = null;

		try {
			String login = getUserLogin();

			if (login != null) {
				codigoConta = new WrapperLong(contaService.buscarPeloLogin(login).getCodigo());
			}

		} catch (ServiceDacException e) {
			AbstractBean.reportarMensagemDeErro(e.getMessage());
		}

		return codigoConta;

	}

	@Named
	@Produces
	@ContaAutenticada
	public Conta getContaAutenticada(@ContaAutenticada WrapperLong wrapperLong) {
		Conta conta = null;

		try {
			String login = getUserLogin();

			if (login != null) {
				Long codigoContaAutenticada = wrapperLong.getCodigo();
				conta = contaService.buscarPeloCodigo(codigoContaAutenticada);
			}

		} catch (ServiceDacException e) {
			AbstractBean.reportarMensagemDeErro(e.getMessage());
		}

		return conta;
	}

	public String getUserLogin() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		Principal userPrincipal = externalContext.getUserPrincipal();

		if (userPrincipal == null) {
			return null;
		}

		return userPrincipal.getName();
	}

}
