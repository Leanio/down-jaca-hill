package br.edu.ifpb.mt.ads.dac.converters;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.edu.ifpb.mt.ads.dac.model.Conta;
import br.edu.ifpb.mt.ads.dac.services.ContaService;
import br.edu.ifpb.mt.ads.dac.services.ServiceDacException;

@FacesConverter(forClass = Conta.class)
public class ContaConverter implements Converter<Conta> {

	@Inject
	private ContaService contaService;

	@Override
	public Conta getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.trim().isEmpty()) {
			return null;
		}

		try {
			Long codigo = Long.parseLong(value);
			return contaService.buscarPeloCodigo(codigo);
		} catch (ServiceDacException | NumberFormatException e) {
			String msgErroStr = String.format(
					"Erro de conversão! Não foi possível realizar a conversão da string '%s' para o tipo esperado.",
					value);
			FacesMessage msgErro = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErroStr, msgErroStr);
			throw new ConverterException(msgErro);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Conta value) {
		if (!(value instanceof Conta)) {
			return null;
		}
		
		Long id = ((Conta) value).getCodigo();
		return (id != null) ? id.toString() : null;
	}

}
