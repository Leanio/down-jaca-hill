package br.edu.ifpb.mt.ads.dac.dao.impl;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.edu.ifpb.mt.ads.dac.dao.DAO;
import br.edu.ifpb.mt.ads.dac.dao.PersistenciaDacException;
import br.edu.ifpb.mt.ads.dac.filters.Filter;
import br.edu.ifpb.mt.ads.dac.model.EntidadePersistente;

public abstract class GenericoDAOImpl<T extends EntidadePersistente, K> implements DAO<T, K> {

	@Inject
	protected EntityManager entityManager;

	protected Class<T> classeEntidade;

	public GenericoDAOImpl(Class<T> classeEntidade) {
		this.classeEntidade = classeEntidade;
	}

	@Override
	public void salvar(T entidade) throws PersistenciaDacException {
		try {
			entityManager.persist(entidade);
		} catch (PersistenceException p) {
			throw new PersistenciaDacException("Não foi possível realizar a operação de salvar");
		}
	}

	@Override
	public void remover(K codigo) throws PersistenciaDacException {
		try {
			T entidade = buscarPeloCodigo(codigo);
			entityManager.remove(entidade);
		} catch (PersistenceException p) {
			throw new PersistenciaDacException("Não foi possível realizar a operação de remover");
		}
	}

	@Override
	public T atualizar(T entidade) throws PersistenciaDacException {
		try {
			entidade = entityManager.merge(entidade);
			return entidade;
		} catch (PersistenceException p) {
			throw new PersistenciaDacException("Não foi possível realizar a operação de atualizar");
		}
	}

	@Override
	public T buscarPeloCodigo(K codigo) throws PersistenciaDacException {
		try {
			return entityManager.find(classeEntidade, codigo);
		} catch (PersistenceException p) {
			throw new PersistenciaDacException("Não foi possível encontrar o objeto");
		}
	}

	@Override
	public List<T> listar() throws PersistenciaDacException {
		try {
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(classeEntidade);
			Root<T> root = criteriaQuery.from(classeEntidade);

			criteriaQuery.select(root);

			TypedQuery<T> typedQuery = entityManager.createQuery(criteriaQuery);

			List<T> resultado = typedQuery.getResultList();

			return resultado;
		} catch (PersistenceException p) {
			throw new PersistenciaDacException(p.getMessage());
		}
	}

	protected void adicionarPaginacao(TypedQuery<T> typedQuery, Filter filter) {
		Integer posicaoInicialResultado = filter.getProximoItem();
		Integer quantidadeResultadosPorPagina = filter.getQuantidadeResultadosPorPagina();

		if (posicaoInicialResultado != null) {
			typedQuery.setFirstResult(posicaoInicialResultado);
		}

		if (quantidadeResultadosPorPagina != null) {
			typedQuery.setMaxResults(quantidadeResultadosPorPagina);
		}
	}

}
