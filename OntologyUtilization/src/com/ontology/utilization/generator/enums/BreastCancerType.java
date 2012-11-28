package com.ontology.utilization.generator.enums;

public enum BreastCancerType {

	BENIGN("Benign"), MALIGNANT("Malignant");

	private final String value;

	BreastCancerType(String v) {
		value = v;
	}

	public String getValue() {
		return value;
	}
}
