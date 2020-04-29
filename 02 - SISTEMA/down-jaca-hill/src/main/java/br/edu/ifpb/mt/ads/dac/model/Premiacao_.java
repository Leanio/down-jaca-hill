package br.edu.ifpb.mt.ads.dac.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Premiacao.class)
public abstract class Premiacao_ extends br.edu.ifpb.mt.ads.dac.model.EntidadePersistente_ {

	public static volatile SingularAttribute<Premiacao, Integer> colocacao;
	public static volatile SingularAttribute<Premiacao, Evento> evento;
	public static volatile SingularAttribute<Premiacao, Float> valor;
	public static volatile SingularAttribute<Premiacao, Participacao> participacao;

	public static final String COLOCACAO = "colocacao";
	public static final String EVENTO = "evento";
	public static final String VALOR = "valor";
	public static final String PARTICIPACAO = "participacao";

}

