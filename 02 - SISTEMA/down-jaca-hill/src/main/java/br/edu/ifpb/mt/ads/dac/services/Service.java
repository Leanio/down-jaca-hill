package br.edu.ifpb.mt.ads.dac.services;

import java.io.Serializable;
import java.util.List;

public interface Service<T, K> extends Serializable {

	public void salvar(T entidade) throws ServiceDacException;

	public void remover(K codigo) throws ServiceDacException;

	public T atualizar(T entidade) throws ServiceDacException;

	public T buscarPeloCodigo(K codigo) throws ServiceDacException;

	public List<T> listar() throws ServiceDacException;

}
