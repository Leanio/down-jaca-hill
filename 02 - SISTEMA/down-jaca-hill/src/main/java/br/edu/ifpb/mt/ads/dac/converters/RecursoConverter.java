package br.edu.ifpb.mt.ads.dac.converters;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.edu.ifpb.mt.ads.dac.model.Recurso;
import br.edu.ifpb.mt.ads.dac.services.RecursoService;
import br.edu.ifpb.mt.ads.dac.services.ServiceDacException;

@FacesConverter(forClass = Recurso.class)
public class RecursoConverter implements Converter<Recurso> {

	@Inject
	private RecursoService recursoService;

	@Override
	public Recurso getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.trim().isEmpty()) {
			return null;
		}

		try {
			Long codigo = Long.parseLong(value);
			return recursoService.buscarPeloCodigo(codigo);
		} catch (ServiceDacException | NumberFormatException e) {
			String msgErroStr = String.format(
					"Erro de conversão! Não foi possível realizar a conversão da string '%s' para o tipo esperado.",
					value);
			FacesMessage msgErro = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErroStr, msgErroStr);
			throw new ConverterException(msgErro);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Recurso value) {
		if (!(value instanceof Recurso)) {
			return null;
		}
		
		Long id = ((Recurso) value).getCodigo();
		return (id != null) ? id.toString() : null;
	}

}
