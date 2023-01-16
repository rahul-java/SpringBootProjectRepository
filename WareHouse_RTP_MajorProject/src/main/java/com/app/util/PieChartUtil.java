package com.app.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.stereotype.Component;

@Component
public class PieChartUtil {

	public void generatePiChart(String path,List<Object[]> list)
	{
		DefaultPieDataset defaultPieDataset = new DefaultPieDataset();
		for(Object[] ob:list)
		{
			//key=>String, Value=>double
			defaultPieDataset.setValue(String.valueOf(ob[0]), Double.valueOf(ob[1].toString()));
		}
		//setting title of pie chart
		JFreeChart pieChart3D = ChartFactory.createPieChart3D("UOM Report", defaultPieDataset);
		
		try
		{
			ChartUtilities.saveChartAsJPEG(new File(path+"/uom.jpg"), pieChart3D, 500, 400);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	
	public void generateBarChart(String path,List<Object[]> list)
	{
		DefaultCategoryDataset defaultCategoryDataset = new DefaultCategoryDataset();
		for(Object[] ob:list)
		{
			defaultCategoryDataset.setValue(Double.valueOf(ob[1].toString()), String.valueOf(ob[0]), "");
		}
		JFreeChart createBarChart = ChartFactory.createBarChart("UOM Data", "Uom Type", "count", defaultCategoryDataset);
		
		try
		{
			ChartUtilities.saveChartAsJPEG(new File(path+"/uomBar.jpg"), createBarChart, 500, 400);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
