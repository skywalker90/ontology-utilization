package com.ontology.utilization.domain.genetic.algorithm.selection;

import java.util.List;

import com.ontology.utilization.domain.BooleanChromosome;

public abstract class BooleanSelection extends Selection<BooleanChromosome> {

	/* Abstract METHODS */
	public abstract List<BooleanChromosome> selection(List<BooleanChromosome> currentPopulation);

}
