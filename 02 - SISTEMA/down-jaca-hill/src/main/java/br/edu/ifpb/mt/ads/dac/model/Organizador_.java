package br.edu.ifpb.mt.ads.dac.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Organizador.class)
public abstract class Organizador_ extends br.edu.ifpb.mt.ads.dac.model.EntidadePersistente_ {

	public static volatile SingularAttribute<Organizador, Pessoa> pessoa;
	public static volatile SingularAttribute<Organizador, String> rg;
	public static volatile SingularAttribute<Organizador, Date> dataCadastroPerfilOrganizador;

	public static final String PESSOA = "pessoa";
	public static final String RG = "rg";
	public static final String DATA_CADASTRO_PERFIL_ORGANIZADOR = "dataCadastroPerfilOrganizador";

}

