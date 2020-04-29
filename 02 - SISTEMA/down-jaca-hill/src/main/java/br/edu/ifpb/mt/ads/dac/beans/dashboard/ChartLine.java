package br.edu.ifpb.mt.ads.dac.beans.dashboard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.line.LineChartDataSet;
import org.primefaces.model.charts.line.LineChartModel;

import br.edu.ifpb.mt.ads.dac.util.dashboard.ResultadoDashboard;

public abstract class ChartLine implements Serializable {
	
	private static final long serialVersionUID = 2320685561057416976L;
	
	protected LineChartModel lineModel;
	
	protected ChartData data;
	
	@PostConstruct
	public void postConstruct() {
		lineModel = new LineChartModel();
		data = new ChartData();
		lineModel.setData(data);

		criarFiltro();
	}

    protected void add(List<ResultadoDashboard> resultado, String label, String cor) {
        LineChartDataSet dataSet = new LineChartDataSet();
        
        List<Number> values = new ArrayList<>();
        for (ResultadoDashboard rs : resultado) {
        	values.add(rs.getTotal());
        }
        
        dataSet.setData(values);
        
        dataSet.setFill(false);
        dataSet.setLabel(label);
        dataSet.setBorderColor(cor);
        dataSet.setLineTension(0.1);
        
        data.addChartDataSet(dataSet);
    }
    
    public abstract void createLineModel();
    
    public abstract void criarFiltro();

	public LineChartModel getLineModel() {
		return lineModel;
	}

	public void setLineModel(LineChartModel lineModel) {
		this.lineModel = lineModel;
	}
	
}
