package com.ontology.utilization.factory;

import java.util.List;

import com.ontology.utilization.domain.Patient;

public interface PatientFactory<T> {

	public T producePatient(String source);
	public List<T> producePatient(List<String> source);
	
}
