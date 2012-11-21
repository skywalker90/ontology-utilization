package com.ontology.utilization.domain.genetic.algorithm.handler;

import java.util.List;

import com.ontology.utilization.domain.BooleanChromosome;
import com.ontology.utilization.domain.representation.RuleRepresentation;

public abstract class AlgorithmHandler {

	protected boolean isTerminated;

	public AlgorithmHandler() {
		this.isTerminated = false;
	}

	public abstract void appendStatistic(Double averageFitness, int bestFitness);

	public abstract void appendStateHandler(StateOfAlgorithm state);

	public abstract void appendCurrentPopulation(
			List<BooleanChromosome> appendCurrentPopulation);

	public abstract void appendBestBooleanChromosome(
			BooleanChromosome bestBooleanChromosome);

	public void stopAlgorithm() {
		synchronized (this) {
			this.isTerminated = true;
		}
	}

	public boolean isTerminated() {
		return this.isTerminated;
	}

}
