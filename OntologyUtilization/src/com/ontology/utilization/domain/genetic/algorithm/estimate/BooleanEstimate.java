package com.ontology.utilization.domain.genetic.algorithm.estimate;

import com.ontology.utilization.domain.BooleanChromosome;

public abstract class BooleanEstimate extends Estimate<BooleanChromosome> {

	public abstract void estimate(BooleanChromosome chromosome) throws Exception;

}
