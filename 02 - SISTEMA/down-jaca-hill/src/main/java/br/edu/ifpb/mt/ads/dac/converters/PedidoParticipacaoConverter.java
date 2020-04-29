package br.edu.ifpb.mt.ads.dac.converters;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.edu.ifpb.mt.ads.dac.model.PedidoParticipacao;
import br.edu.ifpb.mt.ads.dac.services.PedidoParticipacaoService;
import br.edu.ifpb.mt.ads.dac.services.ServiceDacException;

@FacesConverter(forClass = PedidoParticipacao.class)
public class PedidoParticipacaoConverter implements Converter<PedidoParticipacao> {

	@Inject
	private PedidoParticipacaoService pedidoParticipacaoService;

	@Override
	public PedidoParticipacao getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.trim().isEmpty()) {
			return null;
		}

		try {
			Long codigo = Long.parseLong(value);
			return pedidoParticipacaoService.buscarPeloCodigo(codigo);
		} catch (ServiceDacException | NumberFormatException e) {
			String msgErroStr = String.format(
					"Erro de conversão! Não foi possível realizar a conversão da string '%s' para o tipo esperado.",
					value);
			FacesMessage msgErro = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErroStr, msgErroStr);
			throw new ConverterException(msgErro);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, PedidoParticipacao value) {
		if (!(value instanceof PedidoParticipacao)) {
			return null;
		}
		
		Long id = ((PedidoParticipacao) value).getCodigo();
		return (id != null) ? id.toString() : null;
	}

}
