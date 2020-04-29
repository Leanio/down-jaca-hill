package br.edu.ifpb.mt.ads.dac.dao.impl;

import br.edu.ifpb.mt.ads.dac.dao.PremiacaoDAO;
import br.edu.ifpb.mt.ads.dac.model.Premiacao;

public class PremiacaoDAOImpl extends GenericoDAOImpl<Premiacao, Long> implements PremiacaoDAO {

	public PremiacaoDAOImpl() {
		super(Premiacao.class);
	}

}
