package com.ontology.utilization.domain;

import java.util.LinkedList;
import java.util.List;

public class BooleanPatient extends Patient {

	private int id;

	private List<List<Boolean>> description;

	private Boolean isMalignat;

	public BooleanPatient() {
		super();
		description = new LinkedList<List<Boolean>>();
		for (int i = 0; i < 9; i++) {
			description.add(new LinkedList<Boolean>());
		}
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 10; j++) {
				description.get(i).add(false);
			}
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Boolean getIsMalignat() {
		return isMalignat;
	}

	public void setIsMalignat(Boolean isMalignat) {
		this.isMalignat = isMalignat;
	}

	public Boolean isMalignat() {
		return isMalignat;
	}

	public void setMalignat(Boolean isMalignat) {
		this.isMalignat = isMalignat;
	}

	public List<List<Boolean>> getDescription() {
		return description;
	}

	public void setDescription(List<List<Boolean>> description) {
		this.description = description;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("IntegerBinaryPatient, id - " + id
				+ "\n");
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 10; j++) {
				sb.append(description.get(i).get(j) + ", ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}
