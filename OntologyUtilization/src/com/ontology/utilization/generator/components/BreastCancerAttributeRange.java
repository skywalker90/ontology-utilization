package com.ontology.utilization.generator.components;

import com.ontology.utilization.generator.enums.BreastCancerType;

public class BreastCancerAttributeRange {

	private final int from;
	private final int to;
	private final BreastCancerType cancerType;

	public BreastCancerAttributeRange(int from, int to,
			BreastCancerType cancerType) {
		super();
		this.from = from;
		this.to = to;
		this.cancerType = cancerType;
	}

	public int getFrom() {
		return from;
	}

	public int getTo() {
		return to;
	}

	public BreastCancerType getCancerType() {
		return cancerType;
	}

	public String getRangePrefix() {
		return from + "_" + to;
	}
}
