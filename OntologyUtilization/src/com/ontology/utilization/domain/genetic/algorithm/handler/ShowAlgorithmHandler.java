package com.ontology.utilization.domain.genetic.algorithm.handler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.ontology.utilization.domain.BooleanChromosome;
import com.ontology.utilization.domain.representation.RuleRepresentation;

public class ShowAlgorithmHandler extends AlgorithmHandler {

	@Override
	public void appendStateHandler(StateOfAlgorithm state) {
		System.out.println("State: " + state);
	}

	@Override
	public void appendStatistic(Double averageFitness, int bestFitness) {
		System.out.println("AverageFitness - " + averageFitness);
		System.out.println("BestFitness - " + bestFitness);
	}

	@Override
	public void appendCurrentPopulation(
			List<BooleanChromosome> currentPopulation) {
		System.out.println("List of the best last ITERATION");
		for (int i = 0; i < currentPopulation.size(); i++) {
			System.out.println(currentPopulation.get(i).getId());
		}
		System.out.println("-----------------------");

	}

	@Override
	public void appendBestBooleanChromosome(
			BooleanChromosome bestBooleanChromosome) {
		System.out.println("appendBestRuleRepresentation");
		System.out.println(bestBooleanChromosome);
	}

}
