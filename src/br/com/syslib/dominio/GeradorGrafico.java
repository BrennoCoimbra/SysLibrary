package br.com.syslib.dominio;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;

import br.com.syslib.core.util.ConverteDate;

public class GeradorGrafico extends EntidadeDominio {
	
	@SuppressWarnings("deprecation")
	public static void geraRelatorioLinhasByDia(List<Relatorio> relatorios) {
		DefaultCategoryDataset dsQtde = new DefaultCategoryDataset();
		DefaultCategoryDataset dsValor = new DefaultCategoryDataset();
		
		for (Relatorio relatorio : relatorios) {
			String date = ConverteDate.converteDateString(relatorio.getData());
			dsQtde.addValue(relatorio.getQtde(), relatorio.getTitulo(), date);
			dsValor.addValue(relatorio.getValor(), relatorio.getTitulo(), date);
		}
		
		JFreeChart graficoQtde = ChartFactory.createLineChart("Quantidade Livros Vendidos", "Por dia", "Quantidade", dsQtde, PlotOrientation.VERTICAL, true, true, false);
		JFreeChart graficoValor = ChartFactory.createBarChart("Valor de Venda de Livros", "Por dia", "Valores", dsValor, PlotOrientation.VERTICAL, true, true, false);
		
		
		//Quantidade de Livros Vendidos
		CategoryPlot plotQtde = graficoQtde.getCategoryPlot();
//		plotQtde.getRenderer().setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
//		plotQtde.getRenderer().setBaseItemLabelsVisible(true);
		
		plotQtde.setDomainAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
		plotQtde.setRangeAxisLocation(AxisLocation.TOP_OR_LEFT);

		
		CategoryItemRenderer renderer = plotQtde.getRenderer();
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setBaseItemLabelsVisible(true);
		ItemLabelPosition p = new ItemLabelPosition(ItemLabelAnchor.INSIDE1,
		TextAnchor.BOTTOM_LEFT, TextAnchor.BOTTOM_CENTER, 0.0);
		renderer.setBasePositiveItemLabelPosition(p);
		
		//Define o fundo e traço do fundo
		plotQtde.setBackgroundPaint(Color.lightGray);
		plotQtde.setRangeGridlinePaint(Color.white);
		
		//Define o eixo como inteiro
		NumberAxis rangeAxis = (NumberAxis) plotQtde.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setAutoRangeIncludesZero(true);
        
        //Cria ponto ao atingir
        LineAndShapeRenderer renderer1 = (LineAndShapeRenderer) plotQtde.getRenderer(); renderer1.setShapesVisible(true);
        renderer1.setDrawOutlines(true); renderer1.setUseFillPaint(true);

        
		CategoryPlot plotValor = graficoValor.getCategoryPlot();
		plotValor.getRenderer().setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		plotValor.getRenderer().setBaseItemLabelsVisible(true);
		
		try {						

			File file = new File("/Users/brenno/apache-tomcat-9.0.26/wtpwebapps/SysLibrary/resources/graphics/GraficoLinha_Qtde.png");
			OutputStream arquivo = new FileOutputStream(file);
			ChartUtilities.writeChartAsPNG(arquivo, graficoQtde, 800, 400);
			arquivo.close();
						
			file = new File("/Users/brenno/apache-tomcat-9.0.26/wtpwebapps/SysLibrary/resources/graphics/GraficoLinha_Valor.png");
			arquivo = new FileOutputStream(file);
			ChartUtilities.writeChartAsPNG(arquivo, graficoValor, 800, 400);
			arquivo.close();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@SuppressWarnings("deprecation")
	public static void geraRelatorioLinhasByMes(List<Relatorio> relatorios) {
		DefaultCategoryDataset dsQtde = new DefaultCategoryDataset();
		DefaultCategoryDataset dsValor = new DefaultCategoryDataset();
		
		for (Relatorio relatorio : relatorios) {
			dsQtde.addValue(relatorio.getQtde(), relatorio.getTitulo(), relatorio.getMes() + "/" + relatorio.getAno());
			dsValor.addValue(relatorio.getValor(), relatorio.getTitulo(), relatorio.getMes() + "/" + relatorio.getAno());
		}
		
		JFreeChart graficoQtde = ChartFactory.createLineChart("Quantidade Livros Vendidos", "Por Mês", "Quantidade", dsQtde, PlotOrientation.VERTICAL, true, true, false);
		JFreeChart graficoValor = ChartFactory.createBarChart("Valor de Venda de Livros", "Por Mês", "Valores", dsValor, PlotOrientation.VERTICAL, true, true, false);
		
		
		//Quantidade de Livros Vendidos
		CategoryPlot plotQtde = graficoQtde.getCategoryPlot();
//		plotQtde.getRenderer().setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
//		plotQtde.getRenderer().setBaseItemLabelsVisible(true);
		
		plotQtde.setDomainAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
		plotQtde.setRangeAxisLocation(AxisLocation.TOP_OR_LEFT);

		
		CategoryItemRenderer renderer = plotQtde.getRenderer();
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setBaseItemLabelsVisible(true);
		ItemLabelPosition p = new ItemLabelPosition(ItemLabelAnchor.INSIDE1,
		TextAnchor.BOTTOM_LEFT, TextAnchor.CENTER, 0.0);
		renderer.setBasePositiveItemLabelPosition(p);
		
		//Define o fundo e traço do fundo
		plotQtde.setBackgroundPaint(Color.lightGray);
		plotQtde.setRangeGridlinePaint(Color.white);
		
		//Define o eixo como inteiro
		NumberAxis rangeAxis = (NumberAxis) plotQtde.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setAutoRangeIncludesZero(true);
        
        //Cria ponto ao atingir
        LineAndShapeRenderer renderer1 = (LineAndShapeRenderer) plotQtde.getRenderer(); renderer1.setShapesVisible(true);
        renderer1.setDrawOutlines(true); renderer1.setUseFillPaint(true);

        
		CategoryPlot plotValor = graficoValor.getCategoryPlot();
		plotValor.getRenderer().setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		plotValor.getRenderer().setBaseItemLabelsVisible(true);
		
		try {						

			File file = new File("/Users/brenno/apache-tomcat-9.0.26/wtpwebapps/SysLibrary/resources/graphics/GraficoLinha_Qtde.png");
			OutputStream arquivo = new FileOutputStream(file);
			ChartUtilities.writeChartAsPNG(arquivo, graficoQtde, 550, 400);
			arquivo.close();
						
			file = new File("/Users/brenno/apache-tomcat-9.0.26/wtpwebapps/SysLibrary/resources/graphics/GraficoLinha_Valor.png");
			arquivo = new FileOutputStream(file);
			ChartUtilities.writeChartAsPNG(arquivo, graficoValor, 550, 400);
			arquivo.close();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	public static void geraRelatorioLinhasByAno(List<Relatorio> relatorios) {
		DefaultCategoryDataset dsQtde = new DefaultCategoryDataset();
		DefaultCategoryDataset dsValor = new DefaultCategoryDataset();
		
		for (Relatorio relatorio : relatorios) {
			dsQtde.addValue(relatorio.getQtde(), relatorio.getTitulo(), relatorio.getAno() + ".");
			dsValor.addValue(relatorio.getValor(), relatorio.getTitulo(), relatorio.getAno() + ".");
		}
		
		JFreeChart graficoQtde = ChartFactory.createLineChart("Quantidade Livros Vendidos", "Por ano", "Quantidade", dsQtde, PlotOrientation.VERTICAL, true, true, false);
		JFreeChart graficoValor = ChartFactory.createBarChart("Valor de Venda de Livros", "Por ano", "Valores", dsValor, PlotOrientation.VERTICAL, true, true, false);
		
		
		//Quantidade de Livros Vendidos
		CategoryPlot plotQtde = graficoQtde.getCategoryPlot();
//		plotQtde.getRenderer().setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
//		plotQtde.getRenderer().setBaseItemLabelsVisible(false);
		plotQtde.setDomainAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
		plotQtde.setRangeAxisLocation(AxisLocation.TOP_OR_LEFT);

		
		CategoryItemRenderer renderer = plotQtde.getRenderer();
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setBaseItemLabelsVisible(true);
		ItemLabelPosition p = new ItemLabelPosition(ItemLabelAnchor.INSIDE1,
		TextAnchor.BOTTOM_LEFT, TextAnchor.CENTER, 0.0);
		renderer.setBasePositiveItemLabelPosition(p);

		//Define o fundo e traço do fundo
		plotQtde.setBackgroundPaint(Color.lightGray);
		plotQtde.setRangeGridlinePaint(Color.white);
		
		//Define o eixo como inteiro
		NumberAxis rangeAxis = (NumberAxis) plotQtde.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setAutoRangeIncludesZero(true);
        
        //Cria ponto ao atingir
        LineAndShapeRenderer renderer1 = (LineAndShapeRenderer) plotQtde.getRenderer(); renderer1.setShapesVisible(true);
        renderer1.setDrawOutlines(true); renderer1.setUseFillPaint(true);

        
		CategoryPlot plotValor = graficoValor.getCategoryPlot();
		plotValor.getRenderer().setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		plotValor.getRenderer().setBaseItemLabelsVisible(true);
		
		try {						

			File file = new File("/Users/brenno/apache-tomcat-9.0.26/wtpwebapps/SysLibrary/resources/graphics/GraficoLinha_Qtde.png");
			OutputStream arquivo = new FileOutputStream(file);
			ChartUtilities.writeChartAsPNG(arquivo, graficoQtde, 550, 400);
			arquivo.close();
						
			file = new File("/Users/brenno/apache-tomcat-9.0.26/wtpwebapps/SysLibrary/resources/graphics/GraficoLinha_Valor.png");
			arquivo = new FileOutputStream(file);
			ChartUtilities.writeChartAsPNG(arquivo, graficoValor, 550, 400);
			arquivo.close();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	public static void geraRelatorioLinhasBySemanal(List<Relatorio> relatorios) {
		DefaultCategoryDataset dsQtde = new DefaultCategoryDataset();
		DefaultCategoryDataset dsValor = new DefaultCategoryDataset();
		
		for (Relatorio relatorio : relatorios) {
			dsQtde.addValue(relatorio.getQtde(), relatorio.getTitulo(), relatorio.getMes() + "/" + relatorio.getAno());
			dsValor.addValue(relatorio.getValor(), relatorio.getTitulo(), relatorio.getMes() + "/" + relatorio.getAno());
		}
		
		JFreeChart graficoQtde = ChartFactory.createLineChart("Quantidade Livros Vendidos", "Por Semana", "Quantidade", dsQtde, PlotOrientation.VERTICAL, true, true, false);
		JFreeChart graficoValor = ChartFactory.createBarChart("Valor de Venda de Livros", "Por Semana", "Valores", dsValor, PlotOrientation.VERTICAL, true, true, false);
		
		
		//Quantidade de Livros Vendidos
		CategoryPlot plotQtde = graficoQtde.getCategoryPlot();
//		plotQtde.getRenderer().setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
//		plotQtde.getRenderer().setBaseItemLabelsVisible(true);
		
		plotQtde.setDomainAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
		plotQtde.setRangeAxisLocation(AxisLocation.TOP_OR_LEFT);

		
		CategoryItemRenderer renderer = plotQtde.getRenderer();
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setBaseItemLabelsVisible(true);
		ItemLabelPosition p = new ItemLabelPosition(ItemLabelAnchor.INSIDE1,
		TextAnchor.BOTTOM_LEFT, TextAnchor.CENTER, 0.0);
		renderer.setBasePositiveItemLabelPosition(p);
		
		//Define o fundo e traço do fundo
		plotQtde.setBackgroundPaint(Color.lightGray);
		plotQtde.setRangeGridlinePaint(Color.white);
		
		//Define o eixo como inteiro
		NumberAxis rangeAxis = (NumberAxis) plotQtde.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setAutoRangeIncludesZero(true);
        
        //Cria ponto ao atingir
        LineAndShapeRenderer renderer1 = (LineAndShapeRenderer) plotQtde.getRenderer(); renderer1.setShapesVisible(true);
        renderer1.setDrawOutlines(true); renderer1.setUseFillPaint(true);

        
		CategoryPlot plotValor = graficoValor.getCategoryPlot();
		plotValor.getRenderer().setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		plotValor.getRenderer().setBaseItemLabelsVisible(true);
		
		try {						

			File file = new File("/Users/brenno/apache-tomcat-9.0.26/wtpwebapps/SysLibrary/resources/graphics/GraficoLinha_Qtde.png");
			OutputStream arquivo = new FileOutputStream(file);
			ChartUtilities.writeChartAsPNG(arquivo, graficoQtde, 550, 400);
			arquivo.close();
						
			file = new File("/Users/brenno/apache-tomcat-9.0.26/wtpwebapps/SysLibrary/resources/graphics/GraficoLinha_Valor.png");
			arquivo = new FileOutputStream(file);
			ChartUtilities.writeChartAsPNG(arquivo, graficoValor, 550, 400);
			arquivo.close();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
