package com.ontology.utilization.domain.genetic.algorithm.selection;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.ontology.utilization.domain.BooleanChromosome;

public class BooleanRouletteSelection extends BooleanSelection {

	@Override
	public List<BooleanChromosome> selection(
			List<BooleanChromosome> currentPopulation) {
		List<BooleanChromosome> futureParents = new LinkedList<BooleanChromosome>();

		Integer sumOfAll = 0;
		List<Integer> listOfProbability = new LinkedList<Integer>();
		listOfProbability.add(0);

		for (BooleanChromosome bCh : currentPopulation) {
			sumOfAll += bCh.getScore();
			listOfProbability.add(sumOfAll);
		}

		for (int i = 0; i < currentPopulation.size(); i++) {
			Random r = new Random();
			int number = r.nextInt(sumOfAll);

			for (int j = 0; j < currentPopulation.size(); j++) {
				int lowerBound = listOfProbability.get(j);
				int upperBound = listOfProbability.get(j + 1);

				if (lowerBound <= number && number < upperBound) {
					futureParents.add(currentPopulation.get(j));
				}
			}
		}
		
		return futureParents;
	}
}
