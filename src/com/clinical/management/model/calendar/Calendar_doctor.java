package com.clinical.management.model.calendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
	private Integer doc_id;

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
		return this.doc_id;
	}

	/**
	 * @param doctor
	 */
	public void setDoctor(Integer doctor) {
		this.doc_id = doctor;
	}

	public Integer getDocID() {
		return this.doc_id;
	}

	public void setDocID(int id) {
		this.doc_id = id;
	}

	/**
	 * @return mark
	 * retorna uma lista de agedamento dos dias da semana
	 */
	public List<Scheduling> generateCalendarDoctor() {
		List<Scheduling> todosOsAgendamentos = new ArrayList<>();

		todosOsAgendamentos.addAll(createAgendamentos(this.sunday, 1));
		todosOsAgendamentos.addAll(createAgendamentos(this.monday, 2));
		todosOsAgendamentos.addAll(createAgendamentos(this.tuesday, 3));
		todosOsAgendamentos.addAll(createAgendamentos(this.wednesday, 4));
		todosOsAgendamentos.addAll(createAgendamentos(this.thursday, 5));
		todosOsAgendamentos.addAll(createAgendamentos(this.friday, 6));
		todosOsAgendamentos.addAll(createAgendamentos(this.sunday, 7));
		
		return todosOsAgendamentos;
	}

	private List<Scheduling> createAgendamentos(Day_doctor dia, int diaDaSemana) {
		List<Scheduling> agendamentos = new ArrayList<>();
		if (dia == null) return agendamentos;
		int quantidadeDeAtendimentos = calcQuantidadeDeAtendimentos(dia, diaDaSemana);
		int quantidadesDeDiasDoMes = java.time.LocalDate.now().lengthOfMonth();
		for (int consultaIndex = 0; consultaIndex < quantidadeDeAtendimentos; consultaIndex++) {
			Calendar mesAtual = Calendar.getInstance();
			for (int diaDoMes = 1; diaDoMes <= quantidadesDeDiasDoMes; diaDoMes++) {
				mesAtual.set(Calendar.DAY_OF_MONTH, diaDoMes);
				int diaDaSemanaNoMes = mesAtual.get(Calendar.DAY_OF_WEEK);
				if (diaDaSemanaNoMes == diaDaSemana) {
					Scheduling agendamento = criarAgendamento(diaDoMes, dia, consultaIndex);
					agendamentos.add(agendamento);
				}
			}
		}
		return agendamentos;
	}

	private int calcQuantidadeDeAtendimentos(Day_doctor dia, int diaDaSemana) {
		if (dia == null) return -1; // retorna se o dia for nulo
		long tempoDisponivel = (dia.getEnd_service().getTimeInMillis()
							   - dia.getStart_service().getTimeInMillis()) / (1000 * 60);
		int duracaoDoAtendimento = dia.getDuration_service();
		int quantidadeDeAtendimentos = (int) tempoDisponivel / duracaoDoAtendimento;
		return quantidadeDeAtendimentos;
	}

	private Scheduling criarAgendamento(int diaDoMes, Day_doctor dia, int consultaIndex) {
		Calendar diaDeHoje = Calendar.getInstance();
		diaDeHoje.set(Calendar.DAY_OF_MONTH, diaDoMes);
		int inicioDoAtendimento = dia.getStart_service().get(Calendar.HOUR_OF_DAY);
		int tempoDasConsultasAnteriores = consultaIndex * dia.getDuration_service();
		//int horaDeInicio = inicioDoAtendimento + tempoDasConsultasAnteriores;
		diaDeHoje.set(Calendar.HOUR_OF_DAY, inicioDoAtendimento);
		diaDeHoje.set(Calendar.MINUTE, 0);
		diaDeHoje.add(Calendar.MINUTE, tempoDasConsultasAnteriores);

		/*System.out.println("Dia: " 
						   + diaDeHoje.get(Calendar.DAY_OF_MONTH)
						   + "/" + diaDeHoje.get(Calendar.MONTH)
						   + "/" + diaDeHoje.get(Calendar.YEAR)
						   + "- Hora: "
						   + diaDeHoje.get(Calendar.HOUR_OF_DAY)
						   + ":"
						   + diaDeHoje.get(Calendar.MINUTE)
						   );*/
		
		Scheduling agendamento = new Scheduling(diaDeHoje, diaDeHoje, dia.getDoctor(), dia.getDoctor().getSpecialty());
		return agendamento;
	}

}
