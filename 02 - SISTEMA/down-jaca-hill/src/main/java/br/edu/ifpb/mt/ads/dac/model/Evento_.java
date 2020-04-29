package br.edu.ifpb.mt.ads.dac.model;

import br.edu.ifpb.mt.ads.dac.model.enumerations.EstadoEvento;
import br.edu.ifpb.mt.ads.dac.model.enumerations.TipoEvento;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Evento.class)
public abstract class Evento_ extends br.edu.ifpb.mt.ads.dac.model.EntidadePersistente_ {

	public static volatile ListAttribute<Evento, ItemRecurso> itemRecurso;
	public static volatile SingularAttribute<Evento, Date> dataCadastroEvento;
	public static volatile SingularAttribute<Evento, EstadoEvento> estadoEvento;
	public static volatile SingularAttribute<Evento, String> titulo;
	public static volatile SingularAttribute<Evento, Endereco> enderecoEvento;
	public static volatile ListAttribute<Evento, Premiacao> premiacao;
	public static volatile SingularAttribute<Evento, String> descricao;
	public static volatile SingularAttribute<Evento, Date> dataEvento;
	public static volatile SingularAttribute<Evento, Integer> distanciaEmMetros;
	public static volatile SingularAttribute<Evento, Endereco> enderecoPagamento;
	public static volatile SingularAttribute<Evento, TipoEvento> tipoEvento;
	public static volatile SingularAttribute<Evento, Float> taxaParticipacao;
	public static volatile SingularAttribute<Evento, Date> pedidoParticipacaoDe;
	public static volatile SingularAttribute<Evento, Integer> quantidadeVagas;
	public static volatile SingularAttribute<Evento, Date> pedidoParticipacaoAte;
	public static volatile SingularAttribute<Evento, Organizador> organizador;

	public static final String ITEM_RECURSO = "itemRecurso";
	public static final String DATA_CADASTRO_EVENTO = "dataCadastroEvento";
	public static final String ESTADO_EVENTO = "estadoEvento";
	public static final String TITULO = "titulo";
	public static final String ENDERECO_EVENTO = "enderecoEvento";
	public static final String PREMIACAO = "premiacao";
	public static final String DESCRICAO = "descricao";
	public static final String DATA_EVENTO = "dataEvento";
	public static final String DISTANCIA_EM_METROS = "distanciaEmMetros";
	public static final String ENDERECO_PAGAMENTO = "enderecoPagamento";
	public static final String TIPO_EVENTO = "tipoEvento";
	public static final String TAXA_PARTICIPACAO = "taxaParticipacao";
	public static final String PEDIDO_PARTICIPACAO_DE = "pedidoParticipacaoDe";
	public static final String QUANTIDADE_VAGAS = "quantidadeVagas";
	public static final String PEDIDO_PARTICIPACAO_ATE = "pedidoParticipacaoAte";
	public static final String ORGANIZADOR = "organizador";

}

