package com.ontology.utilization.domain.genetic.algorithm.environment;

public abstract class Environment {
	public abstract Boolean isTerminated();

	public abstract int getSizeOfInitialPopulation();

	public abstract int getSizeOfStandardPopulation();

	public abstract int getProbabilityOfCrossover();

	public abstract int getProbabilityOfMutation();
}
