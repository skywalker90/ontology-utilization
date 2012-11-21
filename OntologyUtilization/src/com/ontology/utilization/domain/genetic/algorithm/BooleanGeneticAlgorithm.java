package com.ontology.utilization.domain.genetic.algorithm;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.ontology.utilization.domain.BooleanChromosome;
import com.ontology.utilization.domain.genetic.algorithm.crossover.BooleanCrossover;
import com.ontology.utilization.domain.genetic.algorithm.environment.BooleanEnvironment;
import com.ontology.utilization.domain.genetic.algorithm.estimate.BooleanEstimate;
import com.ontology.utilization.domain.genetic.algorithm.exception.NotFoundPatientException;
import com.ontology.utilization.domain.genetic.algorithm.handler.AlgorithmHandler;
import com.ontology.utilization.domain.genetic.algorithm.handler.StateOfAlgorithm;
import com.ontology.utilization.domain.genetic.algorithm.mutation.BooleanMutation;
import com.ontology.utilization.domain.genetic.algorithm.selection.BooleanPopulationSizeSelection;
import com.ontology.utilization.domain.genetic.algorithm.selection.BooleanPostSelection;
import com.ontology.utilization.domain.genetic.algorithm.selection.BooleanSelection;
import com.ontology.utilization.domain.representation.RuleRepresentation;
import com.ontology.utilization.factory.BooleanChromosomeFactory;

public class BooleanGeneticAlgorithm implements Runnable {

	private List<BooleanChromosome> bestBooleanChromosomes;
	private BooleanChromosome bestBooleanChromosome;
	private BooleanEnvironment environment;
	private BooleanSelection preselection;
	private BooleanCrossover crossover;
	private BooleanMutation mutation;
	private BooleanEstimate estimate;
	private AlgorithmHandler handler;

	public BooleanGeneticAlgorithm(BooleanEnvironment environment,
			BooleanSelection preselection, BooleanCrossover crossover,
			BooleanMutation mutation, BooleanEstimate estimate,
			AlgorithmHandler stateHandler) {
		this.environment = environment;
		this.preselection = preselection;
		this.crossover = crossover;
		this.mutation = mutation;
		this.estimate = estimate;
		this.handler = stateHandler;
	}

	@Override
	public void run() {

		handler.appendStateHandler(StateOfAlgorithm.START);

		/*** INICJALIZACJA ZMIENNYCH ***/
		// ZAWSZE TAK SAMO INICJALIZOWANE
		BooleanSelection populationSelection = new BooleanPopulationSizeSelection(
				environment.getSizeOfStandardPopulation());
		// ZAWSZE TAK SAMO INICJALIZOWANE
		BooleanSelection postSelection = new BooleanPostSelection(
				environment.getSizeOfStandardPopulation());
		/*** INICJALIZACJA ZMIENNYCH ***/

		handler.appendStateHandler(StateOfAlgorithm.INICJALIZACJA_POPULACJI);
		/*** POPULACJA POCZATKOWA I OCENA JEJ ***/
		List<BooleanChromosome> initialPopulation = new LinkedList<BooleanChromosome>();
		for (int i = 0; i < environment.getSizeOfInitialPopulation(); i++) {
			BooleanChromosome bCh = BooleanChromosomeFactory.getInstance()
					.createRandomChromosome();
			try {
				estimate.estimate(bCh);
				initialPopulation.add(bCh);
			} catch (NotFoundPatientException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		/*** KONIEC POPULACJA POCZATKOWA I OCENA JEJ ***/
		/** SORTOWANIE POPULACJI */
		Collections.sort(initialPopulation);

		/**
		 * WYBRANIE NAJLEPSZYCH CHROMOSOMOW Z POPULACJI POCZATKOWEJ PRZY
		 * ZACHOWANIE STANDARDOWEJ WIELKOŒCI
		 **/
		List<BooleanChromosome> currentPopulation = populationSelection
				.selection(initialPopulation);

		while (environment.isTerminated() == false
				&& handler.isTerminated() == false) {

			/** PRESELEKCJA -> ROULLETTE */
			handler.appendStateHandler(StateOfAlgorithm.PRESELEKCJA);
			List<BooleanChromosome> parents = preselection
					.selection(currentPopulation);

			/** KRZYZOWANIE NA RODZICACH */
			handler.appendStateHandler(StateOfAlgorithm.KRZYZOWNIE);
			List<BooleanChromosome> nextGeneration = crossover
					.crossover(parents);
			/** MUTACJA NOWEGO POKOLENIA */
			handler.appendStateHandler(StateOfAlgorithm.MUTACJA);
			nextGeneration = mutation.mutation(nextGeneration);

			/** START --- OCENA CA£OŒCI I SORTOWANIE **/
			for (int index = 0; index < nextGeneration.size(); index++) {
				try {
					estimate.estimate(nextGeneration.get(index));
				} catch (NotFoundPatientException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			Collections.sort(nextGeneration);
			/** KONIEC --- OCENA CA£OŒCI I SORTOWANIE **/

			/** POSTSELEKCJA -> WYRÓWNANIE WILEKOŒCI GDY NIE JEST ZACHOWANA */
			handler.appendStateHandler(StateOfAlgorithm.POSTSELEKCJA);
			nextGeneration = postSelection.selection(nextGeneration);

			// ODPOWIEDNIE PRZYPISANIA
			currentPopulation = nextGeneration;
			bestBooleanChromosomes = currentPopulation;

			/** START STATYSTYKI */
			setBestChromosome(currentPopulation.get(0));
			Double sumOfAllScore = 0.0;
			for (int index = 0; index < currentPopulation.size(); index++) {
				if (currentPopulation.get(index).getScore() > 0) {
					sumOfAllScore += currentPopulation.get(index).getScore();
				}
			}
			handler.appendStatistic((sumOfAllScore / currentPopulation.size()),
					currentPopulation.get(0).getScore());

			/** KONIEC STATYSTYKI */

			environment.computeTerminantion(currentPopulation);
		}
		handler.appendBestBooleanChromosome(bestBooleanChromosome);
		handler.appendCurrentPopulation(bestBooleanChromosomes);
		handler.appendStateHandler(StateOfAlgorithm.KONIEC);
	}

	private void setBestChromosome(BooleanChromosome chromosome) {
		if (bestBooleanChromosome == null) {
			bestBooleanChromosome = chromosome;
		} else {
			if (chromosome.getScore() > bestBooleanChromosome.getScore()) {
				bestBooleanChromosome = chromosome;
			}
		}
	}
}
