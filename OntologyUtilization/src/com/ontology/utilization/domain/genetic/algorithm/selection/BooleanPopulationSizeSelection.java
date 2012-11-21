package com.ontology.utilization.domain.genetic.algorithm.selection;

import java.util.LinkedList;
import java.util.List;

import com.ontology.utilization.domain.BooleanChromosome;
import com.ontology.utilization.factory.BooleanChromosomeFactory;

public class BooleanPopulationSizeSelection extends BooleanSelection {

	private int sizeOfStandardPopulation;

	public BooleanPopulationSizeSelection(int sizeOfStandardPopulation) {
		this.sizeOfStandardPopulation = sizeOfStandardPopulation;
	}

	@Override
	public List<BooleanChromosome> selection(
			List<BooleanChromosome> currentPopulation) {

		if (currentPopulation.size() == sizeOfStandardPopulation) {
			return currentPopulation;
		} else {
			List<BooleanChromosome> listOfNewPopulation = new LinkedList<BooleanChromosome>();
			for (int i = 0; i < sizeOfStandardPopulation; i++) {
				listOfNewPopulation.add(currentPopulation.get(i));
			}
			return listOfNewPopulation;
		}
	}

}
