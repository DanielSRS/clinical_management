package com.clinical.management.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import com.clinical.management.dao.SchedulingDAO;
import com.clinical.management.dao.SpecialtyDAO;
import com.clinical.management.model.calendar.Scheduling;
import com.clinical.management.model.doctor.Doctor;
import com.clinical.management.model.specialty.Specialty;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class AutoAtendimentoController {

    SchedulingDAO sch = new SchedulingDAO();
    SpecialtyDAO espDAO = new SpecialtyDAO();

    List<Scheduling> agendamentos =  sch.getScheduling();
    List<Specialty> especialidades = espDAO.getSpecialtys();
    
    @FXML private VBox contentContainer;

    public void initialize() {
    
        Iterator<Scheduling> it = agendamentos.iterator();

        // data, lista de agendamentos na data
        Map<Integer, Map<Integer, List<Scheduling>>> agendamentosPorDia = new HashMap<>();
        
        

        while (it.hasNext()) {
            Scheduling aux = it.next();
            Integer dia = aux.getDay().get(Calendar.DAY_OF_MONTH);
            Doctor medico = aux.getDoctor();
            if (agendamentosPorDia.containsKey(dia)) { // se há agendamentos no dia
                
                // se em agendametos por dia há agendamentos do medico (por id)
                if (agendamentosPorDia.get(dia).containsKey(medico.getId())) {
                    agendamentosPorDia.get(dia).get(medico.getId()).add(aux); // adiciona o agendamento a lista de agendamentos por medico
                } else {
                    // id do medico, lista de agendamenots
                    //
                    List<Scheduling> agendamentosDoMedicoNoDia = new ArrayList<>();
                    agendamentosDoMedicoNoDia.add(aux);
                    agendamentosPorDia.get(dia).put(medico.getId(), agendamentosDoMedicoNoDia);
                    //agendamentosMedico.put(idDoMedico, agendamentosDoMedicoNoDia);
                }
                //agendamentosPorDia.get(dia).add(aux);
            } else {
                Map<Integer, List<Scheduling>> agendamentosMedico = new HashMap<>();
                List<Scheduling> agendamentosDoMedicoNoDia = new ArrayList<>();
                agendamentosDoMedicoNoDia.add(aux);
                agendamentosMedico.put(medico.getId(), agendamentosDoMedicoNoDia);

                agendamentosPorDia.put(dia, agendamentosMedico);
            }
        }

        for (Map.Entry<Integer, Map<Integer, List<Scheduling>>> pair : agendamentosPorDia.entrySet()) {
            Label l = new Label();
            Label k = new Label();
            Label n = new Label();
            l.setText("Dia: " + pair.getKey() + "");
            k.setText("");
            n.setText("");
            contentContainer.getChildren().add(l);
            contentContainer.getChildren().add(k);
            
            for (Map.Entry<Integer,  List<Scheduling>> data : pair.getValue().entrySet()) {
                Label doc = new Label("        " + data.getKey() + "");
                contentContainer.getChildren().add(doc);

                printAgendamentos(data.getValue());
                //contentContainer.getChildren().add(new Label(""));
                //System.out.println("Dia: " + pair.getKey());
                //System.out.println(pair.getValue());
                contentContainer.getChildren().add(new Label(""));
            }
        }

        //printAgendamentos(agendamentos);

        
    }

    private void printAgendamentos(List<Scheduling> agend) {
        Iterator<Scheduling> it = agend.iterator();
        while (it.hasNext()) {
            Scheduling aux = it.next();
            String dia = aux.getDay().get(Calendar.DAY_OF_MONTH) + "";
            int mes = aux.getDay().get(Calendar.MONTH) + 1;
            String hora = aux.getHour().get(Calendar.HOUR_OF_DAY) + "";
            String minutos = aux.getHour().get(Calendar.MINUTE) + "";
            String status = aux.getStatus().toString();
            String medico = aux.getDoctor().getName();
            String especialidade = "";
            Iterator<Specialty> espIT = especialidades.iterator();
            while(espIT.hasNext()) {
                Specialty auxEsp = espIT.next();
                System.out.println(auxEsp.getName() + " - " + auxEsp.getID() + " - " + aux.getDoctor().getSpecialty());
                if (auxEsp.getID() == aux.getDoctor().getSpecialty()) {
                    especialidade = auxEsp.getName();
                }
            }
            String s = "        Data: " + dia + "/ " + mes + " - hora: " + hora + ":" + minutos
                        + "Status: " + status + " - Especialidade: " + especialidade + " - Dr. " + medico;

            Label l = new Label();
            l.setText(s);

            contentContainer.getChildren().add(l);
        }
    }
}
