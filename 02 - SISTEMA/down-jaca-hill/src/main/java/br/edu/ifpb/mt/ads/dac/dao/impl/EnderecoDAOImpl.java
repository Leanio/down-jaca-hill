package br.edu.ifpb.mt.ads.dac.dao.impl;

import br.edu.ifpb.mt.ads.dac.dao.EnderecoDAO;
import br.edu.ifpb.mt.ads.dac.model.Endereco;

public class EnderecoDAOImpl extends GenericoDAOImpl<Endereco, Long> implements EnderecoDAO {

	public EnderecoDAOImpl() {
		super(Endereco.class);
	}

}
