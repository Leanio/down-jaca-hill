package br.edu.ifpb.mt.ads.dac.model;

import br.edu.ifpb.mt.ads.dac.model.enumerations.EstadoPedido;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PedidoParticipacao.class)
public abstract class PedidoParticipacao_ extends br.edu.ifpb.mt.ads.dac.model.EntidadePersistente_ {

	public static volatile SingularAttribute<PedidoParticipacao, EstadoPedido> estadoPedidoParticipacao;
	public static volatile SingularAttribute<PedidoParticipacao, Evento> evento;
	public static volatile SingularAttribute<PedidoParticipacao, Ciclista> ciclista;
	public static volatile SingularAttribute<PedidoParticipacao, Float> taxaParticipacao;
	public static volatile SingularAttribute<PedidoParticipacao, Date> dataAtualizacaoEstado;
	public static volatile SingularAttribute<PedidoParticipacao, Participacao> participacao;
	public static volatile SingularAttribute<PedidoParticipacao, Pagamento> pagamento;
	public static volatile SingularAttribute<PedidoParticipacao, Modelo> modelo;
	public static volatile SingularAttribute<PedidoParticipacao, Date> dataCadastroPedidoParticipacao;

	public static final String ESTADO_PEDIDO_PARTICIPACAO = "estadoPedidoParticipacao";
	public static final String EVENTO = "evento";
	public static final String CICLISTA = "ciclista";
	public static final String TAXA_PARTICIPACAO = "taxaParticipacao";
	public static final String DATA_ATUALIZACAO_ESTADO = "dataAtualizacaoEstado";
	public static final String PARTICIPACAO = "participacao";
	public static final String PAGAMENTO = "pagamento";
	public static final String MODELO = "modelo";
	public static final String DATA_CADASTRO_PEDIDO_PARTICIPACAO = "dataCadastroPedidoParticipacao";

}

