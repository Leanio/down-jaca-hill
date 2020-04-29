package br.edu.ifpb.mt.ads.dac.beans.dataGenerator;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.Eager;

import br.edu.ifpb.mt.ads.dac.services.ServiceDacException;
import br.edu.ifpb.mt.ads.dac.services.dataGenerator.ContasDataGeneratorService;
import br.edu.ifpb.mt.ads.dac.services.dataGenerator.GruposDataGeneratorService;

@Named
@Eager
@ApplicationScoped
public class InitDatabaseIfNeeded implements Serializable {
	
	private static final long serialVersionUID = -6555909999054479386L;

	@Inject
	private GruposDataGeneratorService gruposDataGeneratorService;
	
	@Inject
	private ContasDataGeneratorService contasDataGeneratorService;
	
	@PostConstruct
	public void postConstruct() {
		try {
			gruposDataGeneratorService.generateData();
			contasDataGeneratorService.generateData();
		} catch (ServiceDacException e) {
			throw new RuntimeException(e);
		}
	}
	
}
