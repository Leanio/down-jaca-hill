package br.edu.ifpb.mt.ads.dac.dao;

import java.util.List;

public interface DAO<T, K> {

	public void salvar(T entidade) throws PersistenciaDacException;
	
	public void remover(K codigo) throws PersistenciaDacException;
	
	public T atualizar(T entidade) throws PersistenciaDacException;
	
	public T buscarPeloCodigo(K codigo) throws PersistenciaDacException;
	
	public List<T> listar() throws PersistenciaDacException;
	
}
