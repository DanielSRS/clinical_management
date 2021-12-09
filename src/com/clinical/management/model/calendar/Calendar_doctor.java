package com.clinical.management.model.calendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.clinical.management.model.doctor.Doctor;

/**
 * Classe respons�vel pela agenda do m�dico
 *
 */
public class Calendar_doctor {

	private Day_doctor sunday;
	private Day_doctor monday;
	private Day_doctor tuesday;
	private Day_doctor wednesday;
	private Day_doctor thursday;
	private Day_doctor friday;
	private Day_doctor saturday;
	private Integer id;
	private Integer doctor;

	/**
	 * Construtor da agenda do m�dico
	 */
	public Calendar_doctor() {
		this.id = null;
	}

	/**
	 * @return sunday
	 * pega o domingo no dia do doutor
	 */
	public Day_doctor getSunday() {
		return sunday;
	}

	/**
	 * @param sunday
	 * seta o domingo no dia do doutor
	 */
	public void setSunday(Day_doctor sunday) {
		this.sunday = sunday;
	}

	/**
	 * @return monday
	 * pega a segunda no dia do doutor
	 */
	public Day_doctor getMonday() {
		return monday;
	}

	/**
	 * @param monday
	 * seta a segunda no dia do doutor
	 */
	public void setMonday(Day_doctor monday) {
		this.monday = monday;
	}

	/**
	 * @return tuesday
	 * pega a ter�a no dia do doutor
	 */
	public Day_doctor getTuesday() {
		return tuesday;
	}

	/**
	 * @param tuesday
	 * seta a ter�a no dia do doutor
	 */
	public void setTuesday(Day_doctor tuesday) {
		this.tuesday = tuesday;
	}

	/**
	 * @return wednesday
	 * pega a quarta no dia do doutor
	 */
	public Day_doctor getWednesday() {
		return wednesday;
	}

	/**
	 * @param wednesday
	 * seta a quarta no dia do doutor
	 */
	public void setWednesday(Day_doctor wednesday) {
		this.wednesday = wednesday;
	}

	/**
	 * @return thursday
	 * pega a quinta no dia do doutor
	 */
	public Day_doctor getThursday() {
		return thursday;
	}

	/**
	 * @param thursday
	 * seta a quinta no dia do doutor
	 */
	public void setThursday(Day_doctor thursday) {
		this.thursday = thursday;
	}

	/**
	 * @return friday
	 * pega a sexta no dia do doutor
	 */
	public Day_doctor getFriday() {
		return friday;
	}

	/**
	 * @param friday
	 * seta a sexta no dia do doutor
	 */
	public void setFriday(Day_doctor friday) {
		this.friday = friday;
	}

	/**
	 * @return saturday
	 * pega o s�bado no dia do doutor
	 */
	public Day_doctor getSaturday() {
		return saturday;
	}

	/**
	 * @param saturday
	 * seta o s�bado no dia do doutor
	 */
	public void setSaturday(Day_doctor saturday) {
		this.saturday = saturday;
	}

	/**
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	

	/**
	 * @return doctor
	 */
	public Integer getDoctor() {
		return doctor;
	}

	/**
	 * @param doctor
	 */
	public void setDoctor(Integer doctor) {
		this.doctor = doctor;
	}

