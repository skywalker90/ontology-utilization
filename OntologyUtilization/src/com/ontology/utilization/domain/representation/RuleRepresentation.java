package com.ontology.utilization.domain.representation;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;



public class RuleRepresentation {

	private Map<Integer, List<Range>> classifiers;

	public RuleRepresentation() {
		super();
		classifiers = new HashMap<Integer, List<Range>>();
		for (int i = 0; i < 9; i++) {
			classifiers.put(i, new LinkedList<Range>());
		}
	}

	public Map<Integer, List<Range>> getClassifiers() {
		return classifiers;
	}

	public void setClassifiers(Map<Integer, List<Range>> classifiers) {
		this.classifiers = classifiers;
	}

	public void addRangeToClassifier(Integer classifierIndex, Range range) {
		List<Range> ranges = classifiers.get(classifierIndex);
		ranges.add(range);
	}

	public String toString() {
		StringBuffer sb = new StringBuffer("RuleRepresentation \n");
		for(int i = 0; i<9; i++){
			List<Range> ranges = classifiers.get(i);
			for(Range range : ranges){
				sb.append(range+", ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}
