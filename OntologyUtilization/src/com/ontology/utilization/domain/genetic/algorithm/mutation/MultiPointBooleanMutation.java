package com.ontology.utilization.domain.genetic.algorithm.mutation;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.ontology.utilization.domain.BooleanChromosome;
import com.ontology.utilization.domain.genetic.algorithm.estimate.BooleanEstimate;

public class MultiPointBooleanMutation extends BooleanMutation {

	private BooleanEstimate estimate;

	public MultiPointBooleanMutation(int probabilityOfMutation,
			BooleanEstimate estimate) {
		super(probabilityOfMutation);
		this.estimate = estimate;
	}

	@Override
	public List<BooleanChromosome> mutation(List<BooleanChromosome> chromosomes) {
		List<BooleanChromosome> nextGeneration = new LinkedList<BooleanChromosome>();

		for (BooleanChromosome ch : chromosomes) {
			BooleanChromosome mutationChromosome = null;
			try {
				mutationChromosome = (BooleanChromosome) ch.clone();
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}

			if (mutationChromosome == null) {
				System.out
						.println("ERROR - OnePointBooleanMutation.mutation - mutationChromosome=null");
				continue;
			}

			for (int cecha = 0; cecha < 9; cecha++) {
				for (int gen = 0; gen < 10; gen++) {

					Random r = new Random();
					Integer random = r.nextInt(100);

					if (random < probabilityOfMutation) {

						mutationChromosome
								.getDescription()
								.get(cecha)
								.set(gen,
										!(mutationChromosome.getDescription()
												.get(cecha).get(gen)));

					}
				}

			}
			if (estimate == null) {
				nextGeneration.add(ch);
				nextGeneration.add(mutationChromosome);
			} else {
				try {
					estimate.estimate(mutationChromosome);
				} catch (Exception e) {
					e.printStackTrace();
				}

				if (mutationChromosome.getScore() >= ch.getScore()) {
					nextGeneration.add(mutationChromosome);
				} else {
					nextGeneration.add(ch);
				}
			}

		}

		return nextGeneration;
	}

}
