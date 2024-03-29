package com.ontology.utilization.domain.genetic.algorithm.crossover;

import java.util.List;

public abstract class Crossover<T> {
	public static final String ONE_POINT_BOOLEAN_CROSOVER_WITHOUT_ESTIMATE = "Krzyżowanie jednopunktowe bez estymacji";
	public static final String ONE_POINT_BOOLEAN_CROSOVER_WITH_ESTIMATE = "Krzyżowanie jednopunktowe";
	public static final String TWO_POINT_BOOLEAN_CROSOVER_WITHOUT_ESTIMATE = "Krzyżowanie dwupunktowe bez estymacji";
	public static final String TWO_POINT_BOOLEAN_CROSOVER_WITH_ESTIMATE = "Krzyżowanie dwupunktowe";
	
	public static final String BOOLEAN_CROSSOVERS[] = {ONE_POINT_BOOLEAN_CROSOVER_WITH_ESTIMATE,ONE_POINT_BOOLEAN_CROSOVER_WITHOUT_ESTIMATE,TWO_POINT_BOOLEAN_CROSOVER_WITH_ESTIMATE,TWO_POINT_BOOLEAN_CROSOVER_WITHOUT_ESTIMATE};
	
	/* Abstract METHODS */
	public abstract List<T> crossover(List<T> parents);

}
