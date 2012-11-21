package com.ontology.utilization.domain.genetic.algorithm.selection;

import java.util.List;

public abstract class Selection<T> {

	/* Abstract METHODS */
	public abstract List<T> selection(List<T> currentPopulation);

}
