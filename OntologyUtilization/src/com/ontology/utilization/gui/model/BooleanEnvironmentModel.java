package com.ontology.utilization.gui.model;

import java.util.LinkedList;
import java.util.List;

import com.ontology.utilization.domain.BooleanPatient;
import com.ontology.utilization.domain.genetic.algorithm.BooleanGeneticAlgorithm;
import com.ontology.utilization.domain.genetic.algorithm.crossover.BooleanCrossover;
import com.ontology.utilization.domain.genetic.algorithm.crossover.Crossover;
import com.ontology.utilization.domain.genetic.algorithm.crossover.OnePointBooleanCrossover;
import com.ontology.utilization.domain.genetic.algorithm.crossover.TwoPointBooleanCrossover;
import com.ontology.utilization.domain.genetic.algorithm.environment.BooleanEnvironment;
import com.ontology.utilization.domain.genetic.algorithm.estimate.BooleanBinaryEstimate;
import com.ontology.utilization.domain.genetic.algorithm.handler.AlgorithmHandler;
import com.ontology.utilization.domain.genetic.algorithm.mutation.BooleanMutation;
import com.ontology.utilization.domain.genetic.algorithm.mutation.MultiPointBooleanMutation;
import com.ontology.utilization.domain.genetic.algorithm.mutation.Mutation;
import com.ontology.utilization.domain.genetic.algorithm.mutation.OnePointBooleanMutation;
import com.ontology.utilization.domain.genetic.algorithm.selection.BooleanRouletteSelection;
import com.ontology.utilization.domain.genetic.algorithm.selection.BooleanSelection;
import com.ontology.utilization.gui.controller.DefaultController;

public class BooleanEnvironmentModel extends AbstractModel {
	private static final double DEFAULT_THRESHOLD = 0.5;
	
	private List<BooleanPatient> patients;
	private boolean isMalignatRule;
	private int sizeOfInitialPopulation; // zakres od 100 - 1000
	private int sizeOfStandardPopulation; // zakres od 30 - 100
	private int probabilityOfCrossover; // zakres od 0 - 100
	private int probabilityOfMutation; // zakres od 0 - 100
	private int iteration;
	private int threshold; // zakres od 0 - 683

	private int sizeOfInitialPopulationMin;
	private int sizeOfInitialPopulationMax;
	private int sizeOfStandardPopulationMin;
	private int sizeOfStandardPopulationMax;
	private int probabilityOfCrossoverMin;
	private int probabilityOfCrossoverMax;
	private int probabilityOfMutationMin;
	private int probabilityOfMutationMax;
	private int iterationMin;
	private int iterationMax;
	private int thresholdMin;
	private int thresholdMax;

	private String mutationMethod;
	private String crossoverMethod;

	private void fireAll(){
		setSizeOfInitialPopulationMax(-1);
		setSizeOfInitialPopulationMin(-1);
		setSizeOfInitialPopulation(-1);

		setSizeOfStandardPopulationMax(-1);
		setSizeOfStandardPopulationMin(-1);
		setSizeOfStandardPopulation(-1);

		setProbabilityOfCrossoverMax(-1);
		setProbabilityOfCrossoverMin(-1);
		setProbabilityOfCrossover(-1);

		setProbabilityOfMutationMax(-1);
		setProbabilityOfMutationMin(-1);
		setProbabilityOfMutation(-1);

		setThresholdMax(-1);
		setThresholdMin(-1);
		setThreshold(-1);

		setIterationMax(-1);
		setIterationMin(-1);
		setIteration(-1);
	}
	
