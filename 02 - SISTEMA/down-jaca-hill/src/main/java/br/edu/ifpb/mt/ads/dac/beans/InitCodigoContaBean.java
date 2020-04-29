package br.edu.ifpb.mt.ads.dac.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.Eager;

import br.edu.ifpb.mt.ads.dac.util.qualifiers.ContaAutenticada;
import br.edu.ifpb.mt.ads.dac.util.qualifiers.impl.WrapperLong;

@Named
@RequestScoped
@Eager
public class InitCodigoContaBean {
	
	@Inject
	@ContaAutenticada
	private WrapperLong codigoContaAutenticada;
	
}
