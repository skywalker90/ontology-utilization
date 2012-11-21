package com.ontology.utilization.domain.genetic.algorithm.estimate;

public interface Coverable<V, X> {

	public boolean cover(V chromosome, X patient);

}
