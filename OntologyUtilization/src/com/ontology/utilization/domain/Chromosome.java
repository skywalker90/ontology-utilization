package com.ontology.utilization.domain;

import java.io.Serializable;

import com.ontology.utilization.domain.representation.RuleRepresentation;

public abstract class Chromosome<T> implements Serializable, Comparable<T>, Cloneable {

	public abstract RuleRepresentation getChromosomeRuleRepresentation();
	public abstract Integer getScore();
	public abstract String toString();

}
