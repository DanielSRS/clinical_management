package com.clinical.management.model.calendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Calendar_doctor {

	private Day_doctor domingo;
	private Day_doctor segunda;
	private Day_doctor terca;
	private Day_doctor quarta;
	private Day_doctor quinta;
	private Day_doctor sexta;
	private Day_doctor sabado;

	public Calendar_doctor() {
	}

	public Day_doctor getDomingo() {
		return domingo;
	}

	public void setDomingo(Day_doctor domingo) {
		this.domingo = domingo;
	}

	public Day_doctor getSegunda() {
		return segunda;
	}

	public void setSegunda(Day_doctor segunda) {
		this.segunda = segunda;
	}

	public Day_doctor getTerca() {
		return terca;
	}

	public void setTerca(Day_doctor terca) {
		this.terca = terca;
	}

	public Day_doctor getQuarta() {
		return quarta;
	}

	public void setQuarta(Day_doctor quarta) {
		this.quarta = quarta;
	}

	public Day_doctor getQuinta() {
		return quinta;
	}

	public void setQuinta(Day_doctor quinta) {
		this.quinta = quinta;
	}

	public Day_doctor getSexta() {
		return sexta;
	}

	public void setSexta(Day_doctor sexta) {
		this.sexta = sexta;
	}

	public Day_doctor getSabado() {
		return sabado;
	}

	public void setSabado(Day_doctor sabado) {
		this.sabado = sabado;
	}
	
	public List<Scheduling> generateCalendarDoctor(){
		List<Scheduling> mark = new ArrayList<>();
		if(this.domingo != null) {
			long timeAvailable = (this.domingo.getEnd_service().getTimeInMillis() - this.domingo.getStart_service().getTimeInMillis()) / 1000 * 60;
			Integer duration = (int) (timeAvailable/ this.domingo.getDuration_service());
			for(int i = 0; i < duration; i++) {
				Calendar data = Calendar.getInstance();
		        int daysInCurrentMonth = java.time.LocalDate.now().lengthOfMonth();
		        for (int j = 1; j < daysInCurrentMonth; j++) {
		            data.set(Calendar.DAY_OF_MONTH, j);
		            int diaDaSemana = data.get(Calendar.DAY_OF_WEEK);
		            if (diaDaSemana == 1) {
		            	Calendar day_final = Calendar.getInstance();
		            	day_final.set(Calendar.DAY_OF_MONTH, j);
		            	day_final.set(Calendar.HOUR_OF_DAY, this.domingo.getStart_service().get(Calendar.HOUR_OF_DAY) + i * this.domingo.getDuration_service());
		                mark.add(new Scheduling(day_final, day_final, this.domingo.getDoctor(), this.domingo.getDoctor().getSpecialty()));
		            }
		        }
			}
		} if(this.segunda != null) {
			long timeAvailable = (this.domingo.getEnd_service().getTimeInMillis() - this.domingo.getStart_service().getTimeInMillis()) / 1000 * 60;
			Integer duration = (int) (timeAvailable/ this.domingo.getDuration_service());
			for(int i = 0; i < duration; i++) {
				Calendar data = Calendar.getInstance();
		        int daysInCurrentMonth = java.time.LocalDate.now().lengthOfMonth();
		        for (int j = 1; j < daysInCurrentMonth; j++) {
		            data.set(Calendar.DAY_OF_MONTH, j);
		            int diaDaSemana = data.get(Calendar.DAY_OF_WEEK);
		            if (diaDaSemana == 2) {
		            	Calendar day_final = Calendar.getInstance();
		            	day_final.set(Calendar.DAY_OF_MONTH, j);
		            	day_final.set(Calendar.HOUR_OF_DAY, this.domingo.getStart_service().get(Calendar.HOUR_OF_DAY) + i * this.domingo.getDuration_service());
		                mark.add(new Scheduling(day_final, day_final, this.domingo.getDoctor(), this.domingo.getDoctor().getSpecialty()));
		            }
		        }
			}
		} if(this.terca != null) {
			long timeAvailable = (this.domingo.getEnd_service().getTimeInMillis() - this.domingo.getStart_service().getTimeInMillis()) / 1000 * 60;
			Integer duration = (int) (timeAvailable/ this.domingo.getDuration_service());
			for(int i = 0; i < duration; i++) {
				Calendar data = Calendar.getInstance();
		        int daysInCurrentMonth = java.time.LocalDate.now().lengthOfMonth();
		        for (int j = 1; j < daysInCurrentMonth; j++) {
		            data.set(Calendar.DAY_OF_MONTH, j);
		            int diaDaSemana = data.get(Calendar.DAY_OF_WEEK);
		            if (diaDaSemana == 3) {
		            	Calendar day_final = Calendar.getInstance();
		            	day_final.set(Calendar.DAY_OF_MONTH, j);
		            	day_final.set(Calendar.HOUR_OF_DAY, this.domingo.getStart_service().get(Calendar.HOUR_OF_DAY) + i * this.domingo.getDuration_service());
		                mark.add(new Scheduling(day_final, day_final, this.domingo.getDoctor(), this.domingo.getDoctor().getSpecialty()));
		            }
		        }
			}
		} if(this.quarta != null) {
			long timeAvailable = (this.domingo.getEnd_service().getTimeInMillis() - this.domingo.getStart_service().getTimeInMillis()) / 1000 * 60;
			Integer duration = (int) (timeAvailable/ this.domingo.getDuration_service());
			for(int i = 0; i < duration; i++) {
				Calendar data = Calendar.getInstance();
		        int daysInCurrentMonth = java.time.LocalDate.now().lengthOfMonth();
		        for (int j = 1; j < daysInCurrentMonth; j++) {
		            data.set(Calendar.DAY_OF_MONTH, j);
		            int diaDaSemana = data.get(Calendar.DAY_OF_WEEK);
		            if (diaDaSemana == 4) {
		            	Calendar day_final = Calendar.getInstance();
		            	day_final.set(Calendar.DAY_OF_MONTH, j);
		            	day_final.set(Calendar.HOUR_OF_DAY, this.domingo.getStart_service().get(Calendar.HOUR_OF_DAY) + i * this.domingo.getDuration_service());
		                mark.add(new Scheduling(day_final, day_final, this.domingo.getDoctor(), this.domingo.getDoctor().getSpecialty()));
		            }
		        }
			}
		} if(this.quinta != null) {
			long timeAvailable = (this.domingo.getEnd_service().getTimeInMillis() - this.domingo.getStart_service().getTimeInMillis()) / 1000 * 60;
			Integer duration = (int) (timeAvailable/ this.domingo.getDuration_service());
			for(int i = 0; i < duration; i++) {
				Calendar data = Calendar.getInstance();
		        int daysInCurrentMonth = java.time.LocalDate.now().lengthOfMonth();
		        for (int j = 1; j < daysInCurrentMonth; j++) {
		            data.set(Calendar.DAY_OF_MONTH, j);
		            int diaDaSemana = data.get(Calendar.DAY_OF_WEEK);
		            if (diaDaSemana == 5) {
		            	Calendar day_final = Calendar.getInstance();
		            	day_final.set(Calendar.DAY_OF_MONTH, j);
		            	day_final.set(Calendar.HOUR_OF_DAY, this.domingo.getStart_service().get(Calendar.HOUR_OF_DAY) + i * this.domingo.getDuration_service());
		                mark.add(new Scheduling(day_final, day_final, this.domingo.getDoctor(), this.domingo.getDoctor().getSpecialty()));
		            }
		        }
			}
		} if(this.sexta != null) {
			long timeAvailable = (this.domingo.getEnd_service().getTimeInMillis() - this.domingo.getStart_service().getTimeInMillis()) / 1000 * 60;
			Integer duration = (int) (timeAvailable/ this.domingo.getDuration_service());
			for(int i = 0; i < duration; i++) {
				Calendar data = Calendar.getInstance();
		        int daysInCurrentMonth = java.time.LocalDate.now().lengthOfMonth();
		        for (int j = 1; j < daysInCurrentMonth; j++) {
		            data.set(Calendar.DAY_OF_MONTH, j);
		            int diaDaSemana = data.get(Calendar.DAY_OF_WEEK);
		            if (diaDaSemana == 6) {
		            	Calendar day_final = Calendar.getInstance();
		            	day_final.set(Calendar.DAY_OF_MONTH, j);
		            	day_final.set(Calendar.HOUR_OF_DAY, this.domingo.getStart_service().get(Calendar.HOUR_OF_DAY) + i * this.domingo.getDuration_service());
		                mark.add(new Scheduling(day_final, day_final, this.domingo.getDoctor(), this.domingo.getDoctor().getSpecialty()));
		            }
		        }
			}
		} if(this.sabado != null) {
			long timeAvailable = (this.domingo.getEnd_service().getTimeInMillis() - this.domingo.getStart_service().getTimeInMillis()) / 1000 * 60;
			Integer duration = (int) (timeAvailable/ this.domingo.getDuration_service());
			for(int i = 0; i < duration; i++) {
				Calendar data = Calendar.getInstance();
		        int daysInCurrentMonth = java.time.LocalDate.now().lengthOfMonth();
		        for (int j = 1; j < daysInCurrentMonth; j++) {
		            data.set(Calendar.DAY_OF_MONTH, j);
		            int diaDaSemana = data.get(Calendar.DAY_OF_WEEK);
		            if (diaDaSemana == 7) {
		            	Calendar day_final = Calendar.getInstance();
		            	day_final.set(Calendar.DAY_OF_MONTH, j);
		            	day_final.set(Calendar.HOUR_OF_DAY, this.domingo.getStart_service().get(Calendar.HOUR_OF_DAY) + i * this.domingo.getDuration_service());
		                mark.add(new Scheduling(day_final, day_final, this.domingo.getDoctor(), this.domingo.getDoctor().getSpecialty()));
		            }
		        }
			}
		}
		
		return mark;
	}

}
