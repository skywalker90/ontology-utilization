package com.ontology.utilization.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.hp.hpl.jena.rdf.model.Model;
import com.ontology.utilization.domain.BooleanPatient;
import com.ontology.utilization.domain.genetic.algorithm.BooleanGeneticAlgorithm;
import com.ontology.utilization.domain.genetic.algorithm.crossover.BooleanCrossover;
import com.ontology.utilization.domain.genetic.algorithm.crossover.TwoPointBooleanCrossover;
import com.ontology.utilization.domain.genetic.algorithm.environment.BooleanEnvironment;
import com.ontology.utilization.domain.genetic.algorithm.estimate.BooleanBinaryEstimate;
import com.ontology.utilization.domain.genetic.algorithm.handler.AlgorithmHandler;
import com.ontology.utilization.domain.genetic.algorithm.handler.ShowAlgorithmHandler;
import com.ontology.utilization.domain.genetic.algorithm.mutation.BooleanMutation;
import com.ontology.utilization.domain.genetic.algorithm.mutation.MultiPointBooleanMutation;
import com.ontology.utilization.domain.genetic.algorithm.selection.BooleanRouletteSelection;
import com.ontology.utilization.domain.genetic.algorithm.selection.BooleanSelection;
import com.ontology.utilization.factory.BooleanPatientFactory;
import com.ontology.utilization.generator.OntologyGenerator;
import com.ontology.utilization.generator.components.BreastCancerAttribute;
import com.ontology.utilization.generator.components.BreastCancerAttributeRange;
import com.ontology.utilization.parser.dataset.FileDatasetParser;
import com.ontology.utilization.parser.dataset.model.Dataset;

public class Test {

	public static void main(String[] args) {

		/** OBS�UGA PARSOWANIA PLIKU */

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
		// DO ANALIZOWANIA I �LEDZENIA CO DZIEJE SIE W ALGORYTMIE
		AlgorithmHandler handler = new ShowAlgorithmHandler();

		BooleanEnvironment environment = new BooleanEnvironment(true, 100, 51,
				30, 5, 60, 500);

		BooleanBinaryEstimate estimate = new BooleanBinaryEstimate(
				environment.getIsMalignatRule(), patients);

		BooleanSelection preSelection = new BooleanRouletteSelection();

		BooleanCrossover crossover = new TwoPointBooleanCrossover(
				environment.getProbabilityOfCrossover(), null);
		/*
		 * CZTERY MO�LIWO�CI BooleanCrossover crossover = new
		 * TwoPointBooleanCrossover( environment.getProbabilityOfCrossover(),
		 * null); BooleanCrossover crossover = new TwoPointBooleanCrossover(
		 * environment.getProbabilityOfCrossover(), estimate); BooleanCrossover
		 * crossover = new OnePointBooleanCrossover(
		 * environment.getProbabilityOfCrossover(), null); BooleanCrossover
		 * crossover = new OnePointBooleanCrossover(
		 * environment.getProbabilityOfCrossover(), estimate);
		 */

		BooleanMutation mutation = new MultiPointBooleanMutation(
				environment.getProbabilityOfMutation(), estimate);
		/*
		 * CZTERY MO�LIWO�CI BooleanMutation mutation = new
		 * MultiPointBooleanMutation( environment.getProbabilityOfMutation(),
		 * estimate); BooleanMutation mutation = new MultiPointBooleanMutation(
		 * environment.getProbabilityOfMutation(), null); BooleanMutation
		 * mutation = new OnePointBooleanMutation(
		 * environment.getProbabilityOfMutation(), estimate); BooleanMutation
		 * mutation = new OnePointBooleanMutation(
		 * environment.getProbabilityOfMutation(), null);
		 */
		/*** KONIEC INICJALIZACJI ZMIENNYCH ***/

		BooleanGeneticAlgorithm algorithm = new BooleanGeneticAlgorithm(
				environment, preSelection, crossover, mutation, estimate,
				handler);

		Thread t = new Thread(algorithm);
		t.start();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// METODA DO STOPOWANIA ALGORYTMU
		handler.stopAlgorithm();

		// TODO check cast.
//		@SuppressWarnings("unchecked")
//		List<BreastCancerAttribute> representation = (List<BreastCancerAttribute>) handler
//				.getBestBooleanChromosomeRepresentation(
//						handler.getBestChromosome(),
//						environment.getIsMalignatRule());
//		System.out.println("\n\n=====================\n");
//		for (BreastCancerAttribute att : representation) {
//			for (BreastCancerAttributeRange r : att.getRanges()) {
//				System.out.println(r.getRangePrefix() + "_"
//						+ att.getAttributeName() + " - describes "
//						+ r.getCancerType().getValue());
//			}
//		}
//		
//		System.out.println("\n\n=====================\n");
//		
//		OntologyGenerator generator = new OntologyGenerator();
//		
//		Model model = generator.generateOntology(representation);
//		
//		
//		//System.out.println(model.);
//		model.write(System.out);
	}

}
