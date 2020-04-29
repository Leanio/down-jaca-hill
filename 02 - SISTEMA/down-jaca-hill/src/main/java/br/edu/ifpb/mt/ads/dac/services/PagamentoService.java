package br.edu.ifpb.mt.ads.dac.services;

import java.util.List;

import br.edu.ifpb.mt.ads.dac.filters.PagamentoFilter;
import br.edu.ifpb.mt.ads.dac.model.Pagamento;
import br.edu.ifpb.mt.ads.dac.util.dashboard.ResultadoDashboard;

public interface PagamentoService extends Service<Pagamento, Long> {

	public List<ResultadoDashboard> ganhosPagamentoMensal(PagamentoFilter pagamentoFilter);

}