	public void initDefaults() {
		fireAll();
		
		setMalignatRule(true);

		setSizeOfInitialPopulationMax(1000);
		setSizeOfInitialPopulationMin(100);
		setSizeOfInitialPopulation(100);

		setSizeOfStandardPopulationMax(100);
		setSizeOfStandardPopulationMin(1);
		setSizeOfStandardPopulation(50);

		setProbabilityOfCrossoverMax(100);
		setProbabilityOfCrossoverMin(1);
		setProbabilityOfCrossover(5);

		setProbabilityOfMutationMax(100);
		setProbabilityOfMutationMin(1);
		setProbabilityOfMutation(3);

		setThresholdMax(0);
		setThresholdMin(0);
		setThreshold(0);

		setIterationMax(1000);
		setIterationMin(1);
		setIteration(50);
		setCrossoverMethod(Crossover.ONE_POINT_BOOLEAN_CROSOVER_WITH_ESTIMATE);
		setMutationMethod(Mutation.ONE_POINT_BOOLEAN_MUTATION_WITH_ESTIMATE);
	}

	public boolean isMalignatRule() {
		return isMalignatRule;
	}

	public void setMalignatRule(Boolean isMalignatRule) {
		boolean oldisMalignatRule = this.isMalignatRule;
		this.isMalignatRule = isMalignatRule;
		firePropertyChange(DefaultController.ELEMENT_isMalignatRule_PROPERTY, oldisMalignatRule, isMalignatRule);
	}

	public Integer getSizeOfInitialPopulation() {
		return sizeOfInitialPopulation;
	}

	public void setSizeOfInitialPopulation(Integer sizeOfInitialPopulation) {
		Integer oldsizeOfInitialPopulation = this.sizeOfInitialPopulation;
		sizeOfInitialPopulation = Math.min(getSizeOfInitialPopulationMax(), Math.max(getSizeOfInitialPopulationMin(), sizeOfInitialPopulation));
		this.sizeOfInitialPopulation = sizeOfInitialPopulation;
		firePropertyChange(DefaultController.ELEMENT_sizeOfInitialPopulation_PROPERTY, oldsizeOfInitialPopulation, sizeOfInitialPopulation);
	}

	public Integer getSizeOfStandardPopulation() {
		return sizeOfStandardPopulation;
	}

	public void setSizeOfStandardPopulation(Integer sizeOfStandardPopulation) {
		Integer oldsizeOfStandardPopulation = this.sizeOfStandardPopulation;
		sizeOfStandardPopulation = Math.min(getSizeOfStandardPopulationMax(), Math.max(getSizeOfStandardPopulationMin(), sizeOfStandardPopulation));
		this.sizeOfStandardPopulation = sizeOfStandardPopulation;
		firePropertyChange(DefaultController.ELEMENT_sizeOfStandardPopulation_PROPERTY, oldsizeOfStandardPopulation, sizeOfStandardPopulation);
		
	}

	public Integer getProbabilityOfCrossover() {
		return probabilityOfCrossover;
	}

	public void setProbabilityOfCrossover(Integer probabilityOfCrossover) {
		Integer oldprobabilityOfCrossover = this.probabilityOfCrossover;
		probabilityOfCrossover = Math.min(getProbabilityOfCrossoverMax(), Math.max(getProbabilityOfCrossoverMin(), probabilityOfCrossover));
		this.probabilityOfCrossover = probabilityOfCrossover;
		firePropertyChange(DefaultController.ELEMENT_probabilityOfCrossover_PROPERTY, oldprobabilityOfCrossover, probabilityOfCrossover);
	}

	public Integer getProbabilityOfMutation() {
		return probabilityOfMutation;
	}

	public void setProbabilityOfMutation(Integer probabilityOfMutation) {
		Integer oldprobabilityOfMutation = this.probabilityOfMutation;
		probabilityOfMutation = Math.min(getProbabilityOfMutationMax(), Math.max(getProbabilityOfMutationMin(), probabilityOfMutation));
		this.probabilityOfMutation = probabilityOfMutation;
		firePropertyChange(DefaultController.ELEMENT_probabilityOfMutation_PROPERTY, oldprobabilityOfMutation, probabilityOfMutation);
	}

	public Integer getIteration() {
		return iteration;
	}

	public void setIteration(Integer iteration) {
		Integer olditeration = this.iteration;
		iteration = Math.min(getIterationMax(), Math.max(getIterationMin(), iteration));
		this.iteration = iteration;
		firePropertyChange(DefaultController.ELEMENT_iteration_PROPERTY, olditeration, iteration);
	}

