package com.ontology.utilization.domain;

import java.util.LinkedList;
import java.util.List;

import com.ontology.utilization.domain.representation.Range;
import com.ontology.utilization.domain.representation.RuleRepresentation;
import com.ontology.utilization.factory.BooleanChromosomeFactory;

public class BooleanChromosome extends Chromosome<BooleanChromosome> {

	private int id;

	private List<List<Boolean>> description;

	private Integer score;

	public BooleanChromosome() {
		super();
		if (score == null) {
			score = 0;
		}
		description = new LinkedList<List<Boolean>>();
		for (int i = 0; i < 9; i++) {
			description.add(new LinkedList<Boolean>());
		}

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 10; j++) {
				description.get(i).add(false);
			}
		}
	}

	@Override
	public Object clone() throws CloneNotSupportedException {

		BooleanChromosome bCH = new BooleanChromosome(BooleanChromosomeFactory
				.getInstance().getNextCount());
		List<List<Boolean>> bCHDescription = new LinkedList<List<Boolean>>();

		bCHDescription = new LinkedList<List<Boolean>>();
		for (int i = 0; i < 9; i++) {
			bCHDescription.add(new LinkedList<Boolean>());
		}

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 10; j++) {
				bCHDescription.get(i).add(false);
			}
		}

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 10; j++) {
				bCHDescription.get(i).set(j, description.get(i).get(j));
			}
		}

		bCH.setDescription(bCHDescription);

		return bCH;
	}

	public BooleanChromosome(Integer id) {
		this();
		this.id = id;
	}

	public void setValue(Integer indexClassifier, Integer index, Boolean value) {
		description.get(indexClassifier).set(index, value);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<List<Boolean>> getDescription() {
		return description;
	}

	public void setDescription(List<List<Boolean>> description) {
		this.description = description;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	@Override
	public RuleRepresentation getChromosomeRuleRepresentation() {
		RuleRepresentation ruleRepresentation = new RuleRepresentation();
		for (int i = 0; i < 9; i++) {

			int start_x = 0;
			int end_x = 0;

			boolean isPattern = description.get(i).get(start_x);
			boolean next = false;
			Range range = null;
			if (isPattern == true) {
				range = new Range();
			}

			for (int y = 1; y < 10; y++) {

				next = description.get(i).get(y);

				if (next == true && isPattern == true) {
					end_x = y;
				} else if (next == true && isPattern == false) {
					range = new Range();
					start_x = y;
					end_x = y;
					isPattern = true;
				} else if (next == false && isPattern == true) {
					range.setFrom(start_x);
					range.setTo(end_x);
					ruleRepresentation.addRangeToClassifier(i, range);
					range = null;
					isPattern = false;
				}
			}

			if (next = true && isPattern == true) {
				range.setFrom(start_x);
				range.setTo(end_x);
				ruleRepresentation.addRangeToClassifier(i, range);
			}

		}
		return ruleRepresentation;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer("BooleanChromosome, id - " + id
				+ ", score: " + score + "\n");
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 10; j++) {
				sb.append(description.get(i).get(j) + ", ");
			}
			sb.append("\n");
		}

		return sb.toString();
	}

	@Override
	public int compareTo(BooleanChromosome chromosome) {
		return chromosome.getScore().compareTo(this.getScore());
	}
}
