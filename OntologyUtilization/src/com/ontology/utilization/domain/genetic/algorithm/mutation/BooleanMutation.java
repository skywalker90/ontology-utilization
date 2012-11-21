package com.ontology.utilization.domain.genetic.algorithm.mutation;

import java.util.List;

import com.ontology.utilization.domain.BooleanChromosome;

public abstract class BooleanMutation extends Mutation<BooleanChromosome> {

	protected int probabilityOfMutation;

	public BooleanMutation(int probabilityOfMutation) {
		super();
		this.probabilityOfMutation = probabilityOfMutation;
	}

	public int getProbabilityOfMutation() {
		return probabilityOfMutation;
	}

	public void setProbabilityOfMutation(int probabilityOfMutation) {
		this.probabilityOfMutation = probabilityOfMutation;
	}

	/* Abstract METHODS */
	public abstract List<BooleanChromosome> mutation(
			List<BooleanChromosome> chromosomes);

}