	public Integer getThreshold() {
		return threshold;
	}

	public void setThreshold(Integer threshold) {
		Integer oldthreshold = this.threshold;
		threshold = Math.min(getThresholdMax(), Math.max(getThresholdMin(), threshold));
		this.threshold = threshold;
		firePropertyChange(DefaultController.ELEMENT_threshold_PROPERTY, oldthreshold, threshold);
	}

	public Integer getSizeOfInitialPopulationMin() {
		return sizeOfInitialPopulationMin;
	}

	public void setSizeOfInitialPopulationMin(Integer sizeOfInitialPopulationMin) {
		Integer oldsizeOfInitialPopulationMin = this.sizeOfInitialPopulationMin;
		this.sizeOfInitialPopulationMin = sizeOfInitialPopulationMin;
		firePropertyChange(DefaultController.ELEMENT_sizeOfInitialPopulationMin_PROPERTY, oldsizeOfInitialPopulationMin, sizeOfInitialPopulationMin);
	}

	public Integer getSizeOfInitialPopulationMax() {
		return sizeOfInitialPopulationMax;
	}

	public void setSizeOfInitialPopulationMax(Integer sizeOfInitialPopulationMax) {
		Integer oldsizeOfInitialPopulationMax = this.sizeOfInitialPopulationMax;
		this.sizeOfInitialPopulationMax = sizeOfInitialPopulationMax;
		firePropertyChange(DefaultController.ELEMENT_sizeOfInitialPopulationMax_PROPERTY, oldsizeOfInitialPopulationMax, sizeOfInitialPopulationMax);
	}

	public Integer getSizeOfStandardPopulationMin() {
		return sizeOfStandardPopulationMin;
	}

	public void setSizeOfStandardPopulationMin(Integer sizeOfStandardPopulationMin) {
		Integer oldsizeOfStandardPopulationMin = this.sizeOfStandardPopulationMin;
		this.sizeOfStandardPopulationMin = sizeOfStandardPopulationMin;
		firePropertyChange(DefaultController.ELEMENT_sizeOfStandardPopulationMin_PROPERTY, oldsizeOfStandardPopulationMin, sizeOfStandardPopulationMin);
	}

	public Integer getSizeOfStandardPopulationMax() {
		return sizeOfStandardPopulationMax;
	}

	public void setSizeOfStandardPopulationMax(Integer sizeOfStandardPopulationMax) {
		Integer oldsizeOfStandardPopulationMax = this.sizeOfStandardPopulationMax;
		this.sizeOfStandardPopulationMax = sizeOfStandardPopulationMax;
		firePropertyChange(DefaultController.ELEMENT_sizeOfStandardPopulationMax_PROPERTY, oldsizeOfStandardPopulationMax, sizeOfStandardPopulationMax);
	}

	public Integer getProbabilityOfCrossoverMin() {
		return probabilityOfCrossoverMin;
	}

	public void setProbabilityOfCrossoverMin(Integer probabilityOfCrossoverMin) {
		Integer oldprobabilityOfCrossoverMin = this.probabilityOfCrossoverMin;
		this.probabilityOfCrossoverMin = probabilityOfCrossoverMin;
		firePropertyChange(DefaultController.ELEMENT_probabilityOfCrossoverMin_PROPERTY, oldprobabilityOfCrossoverMin, probabilityOfCrossoverMin);
	}

	public Integer getProbabilityOfCrossoverMax() {
		return probabilityOfCrossoverMax;
	}

	public void setProbabilityOfCrossoverMax(Integer probabilityOfCrossoverMax) {
		Integer oldprobabilityOfCrossoverMax = this.probabilityOfCrossoverMax;
		this.probabilityOfCrossoverMax = probabilityOfCrossoverMax;
		firePropertyChange(DefaultController.ELEMENT_probabilityOfCrossoverMax_PROPERTY, oldprobabilityOfCrossoverMax, probabilityOfCrossoverMax);
	}

