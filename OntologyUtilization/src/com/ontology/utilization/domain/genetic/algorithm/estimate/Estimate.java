package com.ontology.utilization.domain.genetic.algorithm.estimate;

public abstract class Estimate<T> {

	public abstract void estimate(T chromosome) throws Exception;

}
