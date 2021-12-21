/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author HEREDIA_Jader e Iury_Lucas
 */

import java.util.ArrayList;
//import java.util.Random;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.renderer.xy.XYStepRenderer;
import org.jfree.chart.title.TextTitle;

import javax.swing.JFrame;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;

public class Grafico extends JFrame {
    private final ArrayList <Integer> nivel;
    public ChartPanel chartPanel;
    
    //obtem os níveis referentes à msg enviada/recebida
    public Grafico(ArrayList<Integer> nivel) {
        this.nivel = nivel;
        initUI();
        //System.out.println(nivel);
    }
    //Constroi o grafico
    private void initUI() {
        XYDataset dataset = createDataset(); //cria o dataset 
        JFreeChart chart = createChart(dataset); //cria o gráfico 
        
        //manipula configurações da janela onde será plotado o gráfico
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBackground(Color.white);
        add(chartPanel);

        pack();
        setTitle("Line_Code");
        setSize(800,400);
        setLocationRelativeTo(null);
    }

    private XYDataset createDataset() { //constroi e retorna um dataset

        XYSeries series = new XYSeries("Código");
        XYSeriesCollection dataset = new XYSeriesCollection();
        
        for(int i=0; i < nivel.size();i++){
            series.add(i*2, nivel.get(i));
            if(i == nivel.size()-1){  //adiciona mais uma posição no array de 
               series.add((i+1)*2, nivel.get(i)); //nível para completar o últmo passo
            }
        }

        dataset.addSeries(series);
        return dataset;
    }

    public JFreeChart createChart(XYDataset dataset) { //constroi e retorna o gráfico

        JFreeChart chart = ChartFactory.createXYLineChart( //cria gráfico linha para obter dados eixo X (passos)
                "Codificação em Linha 2B1Q",
                "Tempo",
                "Nível",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        
        XYPlot plot = chart.getXYPlot();
        XYStepRenderer renderer = new XYStepRenderer(); //cria grafico Step
        
        renderer.setSeriesPaint(0, Color.red);
        renderer.setDefaultShapesVisible(true);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
        
        plot.setRenderer(renderer);     
        plot.setBackgroundPaint(Color.white);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);
        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);
        
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        
        chart.getLegend().setFrame(BlockBorder.NONE);
        chart.setTitle(new TextTitle("Codificação em Linha 2B1Q",
                        new Font("Serif", java.awt.Font.BOLD, 18)));
        return chart;
    }
    
    public ChartPanel getChartPanel(){
        return chartPanel;
    }

    /*public static void main(String[] args) {
        int nv[] = {-3,-1,1,3};
        ArrayList<Integer> nivel =  new ArrayList();
        
        Random rand = new Random();
        for(int i=0;i<10;i++){
            Integer a = rand.nextInt(4);
            nivel.add(nv[a]);
        }
                 
        var ex = new Grafico(nivel);
        ex.setVisible(true);
        
    }*/
}