	public Integer getProbabilityOfMutationMin() {
		return probabilityOfMutationMin;
	}

	public void setProbabilityOfMutationMin(Integer probabilityOfMutationMin) {
		Integer oldprobabilityOfMutationMin = this.probabilityOfMutationMin;
		this.probabilityOfMutationMin = probabilityOfMutationMin;
		firePropertyChange(DefaultController.ELEMENT_probabilityOfMutationMin_PROPERTY, oldprobabilityOfMutationMin, probabilityOfMutationMin);
	}

	public Integer getProbabilityOfMutationMax() {
		return probabilityOfMutationMax;
	}

	public void setProbabilityOfMutationMax(Integer probabilityOfMutationMax) {
		Integer oldprobabilityOfMutationMax = this.probabilityOfMutationMax;
		this.probabilityOfMutationMax = probabilityOfMutationMax;
		firePropertyChange(DefaultController.ELEMENT_probabilityOfMutationMax_PROPERTY, oldprobabilityOfMutationMax, probabilityOfMutationMax);
	}

	public Integer getIterationMin() {
		return iterationMin;
	}

	public void setIterationMin(Integer iterationMin) {
		Integer olditerationMin = this.iterationMin;
		this.iterationMin = iterationMin;
		firePropertyChange(DefaultController.ELEMENT_iterationMin_PROPERTY, olditerationMin, iterationMin);
	}

	public Integer getIterationMax() {
		return iterationMax;
	}

	public void setIterationMax(Integer iterationMax) {
		Integer olditerationMax = this.iterationMax;
		this.iterationMax = iterationMax;
		firePropertyChange(DefaultController.ELEMENT_iterationMax_PROPERTY, olditerationMax, iterationMax);
	}

	public Integer getThresholdMin() {
		return thresholdMin;
	}

	public void setThresholdMin(Integer thresholdMin) {
		Integer oldthresholdMin = this.thresholdMin;
		this.thresholdMin = thresholdMin;
		firePropertyChange(DefaultController.ELEMENT_thresholdMin_PROPERTY, oldthresholdMin, thresholdMin);
	}

	public Integer getThresholdMax() {
		return thresholdMax;
	}

	public void setThresholdMax(Integer thresholdMax) {
		Integer oldthresholdMax = this.thresholdMax;
		this.thresholdMax = thresholdMax;
		firePropertyChange(DefaultController.ELEMENT_thresholdMax_PROPERTY, oldthresholdMax, thresholdMax);
	}

	public List<BooleanPatient> getPatients() {
		return patients;
	}

	public void setPatients(LinkedList<BooleanPatient> patients) {
		List<BooleanPatient> oldpatients = this.patients;
		if (patients != null) {
			setThresholdMax(patients.size());
			setThreshold((int)(patients.size()*DEFAULT_THRESHOLD));
		}
		this.patients = patients;
		firePropertyChange(DefaultController.ELEMENT_patients_PROPERTY, oldpatients, patients);
	}

	public String getMutationMethod() {
		return mutationMethod;
	}

	public void setMutationMethod(String mutationMethod) {
		String oldmutationMethod = this.mutationMethod;
		this.mutationMethod = mutationMethod;
		firePropertyChange(DefaultController.ELEMENT_mutationMethod_PROPERTY, oldmutationMethod, mutationMethod);
	}

	public String getCrossoverMethod() {
		return crossoverMethod;
	}

	public void setCrossoverMethod(String crossoverMethod) {
		String oldcrossoverMethod = this.crossoverMethod;
		this.crossoverMethod = crossoverMethod;
		firePropertyChange(DefaultController.ELEMENT_crossoverMethod_PROPERTY, oldcrossoverMethod, crossoverMethod);
	}

	private BooleanEnvironment createBooleanEnvironment() {
		return new BooleanEnvironment(isMalignatRule, sizeOfInitialPopulation, sizeOfStandardPopulation, probabilityOfCrossoverMax, probabilityOfMutation, iteration, threshold);
	}

