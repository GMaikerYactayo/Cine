/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.GraficoCineImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import modelo.GraficoCine;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author PC1
 */
@Named(value = "graficoCineC")
@SessionScoped
public class GraficoCineC implements Serializable {
private BarChartModel barModel;
    private List<GraficoCine> LgraficoCine;
    private GraficoCineImpl graficodao;

    public GraficoCineC() {
        graficodao = new GraficoCineImpl();
    }

    @PostConstruct
    public void init() {
        try {
            createBarModel();
        } catch (Exception e) {
        }

    }

    private BarChartModel initBarModel() throws Exception {
        BarChartModel model = new BarChartModel();

        LgraficoCine = graficodao.listar();

        ChartSeries cantidad = new ChartSeries();
        cantidad.setLabel("Prestamos");

        for (GraficoCine list : LgraficoCine) {
            cantidad.set(list.getMES(), Integer.parseInt(list.getCANTIDAD()));
        }

        model.addSeries(cantidad);
        return model;
    }

    private void createBarModel() throws Exception {
        barModel = initBarModel();

        barModel.setTitle("Cines");
        barModel.setLegendPosition("ne");
        barModel.setAnimate(true);

        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Meses");

        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Cantidad");
        yAxis.setMin(0);
        yAxis.setMax(160);
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }

    public List<GraficoCine> getLgraficoCine() {
        return LgraficoCine;
    }

    public void setLgraficoCine(List<GraficoCine> LgraficoCine) {
        this.LgraficoCine = LgraficoCine;
    }

    public GraficoCineImpl getGraficodao() {
        return graficodao;
    }

    public void setGraficodao(GraficoCineImpl graficodao) {
        this.graficodao = graficodao;
    }
}
