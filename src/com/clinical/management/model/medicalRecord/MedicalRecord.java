package com.clinical.management.model.medicalRecord;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.clinical.management.model.patient.Patient;

/**
 * Classe respons�vel pelo prontu�rio m�dico
 *
 */
public class MedicalRecord {
	private Integer patient;
	private String anamnesis;
	private String physicalExams;
	private String hypotheses;
	private String diagnoses;
	private String treatments;
	private Integer id;

	/**
	 * @param patient
	 * @param anamnesis
	 * @param physicalExams
	 * @param hypotheses
	 * @param diagnoses
	 * @param treatments
	 * Construtor da classe prontu�rio m�dico
	 */
	public MedicalRecord(Integer patient, String anamnesis, String physicalExams, String hypotheses, String diagnoses,
			String treatments) {

		this.patient = patient;
		this.anamnesis = anamnesis;
		this.physicalExams = physicalExams;
		this.hypotheses = hypotheses;
		this.diagnoses = diagnoses;
		this.treatments = treatments;
		this.id = null;
	}

	/**
	 * @param patient
	 * @param anamnesis
	 * @param physicalExams
	 * @param hypotheses
	 * @param diagnoses
	 * @param treatments
	 * @param id
	 * Segundo construtor da classe prontu�rio m�dico
	 */
	public MedicalRecord(Integer patient, String anamnesis, String physicalExams, String hypotheses, String diagnoses,
			String treatments, Integer id) {

		this.patient = patient;
		this.anamnesis = anamnesis;
		this.physicalExams = physicalExams;
		this.hypotheses = hypotheses;
		this.diagnoses = diagnoses;
		this.treatments = treatments;
		this.id = id;
	}

	/**
	 * @return patient
	 * pega o paciente
	 */
	public Integer getPatient() {
		return patient;
	}

	/**
	 * @param patient
	 * seta o id do paciente
	 */
	public void setPatient(Patient patient) {
		this.patient = patient.getUser().getID();
	}

	/**
	 * @return anamnesis
	 * pega o anamnese
	 */
	public String getAnamnesis() {
		return anamnesis;
	}

	/**
	 * @param anamnesis
	 * seta o anamnese
	 */
	public void setAnamnesis(String anamnesis) {
		this.anamnesis = anamnesis;
	}

	/**
	 * @return physicalExams
	 * pega os exames f�sicos
	 */
	public String getPhysicalExams() {
		return physicalExams;
	}

	/**
	 * @param physicalExams
	 * seta os exames f�sicos
	 */
	public void setPhysicalExams(String physicalExams) {
		this.physicalExams = physicalExams;
	}

	/**
	 * @return hypotheses
	 * pega as hip�teses
	 */
	public String getHypotheses() {
		return hypotheses;
	}

	/**
	 * @param hypotheses
	 * seta as hip�teses
	 */
	public void setHypotheses(String hypotheses) {
		this.hypotheses = hypotheses;
	}

	/**
	 * @return diagnoses
	 * pega os diagn�sticos
	 */
	public String getDiagnoses() {
		return diagnoses;
	}

	/**
	 * @param diagnoses
	 * seta os diagn�sticos
	 */
	public void setDiagnoses(String diagnoses) {
		this.diagnoses = diagnoses;
	}

	/**
	 * @return treatments
	 * pega os tratamentos
	 */
	public String getTreatments() {
		return treatments;
	}

	/**
	 * @param treatments
	 * seta os tratamentos
	 */
	public void setTreatments(String treatments) {
		this.treatments = treatments;
	}

	/**
	 * @return id
	 * pega o id da classe prontu�rio m�dico
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 * seta o id da classe prontu�rio m�dico
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * esse m�todo cria 1 arquivo txt com os dados do prontu�rio m�dico
	 */
	public void printRecipe() {
		List<String> temps = new ArrayList<>();

		temps.add(this.patient.toString());
		temps.add(this.anamnesis);
		temps.add(diagnoses);
		temps.add(hypotheses);
		temps.add(physicalExams);
		temps.add(treatments);

		String path = "c:\\medicalRecords\\" + this.patient + ".txt";

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
