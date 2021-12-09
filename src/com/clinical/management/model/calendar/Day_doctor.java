package com.clinical.management.model.calendar;

import java.util.Calendar;

import com.clinical.management.model.doctor.Doctor;

/**
 * Classe respons�vel pelo dia do m�dico
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
	 * Construtor da classe dia do m�dico
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
	 * pega o in�cio do servi�o do m�dico
	 */
	public Calendar getStart_service() {
		return Start_service;
	}

	/**
	 * @param start_service
	 * seta o in�cio do servi�o do m�dico
	 */
	public void setStart_service(Calendar start_service) {
		Start_service = start_service;
	}

	/**
	 * @return End_service
	 * pega o fim do servi�o do m�dico
	 */
	public Calendar getEnd_service() {
		return End_service;
	}

	/**
	 * @param end_service
	 * pega o fim do servi�o do m�dico
	 */
	public void setEnd_service(Calendar end_service) {
		End_service = end_service;
	}

	/**
	 * @return doctor
	 * pega o m�dico que se refere
	 */
	public Doctor getDoctor() {
		return doctor;
	}

	/**
	 * @param doctor
	 * seta o m�dico que se refere
	 */
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	/**
	 * @return duration_service
	 * pega a dura��o do servi�o
	 */
	public Integer getDuration_service() {
		return duration_service;
	}

	/**
	 * @param duration_service
	 * seta a dura��o do servi�o
	 */
	public void setDuration_service(Integer duration_service) {
		this.duration_service = duration_service;
	}

	/**
	 * @return id
	 * pega o id do dia do m�dico
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 * seta o id do dia do m�dico
	 */
	public void setId(Integer id) {
		this.id = id;
	}

}
