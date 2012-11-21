package com.ontology.utilization.domain.genetic.algorithm.estimate;

import java.util.List;

import javax.naming.directory.SearchResult;

import com.ontology.utilization.domain.BooleanChromosome;
import com.ontology.utilization.domain.BooleanPatient;
import com.ontology.utilization.domain.genetic.algorithm.exception.NotFoundPatientException;

public class BooleanBinaryEstimate extends BooleanEstimate implements
		Coverable<BooleanChromosome, BooleanPatient> {

	private List<BooleanPatient> patients;
	private Boolean isMalignatRule;

	public BooleanBinaryEstimate(Boolean isMalignatRule,
			List<BooleanPatient> patients) {
		this.isMalignatRule = isMalignatRule;
		this.patients = patients;
	}

	@Override
	public void estimate(BooleanChromosome chromosome)
			throws NotFoundPatientException {

		if (patients == null || patients.isEmpty() == true) {
			throw new NotFoundPatientException(
					"Nie znaleziono pacjentów, którzy s¹ punktem odniesienia dla oceny chromosomu.");
		}

		Integer score = 0;

		for (BooleanPatient bP : patients) {
			if (cover(chromosome, bP) == true) {
				score++;
			}
		}
		chromosome.setScore(score);
	}

	@Override
	public boolean cover(BooleanChromosome chromosome, BooleanPatient patient) {

		if (isMalignatRule == true) {
			if (patient.isMalignat() == true) {
				// isMalignatRule==TRUE AND p.isMalignat=TRUE
				// THE SAME SEARCH RULE AND PAITENT STATUS
				return serachRuleIsTheSameAsPatientHealthStatus(chromosome,
						patient);

			} else {
				// isMalignatRule==TRUE AND p.isMalignat=FALSE
				// NOT! THE SAME SEARCH RULE AND PAITENT STATUS
				return serachRuleIsNotTheSameAsPatientHealthStatus(chromosome,
						patient);
			}

		} else {

			if (patient.isMalignat() == true) {
				// isMalignatRule==FALSE AND p.isMalignat=TRUE
				// NOT! THE SAME SEARCH RULE AND PAITENT STATUS
				return serachRuleIsNotTheSameAsPatientHealthStatus(chromosome,
						patient);
			} else {
				// isMalignatRule==FALSE AND p.isMalignat=FALSE
				// THE SAME SEARCH RULE AND PAITENT STATUS
				return serachRuleIsTheSameAsPatientHealthStatus(chromosome,
						patient);

			}

		}

	}

	private boolean serachRuleIsTheSameAsPatientHealthStatus(
			BooleanChromosome chromosome, BooleanPatient patient) {

		for (int cecha = 0; cecha < 9; cecha++) {
			for (int gen = 0; gen < 10; gen++) {
				if (patient.getDescription().get(cecha).get(gen) == true
						&& chromosome.getDescription().get(cecha).get(gen) == true) {
					break;
				} else if (patient.getDescription().get(cecha).get(gen) == true
						&& chromosome.getDescription().get(cecha).get(gen) == false) {
					return false;
				}
			}
		}
		return true;
	}

	private boolean serachRuleIsNotTheSameAsPatientHealthStatus(
			BooleanChromosome chromosome, BooleanPatient patient) {
		for (int cecha = 0; cecha < 9; cecha++) {
			for (int gen = 0; gen < 10; gen++) {
				if (patient.getDescription().get(cecha).get(gen) == true
						&& chromosome.getDescription().get(cecha).get(gen) == true) {
					return false;
				}
			}
		}

		return true;
	}

	public List<BooleanPatient> getPatients() {
		return patients;
	}

	public void setPatients(List<BooleanPatient> patients) {
		this.patients = patients;
	}

	public Boolean getIsMalignatRule() {
		return isMalignatRule;
	}

	public void setIsMalignatRule(Boolean isMalignatRule) {
		this.isMalignatRule = isMalignatRule;
	}

}
