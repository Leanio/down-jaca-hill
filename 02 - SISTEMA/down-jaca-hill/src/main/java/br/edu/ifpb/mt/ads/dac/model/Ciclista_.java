package br.edu.ifpb.mt.ads.dac.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Ciclista.class)
public abstract class Ciclista_ extends br.edu.ifpb.mt.ads.dac.model.EntidadePersistente_ {

	public static volatile SingularAttribute<Ciclista, Boolean> isProfissional;
	public static volatile SingularAttribute<Ciclista, Pessoa> pessoa;
	public static volatile SingularAttribute<Ciclista, Date> dataCadastroPerfilCiclista;

	public static final String IS_PROFISSIONAL = "isProfissional";
	public static final String PESSOA = "pessoa";
	public static final String DATA_CADASTRO_PERFIL_CICLISTA = "dataCadastroPerfilCiclista";

}

