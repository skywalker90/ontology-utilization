package com.ontology.utilization.domain.genetic.algorithm.selection;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.ontology.utilization.domain.BooleanChromosome;
import com.ontology.utilization.factory.BooleanChromosomeFactory;

public class BooleanPostSelection extends BooleanSelection {

	private int sizeOfStandardPopulation;

	public BooleanPostSelection(int sizeOfStandardPopulation) {
		this.sizeOfStandardPopulation = sizeOfStandardPopulation;
	}

	@Override
	public List<BooleanChromosome> selection(
			List<BooleanChromosome> currentPopulation) {
		List<BooleanChromosome> listOfNewPopulation = new LinkedList<BooleanChromosome>();

		listOfNewPopulation.add(currentPopulation.get(0));

		for (int i = 1; i < currentPopulation.size(); i++) {
			BooleanChromosome bChFromCurrentPopulation = currentPopulation
					.get(i);
			BooleanChromosome bChFromNewPopulation = listOfNewPopulation
					.get(listOfNewPopulation.size() - 1);
			if (bChFromCurrentPopulation.getId() != bChFromNewPopulation
					.getId()) {
				listOfNewPopulation.add(bChFromCurrentPopulation);
			}
		}

		int countHowMuchNewChromosomeNeeded = sizeOfStandardPopulation
				- listOfNewPopulation.size();
		for (int j = 0; j < countHowMuchNewChromosomeNeeded; j++) {
			listOfNewPopulation.add(BooleanChromosomeFactory.getInstance()
					.createRandomChromosome());
		}

		while (true) {
			if (listOfNewPopulation.size() > sizeOfStandardPopulation) {
				Random r = new Random();
				int index = r.nextInt(listOfNewPopulation.size() - 10) + 10;
				listOfNewPopulation.remove(index);
			} else {
				break;
			}
		}

		return listOfNewPopulation;
	}

}
