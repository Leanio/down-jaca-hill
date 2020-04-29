package br.edu.ifpb.mt.ads.dac.model;

import br.edu.ifpb.mt.ads.dac.model.enumerations.MeioPagamento;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Pagamento.class)
public abstract class Pagamento_ extends br.edu.ifpb.mt.ads.dac.model.EntidadePersistente_ {

	public static volatile SingularAttribute<Pagamento, Date> dataPagamento;
	public static volatile SingularAttribute<Pagamento, PedidoParticipacao> pedidoParticipacao;
	public static volatile SingularAttribute<Pagamento, MeioPagamento> meioPagamento;
	public static volatile SingularAttribute<Pagamento, Integer> quantidadeParcelas;

	public static final String DATA_PAGAMENTO = "dataPagamento";
	public static final String PEDIDO_PARTICIPACAO = "pedidoParticipacao";
	public static final String MEIO_PAGAMENTO = "meioPagamento";
	public static final String QUANTIDADE_PARCELAS = "quantidadeParcelas";

}

