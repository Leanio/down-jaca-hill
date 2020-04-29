package br.edu.ifpb.mt.ads.dac.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ItemRecurso.class)
public abstract class ItemRecurso_ extends br.edu.ifpb.mt.ads.dac.model.EntidadePersistente_ {

	public static volatile SingularAttribute<ItemRecurso, Recurso> recurso;
	public static volatile SingularAttribute<ItemRecurso, String> observacao;
	public static volatile SingularAttribute<ItemRecurso, Evento> evento;
	public static volatile SingularAttribute<ItemRecurso, Float> gastoUnitario;
	public static volatile SingularAttribute<ItemRecurso, Integer> quantidade;

	public static final String RECURSO = "recurso";
	public static final String OBSERVACAO = "observacao";
	public static final String EVENTO = "evento";
	public static final String GASTO_UNITARIO = "gastoUnitario";
	public static final String QUANTIDADE = "quantidade";

}

