package br.edu.ifpb.mt.ads.dac.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Conta.class)
public abstract class Conta_ extends br.edu.ifpb.mt.ads.dac.model.EntidadePersistente_ {

	public static volatile SingularAttribute<Conta, String> senha;
	public static volatile SingularAttribute<Conta, TokenRedefinicaoSenha> tokenRedefinicaoSenha;
	public static volatile SingularAttribute<Conta, Pessoa> pessoa;
	public static volatile ListAttribute<Conta, Grupo> grupo;
	public static volatile SingularAttribute<Conta, String> login;
	public static volatile SingularAttribute<Conta, Boolean> ativa;
	public static volatile SingularAttribute<Conta, String> email;
	public static volatile SingularAttribute<Conta, Date> dataCadastroContaAcesso;

	public static final String SENHA = "senha";
	public static final String TOKEN_REDEFINICAO_SENHA = "tokenRedefinicaoSenha";
	public static final String PESSOA = "pessoa";
	public static final String GRUPO = "grupo";
	public static final String LOGIN = "login";
	public static final String ATIVA = "ativa";
	public static final String EMAIL = "email";
	public static final String DATA_CADASTRO_CONTA_ACESSO = "dataCadastroContaAcesso";

}