	public BooleanGeneticAlgorithm createBooleanGeneticAlgorithm(AlgorithmHandler handler) {
		BooleanEnvironment environment = createBooleanEnvironment();
		BooleanBinaryEstimate estimate = new BooleanBinaryEstimate(environment.getIsMalignatRule(), patients);
		BooleanSelection preSelection = new BooleanRouletteSelection();

		BooleanCrossover crossover = null;
		if (crossoverMethod.equals(Crossover.ONE_POINT_BOOLEAN_CROSOVER_WITH_ESTIMATE)) {
			crossover = new OnePointBooleanCrossover(environment.getProbabilityOfMutation(), estimate);
		} else if (crossoverMethod.equals(Crossover.TWO_POINT_BOOLEAN_CROSOVER_WITH_ESTIMATE)) {
			crossover = new TwoPointBooleanCrossover(environment.getProbabilityOfMutation(), estimate);
		} else if (crossoverMethod.equals(Crossover.ONE_POINT_BOOLEAN_CROSOVER_WITHOUT_ESTIMATE)) {
			crossover = new OnePointBooleanCrossover(environment.getProbabilityOfMutation(), null);
		} else if (crossoverMethod.equals(Crossover.TWO_POINT_BOOLEAN_CROSOVER_WITHOUT_ESTIMATE)) {
			crossover = new TwoPointBooleanCrossover(environment.getProbabilityOfMutation(), null);
		}

		BooleanMutation mutation = null;
		if (mutationMethod.equals(Mutation.ONE_POINT_BOOLEAN_MUTATION_WITH_ESTIMATE)) {
			mutation = new OnePointBooleanMutation(environment.getProbabilityOfMutation(), estimate);
		} else if (mutationMethod.equals(Mutation.MULTI_POINT_BOOLEAN_MUTATION_WITH_ESTIMATE)) {
			mutation = new MultiPointBooleanMutation(environment.getProbabilityOfMutation(), estimate);
		} else if (mutationMethod.equals(Mutation.ONE_POINT_BOOLEAN_MUTATION_WITHOUT_ESTIMATE)) {
			mutation = new OnePointBooleanMutation(environment.getProbabilityOfMutation(), null);
		} else if (mutationMethod.equals(Mutation.MULTI_POINT_BOOLEAN_MUTATION_WITHOUT_ESTIMATE)) {
			mutation = new MultiPointBooleanMutation(environment.getProbabilityOfMutation(), null);
		}

		return new BooleanGeneticAlgorithm(environment, preSelection, crossover, mutation, estimate, handler);
	}

	@Override
	public String toString() {
		return "BooleanEnvironmentModel [patients=" + patients + ", isMalignatRule=" + isMalignatRule + ", sizeOfInitialPopulation=" + sizeOfInitialPopulation + ", sizeOfStandardPopulation="
				+ sizeOfStandardPopulation + ", probabilityOfCrossover=" + probabilityOfCrossover + ", probabilityOfMutation=" + probabilityOfMutation + ", iteration=" + iteration + ", threshold="
				+ threshold + ", sizeOfInitialPopulationMin=" + sizeOfInitialPopulationMin + ", sizeOfInitialPopulationMax=" + sizeOfInitialPopulationMax + ", sizeOfStandardPopulationMin="
				+ sizeOfStandardPopulationMin + ", sizeOfStandardPopulationMax=" + sizeOfStandardPopulationMax + ", probabilityOfCrossoverMin=" + probabilityOfCrossoverMin
				+ ", probabilityOfCrossoverMax=" + probabilityOfCrossoverMax + ", probabilityOfMutationMin=" + probabilityOfMutationMin + ", probabilityOfMutationMax=" + probabilityOfMutationMax
				+ ", iterationMin=" + iterationMin + ", iterationMax=" + iterationMax + ", thresholdMin=" + thresholdMin + ", thresholdMax=" + thresholdMax + ", mutationMethod=" + mutationMethod
				+ ", crossoverMethod=" + crossoverMethod + "]";
	}

}
