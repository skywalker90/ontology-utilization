package com.ontology.utilization.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.ontology.utilization.domain.BooleanChromosome;
import com.ontology.utilization.domain.BooleanPatient;
import com.ontology.utilization.domain.genetic.algorithm.crossover.BooleanCrossover;
import com.ontology.utilization.domain.genetic.algorithm.crossover.TwoPointBooleanCrossover;
import com.ontology.utilization.domain.genetic.algorithm.environment.BooleanEnvironment;
import com.ontology.utilization.domain.genetic.algorithm.estimate.BooleanBinaryEstimate;
import com.ontology.utilization.domain.genetic.algorithm.exception.NotFoundPatientException;
import com.ontology.utilization.domain.genetic.algorithm.selection.BooleanPopulationSizeSelection;
import com.ontology.utilization.domain.genetic.algorithm.selection.BooleanPostSelection;
import com.ontology.utilization.domain.genetic.algorithm.selection.BooleanRouletteSelection;
import com.ontology.utilization.domain.genetic.algorithm.selection.BooleanSelection;
import com.ontology.utilization.factory.BooleanChromosomeFactory;
import com.ontology.utilization.factory.BooleanPatientFactory;
import com.ontology.utilization.parser.dataset.FileDatasetParser;
import com.ontology.utilization.parser.dataset.model.Dataset;

public class TestForRaport {

	public static void main(String[] args) {

		File f = new File("breast-cancer-wisconsin.data");
		FileDatasetParser fDP = new FileDatasetParser(f);
		Dataset dataset = null;
		try {
			dataset = fDP.getParsedDataset();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		List<BooleanPatient> patients = BooleanPatientFactory.getInstace()
				.producePatient(dataset.getDataset());

		/*** INICJALIZACJA ZMIENNYCH ***/
		
		BooleanEnvironment environment = new BooleanEnvironment(true,100, 50, 30, 5,
				30, 350);
		BooleanBinaryEstimate estimate = new BooleanBinaryEstimate(environment.getIsMalignatRule(),
				patients);
		BooleanSelection selection = new BooleanRouletteSelection();
		BooleanSelection postSelection1 = new BooleanPopulationSizeSelection(
				environment.getSizeOfStandardPopulation());
		BooleanSelection postSelection2 = new BooleanPostSelection(
				environment.getSizeOfStandardPopulation());
		BooleanCrossover crossover = new TwoPointBooleanCrossover(
				environment.getProbabilityOfCrossover(),null);
		/*** KONIEC INICJALIZACJI ZMIENNYCH ***/

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
			}

		}
		/*** KONIEC POPULACJA POCZATKOWA I OCENA JEJ ***/

		// SORTOWANIE POPULACJI
		Collections.sort(initialPopulation);

		// for (BooleanChromosome ch : initialPopulation) {
		// System.out.println(ch.getId() + " - " + ch.getScore());
		// }

		// WYBRANIE NAJLEPSZYCH CHROMOSOMOW - standardowa wielkosc
		List<BooleanChromosome> currentPopulation = postSelection1
				.selection(initialPopulation);

		for (BooleanChromosome ch : currentPopulation) {
			System.out.println(ch.getId() + " - " + ch.getScore());
		}

		Integer i = 0;
		while (environment.isTerminated() == false) {

			List<BooleanChromosome> parents = selection
					.selection(currentPopulation);

			crossover.setProbabilityOfCrossover(environment
					.getProbabilityOfCrossover());
			List<BooleanChromosome> nextGeneration = crossover
					.crossover(parents);

			for (int index = 0; index < nextGeneration.size(); index++) {
				try {
					estimate.estimate(nextGeneration.get(index));
				} catch (NotFoundPatientException e) {
					e.printStackTrace();
				}
			}

			Collections.sort(nextGeneration);

			if (postSelection2 != null) {
				nextGeneration = postSelection2.selection(nextGeneration);
			}

			String test = "test" + environment.getCurrentIteration();
			File fx = new File(test);

			FileWriter fw = null;
			BufferedWriter bW = null;
			try {
				fw = new FileWriter(fx);
				bW = new BufferedWriter(fw);

				for (BooleanChromosome ch : nextGeneration) {
					bW.write(ch.getId() + " - " + ch.getScore() + "\n");
				}
				bW.close();
				fw.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("Stop");
			currentPopulation = nextGeneration;
			environment.computeTerminantion(currentPopulation);
		}
	}
}
