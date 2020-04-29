package br.edu.ifpb.mt.ads.dac.beans;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

public abstract class AbstractBean implements Serializable {
	
	private static final long serialVersionUID = 7887186144461468149L;

	public static void reportarMensagemDeErro(String detalhe) {
		reportarMensagem(true, detalhe, false);
	}

	public static void reportarMensagemDeSucesso(String detalhe) {
		reportarMensagem(false, detalhe, true);
	}

	private static void reportarMensagem(boolean isErro, String detalhe, boolean keepMessages) {
		String sumario = "Success!";
		Severity severity = FacesMessage.SEVERITY_INFO;
		if (isErro) {
			sumario = "Error!";
			severity = FacesMessage.SEVERITY_ERROR;
			FacesContext.getCurrentInstance().validationFailed();
		}

		FacesMessage msg = new FacesMessage(severity, sumario, detalhe);

		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.setKeepMessages(keepMessages);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public boolean isContaInRoles(String... roles) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();

		for (String role : roles) {
			if (externalContext.isUserInRole(role)) {
				return true;
			}
		}

		return false;
	}
	
	public boolean isContaInRole(String role) {
		return isContaInRoles(role);
	}

}
