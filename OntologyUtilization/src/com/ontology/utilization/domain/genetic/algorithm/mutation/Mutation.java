package com.ontology.utilization.domain.genetic.algorithm.mutation;

import java.util.List;

public abstract class Mutation<T> {
	public static final String ONE_POINT_BOOLEAN_MUTATION_WITHOUT_ESTIMATE = "Mutacja jednopunktowa bez estymacji";
	public static final String ONE_POINT_BOOLEAN_MUTATION_WITH_ESTIMATE = "Mutacja jednopunktowa";
	public static final String MULTI_POINT_BOOLEAN_MUTATION_WITHOUT_ESTIMATE = "Mutacja wielopunktowa bez estymacji";
	public static final String MULTI_POINT_BOOLEAN_MUTATION_WITH_ESTIMATE = "Mutacja wielopunktowa";
	
	public static final String BOOLEAN_MUTATIONS[] = {ONE_POINT_BOOLEAN_MUTATION_WITH_ESTIMATE,ONE_POINT_BOOLEAN_MUTATION_WITHOUT_ESTIMATE,MULTI_POINT_BOOLEAN_MUTATION_WITH_ESTIMATE,MULTI_POINT_BOOLEAN_MUTATION_WITHOUT_ESTIMATE};
	/* Abstract METHODS */
	public abstract List<T> mutation(List<T> chromosomes);

}
