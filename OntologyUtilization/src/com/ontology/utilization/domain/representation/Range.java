package com.ontology.utilization.domain.representation;

public class Range {

	private int form;
	private int to;

	public Range() {
	}

	public int getForm() {
		return form;
	}

	public void setForm(int form) {
		this.form = form;
	}

	public int getTo() {
		return to;
	}

	public void setTo(int to) {
		this.to = to;
	}

	@Override
	public String toString() {
		return "r - from: " + form + " to: " + to;
	}

}
