package com.ontology.utilization.domain.genetic.algorithm.crossover;

import java.util.List;

public abstract class Crossover<T> {

	/* Abstract METHODS */
	public abstract List<T> crossover(List<T> parents);

}
