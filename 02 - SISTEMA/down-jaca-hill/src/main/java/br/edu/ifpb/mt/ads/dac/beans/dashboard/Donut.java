package br.edu.ifpb.mt.ads.dac.beans.dashboard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.donut.DonutChartDataSet;
import org.primefaces.model.charts.donut.DonutChartModel;

import br.edu.ifpb.mt.ads.dac.util.dashboard.ResultadoDashboard;

public abstract class Donut implements Serializable {

	private static final long serialVersionUID = -5461667206308257231L;
	
	protected DonutChartModel donutModel;
	
	protected ChartData data;

	@PostConstruct
	public void postConstruct() {
		donutModel = new DonutChartModel();
		data = new ChartData();
		donutModel.setData(data);
		
		criarFiltro();
	}
	
	public abstract void createDonutModel();
	
	public abstract void criarFiltro();
	
	protected void add(List<ResultadoDashboard> resultado, String... cores) {
		DonutChartDataSet dataSet = new DonutChartDataSet();
		data.addChartDataSet(dataSet);
		
		List<Number> values = new ArrayList<>();
		List<String> labels = new ArrayList<>();
		
		for (ResultadoDashboard rs : resultado) {
			values.add(rs.getTotal());
			labels.add(rs.getGrupo());
		}
		
        List<String> bgColors = new ArrayList<>();
        
        for (String cor : cores) {
        	bgColors.add(cor);
		}
        
        dataSet.setBackgroundColor(bgColors);
		
		dataSet.setData(values);
		data.setLabels(labels);
	}

	public DonutChartModel getDonutModel() {
		return donutModel;
	}

	public void setDonutModel(DonutChartModel donutModel) {
		this.donutModel = donutModel;
	}

}
