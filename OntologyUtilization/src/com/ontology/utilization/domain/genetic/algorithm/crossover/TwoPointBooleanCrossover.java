package com.ontology.utilization.domain.genetic.algorithm.crossover;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.ontology.utilization.domain.BooleanChromosome;
import com.ontology.utilization.domain.genetic.algorithm.estimate.BooleanEstimate;
import com.ontology.utilization.factory.BooleanChromosomeFactory;

public class TwoPointBooleanCrossover extends BooleanCrossover {

	private BooleanEstimate booleanEstimate;

	public TwoPointBooleanCrossover(int probabilityOfCrossover,
			BooleanEstimate booleanEstimate) {
		super(probabilityOfCrossover);
		this.booleanEstimate = booleanEstimate;
	}

	@Override
	public List<BooleanChromosome> crossover(List<BooleanChromosome> parents) {
		List<BooleanChromosome> crossoverGeneration = new LinkedList<BooleanChromosome>();

		if (parents.size() % 2 == 1) {
			parents.add(parents.get(0));
		}

		for (int i = 0; i < parents.size(); i += 2) {
			Random r = new Random();
			Integer random = r.nextInt(100);

			BooleanChromosome parent1 = parents.get(i);
			BooleanChromosome parent2 = parents.get(i + 1);
			BooleanChromosome child1 = null;
			BooleanChromosome child2 = null;

			if (random <= probabilityOfCrossover) {

				child1 = new BooleanChromosome(BooleanChromosomeFactory
						.getInstance().getNextCount());
				child2 = new BooleanChromosome(BooleanChromosomeFactory
						.getInstance().getNextCount());

				for (int cecha = 0; cecha < 9; cecha++) {
					Random rGen = new Random();
					Integer fistPointOfCut = rGen.nextInt(4) + 1;
					Integer secondPointOfCut = rGen.nextInt(3) + 5;
					for (int p1 = 0; p1 < 10; p1++) {

						if (p1 <= fistPointOfCut) {
							child1.setValue(cecha, p1, parent1.getDescription()
									.get(cecha).get(p1));
							child2.setValue(cecha, p1, parent2.getDescription()
									.get(cecha).get(p1));
						} else if (p1 <= secondPointOfCut
								&& p1 >= fistPointOfCut) {
							child1.setValue(cecha, p1, parent2.getDescription()
									.get(cecha).get(p1));
							child2.setValue(cecha, p1, parent1.getDescription()
									.get(cecha).get(p1));
						} else {
							child1.setValue(cecha, p1, parent1.getDescription()
									.get(cecha).get(p1));
							child2.setValue(cecha, p1, parent2.getDescription()
									.get(cecha).get(p1));
						}
					}
				}

			}

			if (booleanEstimate == null) {

				crossoverGeneration.add(parent1);
				crossoverGeneration.add(parent2);
				if (child1 != null) {
					crossoverGeneration.add(child1);
				}
				if (child2 != null) {
					crossoverGeneration.add(child2);
				}
			} else {

				try {
					booleanEstimate.estimate(parent1);
					booleanEstimate.estimate(parent2);
					if (child1 != null) {
						booleanEstimate.estimate(child1);
					}
					if (child2 != null) {
						booleanEstimate.estimate(child2);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

				List<BooleanChromosome> bestOfCrossover = new LinkedList<BooleanChromosome>();

				bestOfCrossover.add(parent1);
				bestOfCrossover.add(parent2);
				if (child1 != null) {
					bestOfCrossover.add(child1);
				}
				if (child2 != null) {
					bestOfCrossover.add(child2);
				}

				Collections.sort(bestOfCrossover);

				crossoverGeneration.add(bestOfCrossover.get(0));
				crossoverGeneration.add(bestOfCrossover.get(1));

			}

		}

		return crossoverGeneration;
	}

}
