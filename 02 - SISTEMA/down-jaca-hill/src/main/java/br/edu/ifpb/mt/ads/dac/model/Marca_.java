package br.edu.ifpb.mt.ads.dac.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Marca.class)
public abstract class Marca_ extends br.edu.ifpb.mt.ads.dac.model.EntidadePersistente_ {

	public static volatile SingularAttribute<Marca, Date> dataCadastroMarca;
	public static volatile SingularAttribute<Marca, String> nome;

	public static final String DATA_CADASTRO_MARCA = "dataCadastroMarca";
	public static final String NOME = "nome";

}

