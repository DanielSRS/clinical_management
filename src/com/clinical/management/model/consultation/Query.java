package com.clinical.management.model.consultation;

import java.util.Calendar;

import com.clinical.management.model.calendar.Scheduling;

/**
 * Classe responsável pelas consultas
 *
 */
public class Query {

	private Integer scheduling;
	private Integer medicalRecord;
	private Calendar date;
	private Integer user;
	private Integer id;

	/**
	 * @param id
	 * construtor da classe consulta
	 */
	public Query(int id) {
		this.id = id;
	}

	/**
	 * @param scheduling
	 * @param medicalRecord
	 * @param date
	 * @param user
	 */
	public Query(int scheduling, int medicalRecord, Calendar date, int user) {
		this.scheduling = scheduling;
		this.medicalRecord = medicalRecord;
		this.date = date;
		this.user = user;
	}

	public Integer getScheduling() {
		return scheduling;
	}

	/**
	 * @param scheduling
	 * Setar os valores de referência para os atributos
	 */
	public void setScheduling(Scheduling scheduling) {
		this.scheduling = scheduling.getId();
		this.date = scheduling.getDay();
		this.user = scheduling.getPatient().getID();
	}

	/**
	 * @return medicalRecord
	 *  protuário médico
	 */
	public Integer getMedicalRecord() {
		return medicalRecord;
	}

	/**
	 * @param medicalRecord
	 * seta o valor do prontuário médico
	 */
	public void setMedicalRecord(Integer medicalRecord) {
		this.medicalRecord = medicalRecord;
	}

	/**
	 * @return user
	 * pega o usuário
	 */
	public Integer getUser() {
		return user;
	}

	/**
	 * @param user
	 * seta o usuário
	 */
	public void setUser(Integer user) {
		this.user = user;
	}

	/**
	 * @return date
	 * pega a data
	 */
	public Calendar getDate() {
		return date;
	}

	/**
	 * @param date
	 * seta a data
	 */
	public void setDate(Calendar date) {
		this.date = date;
	}

	/**
	 * @return id
	 * pega o id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 * seta o id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

}
