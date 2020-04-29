package br.edu.ifpb.mt.ads.dac.converters;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.edu.ifpb.mt.ads.dac.model.TokenRedefinicaoSenha;
import br.edu.ifpb.mt.ads.dac.services.TokenRedefinicaoSenhaService;
import br.edu.ifpb.mt.ads.dac.services.ServiceDacException;

@FacesConverter(forClass = TokenRedefinicaoSenha.class)
public class TokenRedefinicaoSenhaConverter implements Converter<TokenRedefinicaoSenha> {

	@Inject
	private TokenRedefinicaoSenhaService tokenRedefinicaoSenhaService;

	@Override
	public TokenRedefinicaoSenha getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.trim().isEmpty()) {
			return null;
		}

		try {
			Long codigo = Long.parseLong(value);
			return tokenRedefinicaoSenhaService.buscarPeloCodigo(codigo);
		} catch (ServiceDacException | NumberFormatException e) {
			String msgErroStr = String.format(
					"Erro de conversão! Não foi possível realizar a conversão da string '%s' para o tipo esperado.",
					value);
			FacesMessage msgErro = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErroStr, msgErroStr);
			throw new ConverterException(msgErro);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, TokenRedefinicaoSenha value) {
		if (!(value instanceof TokenRedefinicaoSenha)) {
			return null;
		}
		
		Long id = ((TokenRedefinicaoSenha) value).getCodigo();
		return (id != null) ? id.toString() : null;
	}

}
