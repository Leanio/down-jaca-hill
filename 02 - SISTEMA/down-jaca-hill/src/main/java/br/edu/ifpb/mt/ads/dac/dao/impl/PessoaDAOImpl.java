package br.edu.ifpb.mt.ads.dac.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.edu.ifpb.mt.ads.dac.dao.PessoaDAO;
import br.edu.ifpb.mt.ads.dac.model.Pessoa;
import br.edu.ifpb.mt.ads.dac.model.Pessoa_;

public class PessoaDAOImpl extends GenericoDAOImpl<Pessoa, Long> implements PessoaDAO {

	public PessoaDAOImpl() {
		super(Pessoa.class);
	}

	@Override
	public boolean existeOutraPessoaComMesmoCpf(Pessoa pessoa) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Pessoa> root = criteriaQuery.from(Pessoa.class);

		Expression<Long> expression = criteriaBuilder.count(root);
		
		Predicate[] predicate = getExisteOutraPessoaComMesmoCpf(criteriaBuilder, root, pessoa);
		
		criteriaQuery.select(expression);
		criteriaQuery.where(predicate);
		
		TypedQuery<Long> typedQuery = entityManager.createQuery(criteriaQuery);
		
		Long resultado = typedQuery.getSingleResult();
		
		return resultado > 0;
	}
	
	private Predicate[] getExisteOutraPessoaComMesmoCpf(CriteriaBuilder criteriaBuilder, Root<Pessoa> root, Pessoa pessoa) {
		List<Predicate> predicate = new ArrayList<Predicate>();
		
		if (pessoa.getCodigo() != null) {
			predicate.add(criteriaBuilder.notEqual(root, pessoa));
		}
		
		if (pessoa.getCpf() != null) {
			predicate.add(criteriaBuilder.equal(criteriaBuilder.lower(root.<String>get(Pessoa_.cpf)), pessoa.getCpf().toLowerCase()));
		}

		return predicate.toArray(new Predicate[0]);
	}

}
