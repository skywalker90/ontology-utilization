package com.ontology.utilization.generator.components;

import java.util.LinkedList;
import java.util.List;

public class BreastCancerAttribute {

	private final String attributeName;
	private final List<BreastCancerAttributeRange> ranges;

	public BreastCancerAttribute(String name) {
		attributeName = name;
		ranges = new LinkedList<BreastCancerAttributeRange>();
	}

	public List<BreastCancerAttributeRange> getRanges() {
		return ranges;
	}

	public void addRange(BreastCancerAttributeRange range) {
		ranges.add(range);
	}

	public String getAttributeName() {
		return attributeName;
	}

}