	/**
	 * @return mark
	 * retorna uma lista de agedamento dos dias da semana
	 */
	public List<Scheduling> generateCalendarDoctor() {
		List<Scheduling> mark = new ArrayList<>();
		if (this.sunday != null) {
			long timeAvailable = (this.sunday.getEnd_service().getTimeInMillis()
					- this.sunday.getStart_service().getTimeInMillis()) / 1000 * 60;
			Integer duration = (int) (timeAvailable / this.sunday.getDuration_service());
			for (int i = 0; i < duration; i++) {
				Calendar data = Calendar.getInstance();
				int daysInCurrentMonth = java.time.LocalDate.now().lengthOfMonth();
				for (int j = 1; j < daysInCurrentMonth; j++) {
					data.set(Calendar.DAY_OF_MONTH, j);
					int diaDaSemana = data.get(Calendar.DAY_OF_WEEK);
					if (diaDaSemana == 1) {
						Calendar day_final = Calendar.getInstance();
						day_final.set(Calendar.DAY_OF_MONTH, j);
						day_final.set(Calendar.HOUR_OF_DAY, this.sunday.getStart_service().get(Calendar.HOUR_OF_DAY)
								+ i * this.sunday.getDuration_service());
						mark.add(new Scheduling(day_final, day_final, this.sunday.getDoctor(),
								this.sunday.getDoctor().getSpecialty()));
					}
				}
			}
		}
		if (this.monday != null) {
			long timeAvailable = (this.monday.getEnd_service().getTimeInMillis()
					- this.monday.getStart_service().getTimeInMillis()) / 1000 * 60;
			Integer duration = (int) (timeAvailable / this.monday.getDuration_service());
			for (int i = 0; i < duration; i++) {
				Calendar data = Calendar.getInstance();
				int daysInCurrentMonth = java.time.LocalDate.now().lengthOfMonth();
				for (int j = 1; j < daysInCurrentMonth; j++) {
					data.set(Calendar.DAY_OF_MONTH, j);
					int diaDaSemana = data.get(Calendar.DAY_OF_WEEK);
					if (diaDaSemana == 2) {
						Calendar day_final = Calendar.getInstance();
						day_final.set(Calendar.DAY_OF_MONTH, j);
						day_final.set(Calendar.HOUR_OF_DAY, this.monday.getStart_service().get(Calendar.HOUR_OF_DAY)
								+ i * this.monday.getDuration_service());
						mark.add(new Scheduling(day_final, day_final, this.monday.getDoctor(),
								this.monday.getDoctor().getSpecialty()));
					}
				}
			}
		}
		if (this.tuesday != null) {
			long timeAvailable = (this.tuesday.getEnd_service().getTimeInMillis()
					- this.tuesday.getStart_service().getTimeInMillis()) / 1000 * 60;
			Integer duration = (int) (timeAvailable / this.tuesday.getDuration_service());
			for (int i = 0; i < duration; i++) {
				Calendar data = Calendar.getInstance();
				int daysInCurrentMonth = java.time.LocalDate.now().lengthOfMonth();
				for (int j = 1; j < daysInCurrentMonth; j++) {
					data.set(Calendar.DAY_OF_MONTH, j);
					int diaDaSemana = data.get(Calendar.DAY_OF_WEEK);
					if (diaDaSemana == 3) {
						Calendar day_final = Calendar.getInstance();
						day_final.set(Calendar.DAY_OF_MONTH, j);
						day_final.set(Calendar.HOUR_OF_DAY, this.tuesday.getStart_service().get(Calendar.HOUR_OF_DAY)
								+ i * this.tuesday.getDuration_service());
						mark.add(new Scheduling(day_final, day_final, this.tuesday.getDoctor(),
								this.tuesday.getDoctor().getSpecialty()));
					}
				}
			}
		}
		if (this.wednesday != null) {
			long timeAvailable = (this.wednesday.getEnd_service().getTimeInMillis()
					- this.wednesday.getStart_service().getTimeInMillis()) / 1000 * 60;
			Integer duration = (int) (timeAvailable / this.wednesday.getDuration_service());
			for (int i = 0; i < duration; i++) {
				Calendar data = Calendar.getInstance();
				int daysInCurrentMonth = java.time.LocalDate.now().lengthOfMonth();
				for (int j = 1; j < daysInCurrentMonth; j++) {
					data.set(Calendar.DAY_OF_MONTH, j);
					int diaDaSemana = data.get(Calendar.DAY_OF_WEEK);
					if (diaDaSemana == 4) {
						Calendar day_final = Calendar.getInstance();
						day_final.set(Calendar.DAY_OF_MONTH, j);
						day_final.set(Calendar.HOUR_OF_DAY, this.wednesday.getStart_service().get(Calendar.HOUR_OF_DAY)
								+ i * this.wednesday.getDuration_service());
						mark.add(new Scheduling(day_final, day_final, this.wednesday.getDoctor(),
								this.wednesday.getDoctor().getSpecialty()));
					}
				}
			}
		}
		if (this.thursday != null) {
			long timeAvailable = (this.thursday.getEnd_service().getTimeInMillis()
					- this.thursday.getStart_service().getTimeInMillis()) / 1000 * 60;
			Integer duration = (int) (timeAvailable / this.thursday.getDuration_service());
			for (int i = 0; i < duration; i++) {
				Calendar data = Calendar.getInstance();
				int daysInCurrentMonth = java.time.LocalDate.now().lengthOfMonth();
				for (int j = 1; j < daysInCurrentMonth; j++) {
					data.set(Calendar.DAY_OF_MONTH, j);
					int diaDaSemana = data.get(Calendar.DAY_OF_WEEK);
					if (diaDaSemana == 5) {
						Calendar day_final = Calendar.getInstance();
						day_final.set(Calendar.DAY_OF_MONTH, j);
						day_final.set(Calendar.HOUR_OF_DAY, this.thursday.getStart_service().get(Calendar.HOUR_OF_DAY)
								+ i * this.thursday.getDuration_service());
						mark.add(new Scheduling(day_final, day_final, this.thursday.getDoctor(),
								this.thursday.getDoctor().getSpecialty()));
					}
				}
			}
		}
		if (this.friday != null) {
			long timeAvailable = (this.friday.getEnd_service().getTimeInMillis()
					- this.friday.getStart_service().getTimeInMillis()) / 1000 * 60;
			Integer duration = (int) (timeAvailable / this.friday.getDuration_service());
			for (int i = 0; i < duration; i++) {
				Calendar data = Calendar.getInstance();
				int daysInCurrentMonth = java.time.LocalDate.now().lengthOfMonth();
				for (int j = 1; j < daysInCurrentMonth; j++) {
					data.set(Calendar.DAY_OF_MONTH, j);
					int diaDaSemana = data.get(Calendar.DAY_OF_WEEK);
					if (diaDaSemana == 6) {
						Calendar day_final = Calendar.getInstance();
						day_final.set(Calendar.DAY_OF_MONTH, j);
						day_final.set(Calendar.HOUR_OF_DAY, this.friday.getStart_service().get(Calendar.HOUR_OF_DAY)
								+ i * this.friday.getDuration_service());
						mark.add(new Scheduling(day_final, day_final, this.friday.getDoctor(),
								this.friday.getDoctor().getSpecialty()));
					}
				}
			}
		}
		if (this.saturday != null) {
			long timeAvailable = (this.saturday.getEnd_service().getTimeInMillis()
					- this.saturday.getStart_service().getTimeInMillis()) / 1000 * 60;
			Integer duration = (int) (timeAvailable / this.saturday.getDuration_service());
			for (int i = 0; i < duration; i++) {
				Calendar data = Calendar.getInstance();
				int daysInCurrentMonth = java.time.LocalDate.now().lengthOfMonth();
				for (int j = 1; j < daysInCurrentMonth; j++) {
					data.set(Calendar.DAY_OF_MONTH, j);
					int diaDaSemana = data.get(Calendar.DAY_OF_WEEK);
					if (diaDaSemana == 7) {
						Calendar day_final = Calendar.getInstance();
						day_final.set(Calendar.DAY_OF_MONTH, j);
						day_final.set(Calendar.HOUR_OF_DAY, this.saturday.getStart_service().get(Calendar.HOUR_OF_DAY)
								+ i * this.saturday.getDuration_service());
						mark.add(new Scheduling(day_final, day_final, this.saturday.getDoctor(),
								this.saturday.getDoctor().getSpecialty()));
					}
				}
			}
		}

		return mark;
	}

}
