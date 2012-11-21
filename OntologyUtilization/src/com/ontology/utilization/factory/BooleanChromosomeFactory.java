package com.ontology.utilization.factory;

import java.util.Random;

import com.ontology.utilization.domain.BooleanChromosome;

public class BooleanChromosomeFactory extends
		ChromosomeFactory<BooleanChromosome> {

	private BooleanChromosomeFactory() {
	}

	private static BooleanChromosomeFactory instance;

	public static BooleanChromosomeFactory getInstance() {
		if (instance == null) {
			synchronized (BooleanChromosomeFactory.class) {
				if (instance == null) {
					instance = new BooleanChromosomeFactory();
				}
			}
		}
		return instance;
	}

	private int booleanChromosomeCounter;

	@Override
	public BooleanChromosome createRandomChromosome() {

		BooleanChromosome booleanChromosome = new BooleanChromosome();
		booleanChromosome.setId(booleanChromosomeCounter);
		booleanChromosomeCounter++;
		for (int i = 0; i < 9; i++) {
			Random r = new Random();
			int x = r.nextInt(40) + 2;
			for (int j = 0; j < x; j++) {
				int gen = r.nextInt(10);
				int valueTF = r.nextInt(2);
				if (valueTF == 0) {
					booleanChromosome.setValue(i, gen, true);
				} else {
					booleanChromosome.setValue(i, gen, false);
				}
			}
		}
		return booleanChromosome;
	}

	public int getBooleanChromosomeCounter() {
		return booleanChromosomeCounter;
	}

	public void setBooleanChromosomeCounter(int booleanChromosomeCounter) {
		this.booleanChromosomeCounter = booleanChromosomeCounter;
	}

	public int getNextCount() {
		int returnedCount = booleanChromosomeCounter;
		booleanChromosomeCounter++;
		return returnedCount;

	}

}
