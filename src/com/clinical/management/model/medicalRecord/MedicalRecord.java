package com.clinical.management.model.medicalRecord;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.clinical.management.model.patient.Patient;

public class MedicalRecord {
	private Patient patient;
	private String anamnesis;
	private String physicalExams;
	private String hypotheses;
	private String diagnoses;
	private String treatments;
	private Integer id;

	public MedicalRecord(Patient patient, String anamnesis, String physicalExams, String hypotheses, String diagnoses,
			String treatments) {

		this.patient = patient;
		this.anamnesis = anamnesis;
		this.physicalExams = physicalExams;
		this.hypotheses = hypotheses;
		this.diagnoses = diagnoses;
		this.treatments = treatments;
		this.id = null;
	}

	public MedicalRecord(Patient patient, String anamnesis, String physicalExams, String hypotheses, String diagnoses,
			String treatments, Integer id) {

		this.patient = patient;
		this.anamnesis = anamnesis;
		this.physicalExams = physicalExams;
		this.hypotheses = hypotheses;
		this.diagnoses = diagnoses;
		this.treatments = treatments;
		this.id = id;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public String getAnamnesis() {
		return anamnesis;
	}

	public void setAnamnesis(String anamnesis) {
		this.anamnesis = anamnesis;
	}

	public String getPhysicalExams() {
		return physicalExams;
	}

	public void setPhysicalExams(String physicalExams) {
		this.physicalExams = physicalExams;
	}

	public String getHypotheses() {
		return hypotheses;
	}

	public void setHypotheses(String hypotheses) {
		this.hypotheses = hypotheses;
	}

	public String getDiagnoses() {
		return diagnoses;
	}

	public void setDiagnoses(String diagnoses) {
		this.diagnoses = diagnoses;
	}

	public String getTreatments() {
		return treatments;
	}

	public void setTreatments(String treatments) {
		this.treatments = treatments;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void printRecipe() {
		List<String> temps = new ArrayList<>();

		temps.add(this.patient.getUser().getName());
		temps.add(this.patient.getUser().getCpf());
		temps.add(this.anamnesis);
		temps.add(diagnoses);
		temps.add(hypotheses);
		temps.add(physicalExams);
		temps.add(treatments);

		String path = "c:\\medicalRecords\\" + this.patient.getUser().getName() + ".txt";

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
			for (String temp : temps) {
				bw.write(temp);
				bw.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
