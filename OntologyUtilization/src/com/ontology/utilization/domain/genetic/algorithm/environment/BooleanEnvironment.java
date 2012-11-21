package com.ontology.utilization.domain.genetic.algorithm.environment;

import java.util.List;

import com.ontology.utilization.domain.BooleanChromosome;

public class BooleanEnvironment extends Environment {

	private Boolean isMalignatRule;
	private int sizeOfInitialPopulation; // zakres od 100 - 1000
	private int sizeOfStandardPopulation; // zakres od 30 - 100
	private int probabilityOfCrossover; // zakres od 0 - 100
	private int probabilityOfMutation; // zakres od 0 - 100
	private int iteration; //
	private int threshold; // zakres od 0 - 683

	private Boolean isTerminated;

	private int currentIteration;

	public BooleanEnvironment(Boolean isMalignatRule,
			int sizeOfInitialPopulation, int sizeOfStandardPopulation,
			int probabilityOfCrossover, int probabilityOfMutation,
			int iteration, int threshold) {
		super();
		this.isMalignatRule = isMalignatRule;
		this.sizeOfInitialPopulation = sizeOfInitialPopulation;
		this.sizeOfStandardPopulation = sizeOfStandardPopulation;
		this.probabilityOfCrossover = probabilityOfCrossover;
		this.probabilityOfMutation = probabilityOfMutation;
		this.iteration = iteration;
		this.threshold = threshold;
		this.isTerminated = false;
		currentIteration = 0;
	}

	public int getSizeOfInitialPopulation() {
		return sizeOfInitialPopulation;
	}

	public void setSizeOfInitialPopulation(int sizeOfInitialPopulation) {
		this.sizeOfInitialPopulation = sizeOfInitialPopulation;
	}

	public int getSizeOfStandardPopulation() {
		return sizeOfStandardPopulation;
	}

	public void setSizeOfStandardPopulation(int sizeOfStandardPopulation) {
		this.sizeOfStandardPopulation = sizeOfStandardPopulation;
	}

	public int getProbabilityOfCrossover() {
		return probabilityOfCrossover;
	}

	public void setProbabilityOfCrossover(int probabilityOfCrossover) {
		this.probabilityOfCrossover = probabilityOfCrossover;
	}

	public int getProbabilityOfMutation() {
		return probabilityOfMutation;
	}

	public void setProbabilityOfMutation(int probabilityOfMutation) {
		this.probabilityOfMutation = probabilityOfMutation;
	}

	public int getIteration() {
		return iteration;
	}

	public void setIteration(int iteration) {
		this.iteration = iteration;
	}

	public int getThreshold() {
		return threshold;
	}

	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}

	public void computeTerminantion(List<BooleanChromosome> currentPopulation) {

		currentIteration++;
		if (currentIteration >= iteration) {
			isTerminated = true;
			return;
		}

		if (threshold <= currentPopulation.get(0).getScore()) {
			isTerminated = true;
			return;
		}

	}

	public Boolean getIsMalignatRule() {
		return isMalignatRule;
	}

	public void setIsMalignatRule(Boolean isMalignatRule) {
		this.isMalignatRule = isMalignatRule;
	}

	@Override
	public Boolean isTerminated() {
		return isTerminated;
	}

	@Override
	public String toString() {
		return currentIteration + " currentIteration | " + iteration
				+ " iteration | threschold " + threshold;
	}

	public int getCurrentIteration() {
		return currentIteration;
	}

}
