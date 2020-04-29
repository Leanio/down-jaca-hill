package br.edu.ifpb.mt.ads.dac.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Participacao.class)
public abstract class Participacao_ extends br.edu.ifpb.mt.ads.dac.model.EntidadePersistente_ {

	public static volatile SingularAttribute<Participacao, PedidoParticipacao> pedidoParticipacao;
	public static volatile SingularAttribute<Participacao, Date> tempoGasto;
	public static volatile SingularAttribute<Participacao, Premiacao> premiacao;

	public static final String PEDIDO_PARTICIPACAO = "pedidoParticipacao";
	public static final String TEMPO_GASTO = "tempoGasto";
	public static final String PREMIACAO = "premiacao";

}

