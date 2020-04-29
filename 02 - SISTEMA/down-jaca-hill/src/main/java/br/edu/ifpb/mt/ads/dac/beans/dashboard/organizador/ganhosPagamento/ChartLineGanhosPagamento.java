package br.edu.ifpb.mt.ads.dac.beans.dashboard.organizador.ganhosPagamento;

import static br.edu.ifpb.mt.ads.dac.util.dashboard.Util.gerarLabels;
import static br.edu.ifpb.mt.ads.dac.util.date.DateUtil.subtrair;

import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.ads.dac.beans.dashboard.ChartLine;
import br.edu.ifpb.mt.ads.dac.filters.PagamentoFilter;
import br.edu.ifpb.mt.ads.dac.model.Organizador;
import br.edu.ifpb.mt.ads.dac.services.PagamentoService;
import br.edu.ifpb.mt.ads.dac.util.dashboard.ResultadoDashboard;
import br.edu.ifpb.mt.ads.dac.util.date.DateUtil;
import br.edu.ifpb.mt.ads.dac.util.qualifiers.OrganizadorAutenticado;

@Named
@RequestScoped
public class ChartLineGanhosPagamento extends ChartLine {
	
	private static final long serialVersionUID = 4742335429091207008L;

	@Inject
	private PagamentoService pagamentoServie;
	
	@Inject
	@OrganizadorAutenticado
	private Organizador organizador;
	
	private PagamentoFilter pagamentoFilter;
	
	private Integer meses = 12;
	
	public void init() {
		Date hoje = new Date();
		
		Date dataPagamentoDe = subtrair(hoje, meses-1, DateUtil.MES);
		Date dataPagamentoAte = hoje;
		
		pagamentoFilter.setDataPagamentoDe(dataPagamentoDe);;
		pagamentoFilter.setDataPagamentoAte(dataPagamentoAte);
		pagamentoFilter.setOrganizador(organizador);
		
		createLineModel();
	}
	
    public void createLineModel() {
        List<ResultadoDashboard> resultado = pagamentoServie.ganhosPagamentoMensal(pagamentoFilter);
        
        add(resultado, "Reais", "rgb(245, 176, 65)");
   
        List<String> labels = gerarLabels(resultado);
        
        data.setLabels(labels);
    }
    
    public void criarFiltro() {
    	pagamentoFilter = new PagamentoFilter();
    }

	public Integer getMeses() {
		return meses;
	}

	public void setMeses(Integer meses) {
		this.meses = meses;
	}

	public PagamentoFilter getPagamentoFilter() {
		return pagamentoFilter;
	}

	public void setPagamentoFilter(PagamentoFilter pagamentoFilter) {
		this.pagamentoFilter = pagamentoFilter;
	}

}
