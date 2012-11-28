package com.ontology.utilization.generator.components;


import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.ontology.utilization.domain.BooleanChromosome;
import com.ontology.utilization.domain.genetic.algorithm.handler.AlgorithmHandler;
import com.ontology.utilization.domain.representation.Range;
import com.ontology.utilization.domain.representation.RuleRepresentation;
import com.ontology.utilization.generator.components.BreastCancerAttribute;
import com.ontology.utilization.generator.components.BreastCancerAttributeRange;
import com.ontology.utilization.generator.enums.BreastCancerType;

public class BreastCancerAlgorithmHandler extends AlgorithmHandler {

	private final int minAttributeValue;
	private final int maxAttributeValue;

	public BreastCancerAlgorithmHandler(int minAttributeValue,
			int maxAttributeValue) {
		super();
		this.minAttributeValue = minAttributeValue;
		this.maxAttributeValue = maxAttributeValue;
	}

	@Override
	public void appendStatistic(Double averageFitness, int bestFitness) {
		System.out.println("AverageFitness - " + averageFitness);
		System.out.println("BestFitness - " + bestFitness);
	}

	@Override
	public void appendStateHandler(StateOfAlgorithm state) {
		System.out.println("State: " + state);
	}

	@Override
	public void appendCurrentPopulation(
			List<BooleanChromosome> appendCurrentPopulation) {
		System.out.println("List of the best last ITERATION");
		for (int i = 0; i < appendCurrentPopulation.size(); i++) {
			System.out.println(appendCurrentPopulation.get(i).getId());
		}
		System.out.println("-----------------------");
	}

	@Override
	public void appendBestBooleanChromosome(
			BooleanChromosome bestBooleanChromosome) {
		System.out.println("appendBestRuleRepresentation");
		System.out.println(bestBooleanChromosome);
	}

	/**
	 * @return Returns a list of BreastCancerAttribute.
	 */
	@Override
	public Object getBestBooleanChromosomeRepresentation(
			BooleanChromosome bestBooleanChromosome, boolean malignantRule) {

		BreastCancerType thisRule = null;
		BreastCancerType theOtherRule = null;
		if (malignantRule) {
			thisRule = BreastCancerType.MALIGNANT;
			theOtherRule = BreastCancerType.BENIGN;
		} else {
			thisRule = BreastCancerType.BENIGN;
			theOtherRule = BreastCancerType.MALIGNANT;
		}

		RuleRepresentation ruleRepresentation = bestBooleanChromosome
				.getChromosomeRuleRepresentation();
		Map<Integer, List<Range>> classifiers = ruleRepresentation
				.getClassifiers();

		// assert map has enough keys

		List<BreastCancerAttribute> result = new LinkedList<BreastCancerAttribute>();

		for (int i = 0; i < 9; i++) {
			List<Range> ranges = classifiers.get(i);

			String name = null;
			switch (i) {
			case 0:
				name = "Clump Thickness";
				break;
			case 1:
				name = "Uniformity of Cell Size";
				break;
			case 2:
				name = "Uniformity of Cell Shape";
				break;
			case 3:
				name = "Marginal Adhesion";
				break;
			case 4:
				name = "Single Epithelial Cell Size";
				break;
			case 5:
				name = "Bare Nuclei";
				break;
			case 6:
				name = "Bland Chromatin";
				break;
			case 7:
				name = "Normal Nucleoli";
				break;
			case 8:
				name = "Mitoses";
				break;
			}

			BreastCancerAttribute attribute = new BreastCancerAttribute(name);

			if (ranges == null || ranges.size() == 0) {
				attribute.addRange(new BreastCancerAttributeRange(
						minAttributeValue, maxAttributeValue, theOtherRule));
			} else {

				for (int j = 0; j < ranges.size(); j++) {
					Range ran = ranges.get(j);
					Range r = new Range();
					r.setFrom(ran.getFrom() + 1);
					r.setTo(ran.getTo() + 1);

					if (j == 0) {
						if (r.getFrom() > minAttributeValue) {
							attribute.addRange(new BreastCancerAttributeRange(
									minAttributeValue, r.getFrom() - 1,
									theOtherRule));
						}

						attribute.addRange(new BreastCancerAttributeRange(r
								.getFrom(), r.getTo(), thisRule));

					} else {

						Range previousRange = ranges.get(j - 1);
						if (r.getFrom() > previousRange.getTo() + 1) {
							attribute.addRange(new BreastCancerAttributeRange(
									previousRange.getTo() + 1 + 1,
									r.getFrom() - 1, theOtherRule));
						}

						attribute.addRange(new BreastCancerAttributeRange(r
								.getFrom(), r.getTo(), thisRule));

					}

					if (j == (ranges.size() - 1)) {
						if (r.getTo() < maxAttributeValue) {
							attribute.addRange(new BreastCancerAttributeRange(r
									.getTo() + 1, maxAttributeValue,
									theOtherRule));
						}
					}

				}
			}

			result.add(attribute);

		}

		return result;
	}
}
