package com.ontology.utilization.domain.genetic.algorithm.mutation;

import java.util.List;

public abstract class Mutation<T> {
	/* Abstract METHODS */
	public abstract List<T> mutation(List<T> chromosomes);

}
