package br.edu.ifpb.mt.ads.dac.dao.impl;


import br.edu.ifpb.mt.ads.dac.dao.ItemRecursoDAO;
import br.edu.ifpb.mt.ads.dac.model.ItemRecurso;

public class ItemRecursoDAOImpl extends GenericoDAOImpl<ItemRecurso, Long> implements ItemRecursoDAO {

	public ItemRecursoDAOImpl() {
		super(ItemRecurso.class);
	}

}
