package com.ontology.utilization.gui;

import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import com.ontology.utilization.domain.BooleanChromosome;
import com.ontology.utilization.domain.genetic.algorithm.handler.AlgorithmHandler;
import com.ontology.utilization.domain.genetic.algorithm.handler.StateOfAlgorithm;

public class ChartsAlgorithmHandler extends AlgorithmHandler {
	private final ChartPanel chartPanel;
	private final XYSeries avgXYSeries;
	private final XYSeries bestXYSeries;
	private int currentX;
	private BooleanChromosome bestBooleanChromosome;

	public ChartsAlgorithmHandler() {
		avgXYSeries = new XYSeries("Œrednia");
		bestXYSeries = new XYSeries("Najlepszy");
		final XYSeriesCollection seriesCollection = new XYSeriesCollection();
		seriesCollection.addSeries(avgXYSeries);
		seriesCollection.addSeries(bestXYSeries);
		JFreeChart chart = ChartFactory.createXYLineChart("Algorytm genetyczny", "Numer pokolenia", "Ocena", seriesCollection, PlotOrientation.VERTICAL, true, true, false);
		chartPanel = new ChartPanel(chart);
	}

	public ChartPanel getChartPanel() {
		return chartPanel;
	}

	@Override
	public void appendStatistic(Double averageFitness, int bestFitness) {
		avgXYSeries.add(currentX, averageFitness);
		bestXYSeries.add(currentX, bestFitness);
		++currentX;
	}

	@Override
	public void appendStateHandler(StateOfAlgorithm state) {
		//System.out.println("State: " + state);
	}

	@Override
	public void appendCurrentPopulation(List<BooleanChromosome> currentPopulation) {
		System.out.println("List of the best last ITERATION");
		for (int i = 0; i < currentPopulation.size(); i++) {
			System.out.println(currentPopulation.get(i).getId());
		}
		System.out.println("-----------------------");

	}

	@Override
	public void appendBestBooleanChromosome(BooleanChromosome bestBooleanChromosome) {
		this.bestBooleanChromosome = bestBooleanChromosome;
	}

	public BooleanChromosome getBestBooleanChromosome() {
		return bestBooleanChromosome;
	}
}
