package br.edu.ifpb.mt.ads.dac.model;

import br.edu.ifpb.mt.ads.dac.model.enumerations.Sexo;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Pessoa.class)
public abstract class Pessoa_ extends br.edu.ifpb.mt.ads.dac.model.EntidadePersistente_ {

	public static volatile SingularAttribute<Pessoa, Ciclista> ciclista;
	public static volatile SingularAttribute<Pessoa, Endereco> endereco;
	public static volatile SingularAttribute<Pessoa, String> cpf;
	public static volatile SingularAttribute<Pessoa, Conta> conta;
	public static volatile SingularAttribute<Pessoa, String> nome;
	public static volatile SingularAttribute<Pessoa, Sexo> sexo;
	public static volatile SingularAttribute<Pessoa, Date> dataNascimento;
	public static volatile SingularAttribute<Pessoa, Organizador> organizador;

	public static final String CICLISTA = "ciclista";
	public static final String ENDERECO = "endereco";
	public static final String CPF = "cpf";
	public static final String CONTA = "conta";
	public static final String NOME = "nome";
	public static final String SEXO = "sexo";
	public static final String DATA_NASCIMENTO = "dataNascimento";
	public static final String ORGANIZADOR = "organizador";

}

