package br.edu.ifpb.mt.ads.dac.services.dataGenerator;

import java.io.Serializable;

import br.edu.ifpb.mt.ads.dac.services.ServiceDacException;

public interface DataGeneratorService extends Serializable {

	void generateData() throws ServiceDacException;
	
}
