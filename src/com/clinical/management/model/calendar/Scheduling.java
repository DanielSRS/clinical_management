package com.clinical.management.model.calendar;

import java.util.Calendar;

import com.clinical.management.model.doctor.Doctor;
import com.clinical.management.model.specialty.Specialty;
import com.clinical.management.model.users.User;


/**
 * Classe respons�vel pelo agendamento
 *
 */
public class Scheduling {

	private Calendar day;
	private Calendar hour;
	private Doctor doctor;
	private Integer specialty;
	private OrderStatus status;
	private User patient;
	private Integer id;
	private Integer pacienteID;

	/**
	 * @param day
	 * @param hour
	 * @param doctor
	 * @param specialty
	 * Construtor da classe agendamento
	 */
	public Scheduling(Calendar day, Calendar hour, Doctor doctor, Integer specialty) {

		this.day = day;
		this.hour = hour;
		this.doctor = doctor;
		this.specialty = specialty;
		this.status = OrderStatus.AVAILABLE;
		this.id = null;
	}

	/**
	 * @return day
	 * pega o dia do agendamento
	 */
	public Calendar getDay() {
		return day;
	}

	/**
	 * @param day
	 * seta o dia do agendamento
	 */
	public void setDay(Calendar day) {
		this.day = day;
	}

	/**
	 * @return hour
	 * pega a hora do agendamento
	 */
	public Calendar getHour() {
		return hour;
	}

	/**
	 * @param hour
	 * seta a hora do agendamento
	 */
	public void setHour(Calendar hour) {
		this.hour = hour;
	}

	/**
	 * @return doctor
	 * pega o m�dico do agendamento feito
	 */
	public Doctor getDoctor() {
		return doctor;
	}

	/**
	 * @param doctor
	 * seta o m�dico do agendamento feito
	 */
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	/**
	 * @return specialty
	 * pega a especialidade do m�dico nesse agendamento
	 */
	public Integer getSpecialty() {
		return specialty;
	}

	/**
	 * @param specialty
	 * seta a especialidade do m�dico nesse agendamento
	 */
	public void setSpecialty(Specialty specialty) {
		this.specialty = specialty.getID();
	}

	/**
	 * @return status
	 * pega o status do agendamento
	 */
	public OrderStatus getStatus() {
		return status;
	}

	/**
	 * @return patient
	 * pega o paciente desse agendamento
	 */
	public User getPatient() {
		return patient;
	}

	/**
	 * @param patient
	 * seta o paciente desse agendamento
	 */
	public void setPatient(User patient) {
		this.patient = patient;
	}

	/**
	 * @return id
	 * pega o id desse agendamento
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 * seta o id desse agendamento
	 */
	public void setId(Integer id) {
		this.id = id;
		System.out.println("Id alterado: " + id);
	}

	/**
	 * @param patient
	 * @return boolean
	 * marcar agendamento
	 */
	public boolean mark(User patient) {
		if (this.status == OrderStatus.AVAILABLE) {
			this.patient = patient;
			this.status = OrderStatus.MARKED;
			return true;
		}

		return false;
	}

	/**
	 * @return boolean
	 * bloqueia o agendamento
	 */
	public boolean blocked() {
		if (this.status == OrderStatus.AVAILABLE) {
			this.status = OrderStatus.BLOCKED;
			return true;
		}

		return false;
	}

	/**
	 * @return boolean
	 * desmarcar agendamento
	 */
	public boolean markOff() {
		if (this.status == OrderStatus.MARKED) {
			this.status = OrderStatus.AVAILABLE;
			return true;
		}

		return false;
	}

	public void changeStatus(String novoStatus) {
		if (novoStatus.equals(OrderStatus.AVAILABLE.toString())) {
			this.status = OrderStatus.AVAILABLE;
		}
		if (novoStatus.equals(OrderStatus.BLOCKED.toString())) {
			this.status = OrderStatus.BLOCKED;
		}
		if (novoStatus.equals(OrderStatus.MARKED.toString())) {
			this.status = OrderStatus.MARKED;
		}
	}

	public Integer getpacienteID() {
		return this.pacienteID;
	}

	public void setpacienteID(int id) {
		this.pacienteID = id;
	}

	public String toString() {
		/*String s = "";
		int mes = day.get(Calendar.MONTH) + 1;
		mes = mes + 1;
		int dia = day.get(Calendar.DAY_OF_MONTH);
		String diaS = "";
		String mesS = "";
		if (dia < 10) {
			diaS = "0" + dia;
		} else {
			diaS = dia + "";
		}
		if (mes < 10) {
			mesS = "0" + mes;
		} else {
			mesS = mes + "";
		}
		s = diaS + "/" + mesS + "/" + day.get(Calendar.YEAR);*/

		int h = day.get(Calendar.HOUR_OF_DAY);
		int m = day.get(Calendar.MINUTE);
		String hora = "";
		String minute = "";
		if (h < 10) {
			hora = "0" + h;
		} else {
			hora = h + "";
		}
		if (m < 10) {
			minute = "0" + m;
		} else {
			minute = m + "";
		}

		return hora + ":" + minute;
	}

	public String getDataString() {
		String s = "";
		int mes = day.get(Calendar.MONTH) + 1;
		mes = mes + 1;
		int dia = day.get(Calendar.DAY_OF_MONTH);
		String diaS = "";
		String mesS = "";
		if (dia < 10) {
			diaS = "0" + dia;
		} else {
			diaS = dia + "";
		}
		if (mes < 10) {
			mesS = "0" + mes;
		} else {
			mesS = mes + "";
		}
		s = diaS + "/" + mesS + "/" + day.get(Calendar.YEAR);

		return s;
	}

}
