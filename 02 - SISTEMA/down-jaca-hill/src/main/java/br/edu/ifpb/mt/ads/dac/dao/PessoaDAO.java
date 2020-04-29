package br.edu.ifpb.mt.ads.dac.dao;

import br.edu.ifpb.mt.ads.dac.model.Pessoa;

public interface PessoaDAO extends DAO<Pessoa, Long> {
	
	public boolean existeOutraPessoaComMesmoCpf(Pessoa pessoa);

}
