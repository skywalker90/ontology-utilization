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

public class TestOfEstimate {

	public static void main(String[] args) {

		File f = new File("breast-cancer-wisconsin_test_estimate.data");
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

		BooleanBinaryEstimate estimate = new BooleanBinaryEstimate(false,
				patients);

		List<BooleanChromosome> list = new LinkedList<BooleanChromosome>();
		for (int i = 0; i < 10; i++) {
			list.add(new BooleanChromosome(i));
		}

		for (int i = 0; i < 10; i++) {
			for (int cecha = 0; cecha < 9; cecha++) {
				for (int gen = 0; gen < 10; gen++) {
					if (i == gen) {
						list.get(i).getDescription().get(cecha).set(gen, true);
					}
				}
			}
		}

			list.get(4).getDescription().get(1).set(5, true);

		for (BooleanChromosome b : list) {
			System.out.println(b.getChromosomeRuleRepresentation());
			System.out.println();
		}

		for (BooleanChromosome b : list) {
			try {
				estimate.estimate(b);
			} catch (NotFoundPatientException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		for (BooleanChromosome b : list) {
			System.out.println(b);
			System.out.println();
		}

	}
}
