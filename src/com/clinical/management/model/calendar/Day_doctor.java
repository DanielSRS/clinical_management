package com.clinical.management.model.calendar;

import java.util.Calendar;

import com.clinical.management.model.doctor.Doctor;

/**
 * Classe responsável pelo dia do médico
 *
 */
public class Day_doctor {
	private Calendar Start_service, End_service;
	private Doctor doctor;
	private Integer duration_service;
	private Integer id;

	/**
	 * @param start_service
	 * @param end_service
	 * @param doctor
	 * @param duration_service
	 * Construtor da classe dia do médico
	 */
	public Day_doctor(int start_service, int end_service, Doctor doctor, Integer duration_service) {
		this.doctor = doctor;
		this.duration_service = duration_service;
		Calendar d = Calendar.getInstance();
		d.set(2000, Calendar.DECEMBER, 01, start_service, 0);
		this.Start_service = d;
		Calendar b = Calendar.getInstance();
		b.set(2000, Calendar.DECEMBER, 01, end_service, 0);
		this.End_service = b;
		this.id = null;

	}

	/**
	 * @return Start_service
	 * pega o início do serviço do médico
	 */
	public Calendar getStart_service() {
		return Start_service;
	}

	/**
	 * @param start_service
	 * seta o início do serviço do médico
	 */
	public void setStart_service(Calendar start_service) {
		Start_service = start_service;
	}

	/**
	 * @return End_service
	 * pega o fim do serviço do médico
	 */
	public Calendar getEnd_service() {
		return End_service;
	}

	/**
	 * @param end_service
	 * pega o fim do serviço do médico
	 */
	public void setEnd_service(Calendar end_service) {
		End_service = end_service;
	}

	/**
	 * @return doctor
	 * pega o médico que se refere
	 */
	public Doctor getDoctor() {
		return doctor;
	}

	/**
	 * @param doctor
	 * seta o médico que se refere
	 */
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	/**
	 * @return duration_service
	 * pega a duração do serviço
	 */
	public Integer getDuration_service() {
		return duration_service;
	}

	/**
	 * @param duration_service
	 * seta a duração do serviço
	 */
	public void setDuration_service(Integer duration_service) {
		this.duration_service = duration_service;
	}

	/**
	 * @return id
	 * pega o id do dia do médico
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 * seta o id do dia do médico
	 */
	public void setId(Integer id) {
		this.id = id;
	}

}
