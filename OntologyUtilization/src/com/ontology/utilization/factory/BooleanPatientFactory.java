package com.ontology.utilization.factory;

import java.util.LinkedList;
import java.util.List;

import com.ontology.utilization.domain.BooleanPatient;
import com.ontology.utilization.domain.Patient;

public class BooleanPatientFactory implements PatientFactory<BooleanPatient> {

	private BooleanPatientFactory() {
	}

	private static BooleanPatientFactory instance;

	public static BooleanPatientFactory getInstace() {
		if (instance == null) {
			synchronized (BooleanPatientFactory.class) {
				if (instance == null) {
					instance = new BooleanPatientFactory();
				}
			}
		}
		return instance;
	}

	@Override
	public BooleanPatient producePatient(String source) {
		BooleanPatient patient = new BooleanPatient();
		String[] patientProperties = source.split(",");

		patient.setId(Integer.parseInt(patientProperties[0]));
		patient.setIsMalignat(isMalignat(patientProperties[10]));

		for (int indexOfProperty = 1, indexOfDescrption = 0; indexOfProperty < 10; indexOfProperty++, indexOfDescrption++) {
			patient.getDescription()
					.get(indexOfDescrption)
					.set(parsePatientPropertyToBooleanTruePlace(patientProperties[indexOfProperty]),
							true);

		}

		return patient;

	}

	private int parsePatientPropertyToBooleanTruePlace(String property) {
		if (property.equals("?") == true) {
			return -1;
		}
		Integer index = Integer.parseInt(property);
		return index - 1;
	}

	private boolean isMalignat(String property) {
		if (property.equals("4") == true) {
			return true;
		}
		return false;
	}

	@Override
	public List<BooleanPatient> producePatient(List<String> sources) {
		List<BooleanPatient> listOfPatients = new LinkedList<BooleanPatient>();
		for (String source : sources) {
			BooleanPatient patient = new BooleanPatient();
			String[] patientProperties = source.split(",");

			patient.setId(Integer.parseInt(patientProperties[0]));
			patient.setIsMalignat(isMalignat(patientProperties[10]));

			try {

				for (int indexOfProperty = 1, indexOfDescrption = 0; indexOfProperty < 10; indexOfProperty++, indexOfDescrption++) {
					// System.out.println(indexOfProperty
					// + " - indexOfProperty|indexOfDescritpion - "
					// + indexOfDescrption);
					int i = parsePatientPropertyToBooleanTruePlace(patientProperties[indexOfProperty]);
					// System.out.println("Indeks wstawiania: " + i);
					patient.getDescription().get(indexOfDescrption)
							.set(i, true);

				}
			} catch (IndexOutOfBoundsException indexOutOfBoundExcept) {
				// System.out.println(indexOutOfBoundExcept);
				// indexOutOfBoundExcept.printStackTrace();
				continue;
			}
			listOfPatients.add(patient);
		}
		return listOfPatients;
	}
}
