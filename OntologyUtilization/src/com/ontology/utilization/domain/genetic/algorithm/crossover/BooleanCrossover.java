package com.ontology.utilization.domain.genetic.algorithm.crossover;

import java.util.List;

import com.ontology.utilization.domain.BooleanChromosome;

public abstract class BooleanCrossover extends Crossover<BooleanChromosome> {

	protected int probabilityOfCrossover;

	public BooleanCrossover(int probabilityOfCrossover) {
		this.probabilityOfCrossover = probabilityOfCrossover;
	}

	public int getProbabilityOfCrossover() {
		return this.probabilityOfCrossover;
	}

	public void setProbabilityOfCrossover(int probabilityOfCrossover) {
		this.probabilityOfCrossover = probabilityOfCrossover;
	}

	/* Abstract METHODS */
	public abstract List<BooleanChromosome> crossover(
			List<BooleanChromosome> parents);

}
