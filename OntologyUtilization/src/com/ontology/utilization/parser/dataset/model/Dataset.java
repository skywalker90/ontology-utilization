package com.ontology.utilization.parser.dataset.model;

import java.util.List;

public class Dataset {

	private List<String> dataset;

	public List<String> getDataset() {
		return dataset;
	}

	public void setDataset(List<String> dataset) {
		this.dataset = dataset;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("Dataset \n");
		for (String line : dataset) {
			sb.append(line + "\n");
		}
		return sb.toString();
	}

}
