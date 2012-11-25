package com.ontology.utilization.gui.controller;

import java.util.List;

import com.ontology.utilization.domain.BooleanPatient;

public class DefaultController extends AbstractController {
	public final static String ELEMENT_patients_PROPERTY = "Patients";
	public final static String ELEMENT_isMalignatRule_PROPERTY = "MalignatRule";
	public final static String ELEMENT_sizeOfInitialPopulation_PROPERTY = "SizeOfInitialPopulation";
	public final static String ELEMENT_sizeOfInitialPopulationMin_PROPERTY = "SizeOfInitialPopulationMin";
	public final static String ELEMENT_sizeOfInitialPopulationMax_PROPERTY = "SizeOfInitialPopulationMax";
	public final static String ELEMENT_sizeOfStandardPopulation_PROPERTY = "SizeOfStandardPopulation";
	public final static String ELEMENT_sizeOfStandardPopulationMin_PROPERTY = "SizeOfStandardPopulationMin";
	public final static String ELEMENT_sizeOfStandardPopulationMax_PROPERTY = "SizeOfStandardPopulationMax";
	public final static String ELEMENT_probabilityOfCrossover_PROPERTY = "ProbabilityOfCrossover";
	public final static String ELEMENT_probabilityOfCrossoverMin_PROPERTY = "ProbabilityOfCrossoverMin";
	public final static String ELEMENT_probabilityOfCrossoverMax_PROPERTY = "ProbabilityOfCrossoverMax";
	public final static String ELEMENT_probabilityOfMutation_PROPERTY = "ProbabilityOfMutation";
	public final static String ELEMENT_probabilityOfMutationMin_PROPERTY = "ProbabilityOfMutationMin";
	public final static String ELEMENT_probabilityOfMutationMax_PROPERTY = "ProbabilityOfMutationMax";
	public final static String ELEMENT_threshold_PROPERTY = "Threshold";
	public final static String ELEMENT_thresholdMin_PROPERTY = "ThresholdMin";
	public final static String ELEMENT_thresholdMax_PROPERTY = "ThresholdMax";
	public final static String ELEMENT_crossoverMethod_PROPERTY = "CrossoverMethod";
	public final static String ELEMENT_mutationMethod_PROPERTY = "MutationMethod";
	public static final String ELEMENT_iteration_PROPERTY = "Iteration";
	public static final String ELEMENT_iterationMin_PROPERTY = "IterationMin";
	public static final String ELEMENT_iterationMax_PROPERTY = "IterationMax";

	//  Code omitted

	public void changeElementIsMalignatRule(boolean newIsMalignatRule) {
		setModelProperty(ELEMENT_isMalignatRule_PROPERTY, newIsMalignatRule);
	}

	public void changeElementSizeOfInitialPopulation(int newSizeOfInitialPopulation) {
		setModelProperty(ELEMENT_sizeOfInitialPopulation_PROPERTY, newSizeOfInitialPopulation);
	}

	public void changeElementSizeOfInitialPopulationMin(int newSizeOfInitialPopulationMin) {
		setModelProperty(ELEMENT_sizeOfInitialPopulationMin_PROPERTY, newSizeOfInitialPopulationMin);
	}

	public void changeElementSizeOfInitialPopulationMax(int newSizeOfInitialPopulationMax) {
		setModelProperty(ELEMENT_sizeOfInitialPopulationMax_PROPERTY, newSizeOfInitialPopulationMax);
	}

	public void changeElementSizeOfStandardPopulation(int newSizeOfStandardPopulation) {
		setModelProperty(ELEMENT_sizeOfStandardPopulation_PROPERTY, newSizeOfStandardPopulation);
	}

	public void changeElementSizeOfStandardPopulationMin(int newSizeOfStandardPopulationMin) {
		setModelProperty(ELEMENT_sizeOfStandardPopulationMin_PROPERTY, newSizeOfStandardPopulationMin);
	}

	public void changeElementSizeOfStandardPopulationMax(int newSizeOfStandardPopulationMax) {
		setModelProperty(ELEMENT_sizeOfStandardPopulationMax_PROPERTY, newSizeOfStandardPopulationMax);
	}

	public void changeElementProbabilityOfCrossover(int newProbabilityOfCrossover) {
		setModelProperty(ELEMENT_probabilityOfCrossover_PROPERTY, newProbabilityOfCrossover);
	}

	public void changeElementProbabilityOfCrossoverMin(int newProbabilityOfCrossoverMin) {
		setModelProperty(ELEMENT_probabilityOfCrossoverMin_PROPERTY, newProbabilityOfCrossoverMin);
	}

	public void changeElementProbabilityOfCrossoverMax(int newProbabilityOfCrossoverMax) {
		setModelProperty(ELEMENT_probabilityOfCrossoverMax_PROPERTY, newProbabilityOfCrossoverMax);
	}

	public void changeElementProbabilityOfMutation(int newProbabilityOfMutation) {
		setModelProperty(ELEMENT_probabilityOfMutation_PROPERTY, newProbabilityOfMutation);
	}

	public void changeElementProbabilityOfMutationMin(int newProbabilityOfMutationMin) {
		setModelProperty(ELEMENT_probabilityOfMutationMin_PROPERTY, newProbabilityOfMutationMin);
	}

	public void changeElementProbabilityOfMutationMax(int newProbabilityOfMutationMax) {
		setModelProperty(ELEMENT_probabilityOfMutationMax_PROPERTY, newProbabilityOfMutationMax);
	}

	public void changeElementThreshold(int newThreshold) {
		setModelProperty(ELEMENT_threshold_PROPERTY, newThreshold);
	}

	public void changeElementThresholdMin(int newThresholdMin) {
		setModelProperty(ELEMENT_thresholdMin_PROPERTY, newThresholdMin);
	}

	public void changeElementThresholdMax(int newThresholdMax) {
		setModelProperty(ELEMENT_thresholdMax_PROPERTY, newThresholdMax);
	}

	public void changeElementPatients(List<BooleanPatient> newPatients) {
		setModelProperty(ELEMENT_patients_PROPERTY, newPatients);
	}

	public void changeElementCrossoverMethod(String newCrossoverMethod) {
		setModelProperty(ELEMENT_crossoverMethod_PROPERTY, newCrossoverMethod);
	}

	public void changeElementMutationMethod(String newMutationMethod) {
		setModelProperty(ELEMENT_mutationMethod_PROPERTY, newMutationMethod);
	}

	public void changeElementIteration(int newIteration) {
		setModelProperty(ELEMENT_iteration_PROPERTY, newIteration);
	}

	public void changeElementIterationMin(int newIterationMin) {
		setModelProperty(ELEMENT_iterationMin_PROPERTY, newIterationMin);
	}

	public void changeElementIterationMax(int newIterationMax) {
		setModelProperty(ELEMENT_iterationMax_PROPERTY, newIterationMax);
	}
}