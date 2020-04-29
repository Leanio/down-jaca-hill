package br.edu.ifpb.mt.ads.dac.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Modelo.class)
public abstract class Modelo_ extends br.edu.ifpb.mt.ads.dac.model.EntidadePersistente_ {

	public static volatile SingularAttribute<Modelo, Marca> marca;
	public static volatile SingularAttribute<Modelo, Date> dataCadastroModelo;
	public static volatile SingularAttribute<Modelo, String> nome;

	public static final String MARCA = "marca";
	public static final String DATA_CADASTRO_MODELO = "dataCadastroModelo";
	public static final String NOME = "nome";

}

