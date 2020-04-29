package br.edu.ifpb.mt.ads.dac.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TokenRedefinicaoSenha.class)
public abstract class TokenRedefinicaoSenha_ extends br.edu.ifpb.mt.ads.dac.model.EntidadePersistente_ {

	public static volatile SingularAttribute<TokenRedefinicaoSenha, Date> dataVencimento;
	public static volatile SingularAttribute<TokenRedefinicaoSenha, Conta> conta;
	public static volatile SingularAttribute<TokenRedefinicaoSenha, String> token;

	public static final String DATA_VENCIMENTO = "dataVencimento";
	public static final String CONTA = "conta";
	public static final String TOKEN = "token";

}

