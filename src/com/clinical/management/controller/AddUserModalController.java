package com.clinical.management.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.clinical.management.dao.Calendar_doctorDAO;
import com.clinical.management.dao.Day_doctorDAO;
import com.clinical.management.dao.DoctorDAO;
import com.clinical.management.dao.SpecialtyDAO;
import com.clinical.management.dao.UserDAO;
import com.clinical.management.model.calendar.Calendar_doctor;
import com.clinical.management.model.calendar.Day_doctor;
import com.clinical.management.model.doctor.Doctor;
import com.clinical.management.model.specialty.Specialty;
import com.clinical.management.model.users.OrderTypes;
import com.clinical.management.model.users.User;
import com.clinical.management.view.navigation.StackNavigator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class AddUserModalController {

    private UsersPageController userListingPageController;

    private List<Specialty> especialidades;

    private Integer idDOUsuarioCriado = null;

    @FXML private Label labelMedico;

    @FXML private TextField nameField;

    @FXML private TextField cpfField;

    @FXML private TextField passwordField;

    @FXML private VBox userContent;

    @FXML private VBox doctorContent;

    @FXML private ComboBox<String> especialidade;

    @FXML private ComboBox<String> subespecialidade;

    // Segunda
    @FXML private TextField segHI;
    @FXML private TextField segHF;
    @FXML private TextField segMI;
    @FXML private TextField segMF;

    // Terça
    @FXML private TextField terHI;
    @FXML private TextField terHF;
    @FXML private TextField terMI;
    @FXML private TextField terMF;

    // Quarta
    @FXML private TextField quaHI;
    @FXML private TextField quaHF;
    @FXML private TextField quaMI;
    @FXML private TextField quaMF;

    // Quinta
    @FXML private TextField quiHI;
    @FXML private TextField quiHF;
    @FXML private TextField quiMI;
    @FXML private TextField quiMF;

    // Sexta
    @FXML private TextField sexHI;
    @FXML private TextField sexHF;
    @FXML private TextField sexMI;
    @FXML private TextField sexMF;

    // Sabado
    @FXML private TextField sabHI;
    @FXML private TextField sabHF;
    @FXML private TextField sabMI;
    @FXML private TextField sabMF;

    // Domingo
    @FXML private TextField domHI;
    @FXML private TextField domHF;
    @FXML private TextField domMI;
    @FXML private TextField domMF;

    @FXML private RadioButton medicoRadio;
    @FXML private RadioButton recepcionistaRadio;
    @FXML private RadioButton adminRadio;

    @FXML private Button salvarButton;

    private RadioButton selected;
    private ToggleGroup radioGroup = new ToggleGroup();

    private OrderTypes ot = OrderTypes.PATIENT;

    @FXML private void changeUserAccess() {
        if (idDOUsuarioCriado != null) return; //não altera se usuário ja criado
        RadioButton rb = (RadioButton) radioGroup.getSelectedToggle();
        labelMedico.setDisable(true);
        if (selected != null && selected.equals(rb)) {
            rb.setSelected(false);
            selected = null;
            salvarButton.setText("Salvar");
            ot = OrderTypes.PATIENT;
            return;
        }
        selected = rb;
        if (rb.equals(medicoRadio)) {
            System.out.println("acesso de medico");
            salvarButton.setText("Continuar");
            ot = OrderTypes.DOCTOR;
            labelMedico.setDisable(false);
        }
        if (rb.equals(recepcionistaRadio)) {
            System.out.println("acesso de recepcionista");
            salvarButton.setText("Salvar");
            ot = OrderTypes.RECEPTIONIST;
        }
        if (rb.equals(adminRadio)) {
            System.out.println("acesso de admin");
            salvarButton.setText("Salvar");
            ot = OrderTypes.ADMIN;
        }
    }

    /**
     * Cria um novo usuário
     */
    @FXML private void createUser() {
        String name = nameField.getText();
        String cpf = cpfField.getText();
        String password = passwordField.getText();

        // Não faz nada se algum campo estive vazio
        if (name.equals("") || cpf.equals("") || password.equals("")){
            return;
        }

        if (salvarButton.getText().equals("Continuar")) {
            goToDoctor();
            salvarButton.setText("Salvar");
            return;
        }
 
        if (idDOUsuarioCriado == null) {
            User newUser = new User(name, cpf, ot, password); // cria usuário
            UserDAO dbUser = new UserDAO();
            idDOUsuarioCriado = dbUser.saveUser(newUser);  // adiciona no banco de dados

            System.out.println("Usuário criado com o id: " + idDOUsuarioCriado);

            if (idDOUsuarioCriado != null && ot == OrderTypes.DOCTOR) {
                createDoctor(newUser, idDOUsuarioCriado.intValue());
            }
        }

        if (userListingPageController != null) {
            userListingPageController.renderUsers();  // recarrega a listagem de usuários
        }

        closeModal(); //fecha o modal
    }
    
    /**
     * Fecha o modal
     */
    @FXML public void closeModal(){
        StackNavigator.removeModal();
    }

    /**
     * Define o controller da pagina principal
     */
    public void addController(UsersPageController controller) {
        this.userListingPageController = controller;
    }

    @FXML private void goToDoctor() {
        this.userContent.setVisible(false);
        this.doctorContent.setVisible(true);
        salvarButton.setText("Salvar");
    }

    @FXML private void goToUser() {
        if (ot == OrderTypes.DOCTOR) {
            salvarButton.setText("Continuar");
        }
        this.userContent.setVisible(true);
        this.doctorContent.setVisible(false);
    }

    public void initialize() {
        SpecialtyDAO spd = new SpecialtyDAO();
        especialidades = spd.getSpecialtys();
        Iterator<Specialty> it = especialidades.iterator();

        while(it.hasNext()) {
            Specialty aux = it.next();
            this.especialidade.getItems().add(aux.getName());
            this.subespecialidade.getItems().add(aux.getName());
        }

        medicoRadio.setToggleGroup(radioGroup);
        recepcionistaRadio.setToggleGroup(radioGroup);
        adminRadio.setToggleGroup(radioGroup);
    }

    private void createDoctor(User user, int id) {
        Integer idDaEspecialidade = null;
        Integer idDaSubEspecialidade = null;
        Iterator<Specialty> it = especialidades.iterator();

        String especialidadeSelecionada = especialidade.getValue();
        String subEspecialidadeSelecionada = subespecialidade.getValue();

        while (it.hasNext()) {
            Specialty aux = it.next();
            if (especialidadeSelecionada.equals(aux.getName())) {
                idDaEspecialidade = aux.getID();
            }

        }

        it = especialidades.iterator();

        while (it.hasNext()) {
            Specialty aux = it.next();
            if (subEspecialidadeSelecionada.equals(aux.getName())) {
                if (!especialidadeSelecionada.equals(subEspecialidadeSelecionada)) {
                    idDaSubEspecialidade = aux.getID();
                }
            }

        }

        Doctor doctor = new Doctor(user.getName(), user.getCpf(), user.getPassword(), idDaEspecialidade);
        doctor.setSubSpacialtyID(idDaSubEspecialidade);
        DoctorDAO docDB = new DoctorDAO();
        Integer idDoMedicoCriado = docDB.saveDoctor(doctor, id);
        System.out.println("Medico: " + doctor.getName() + ", ID: " + idDoMedicoCriado);
        if (idDoMedicoCriado == null)  return;
        doctor.setId(idDoMedicoCriado);
        createAgenda(doctor, idDoMedicoCriado);
    }

    private void createAgenda(Doctor doctor,Integer docID){

        //List<Day_doctor> dias = new ArrayList<>();
        Day_doctorDAO dayDAO = new Day_doctorDAO();

        Day_doctor seg = null;
        Day_doctor ter = null;
        Day_doctor qua = null;
        Day_doctor qui = null;
        Day_doctor sex = null;
        Day_doctor sab = null;
        Day_doctor dom = null;

        Integer segid = null, terid = null, quaid = null, quiid = null, sexid = null, sabid = null, domid = null;
        
        try {
            int horaDeInicioSegunda = Integer.parseInt(segHI.getText());
            int horaDeFimSegunda = Integer.parseInt(segHF.getText());
            seg = new Day_doctor(horaDeInicioSegunda, horaDeFimSegunda, doctor, 20);
            segid = dayDAO.saveDay_doctor(seg, 1);
            seg.setId(segid);
        } catch (NumberFormatException e) {
            //
        }

        try {
            int horaDeInicioTerca = Integer.parseInt(terHI.getText());
            int horaDeFimTerca = Integer.parseInt(terHF.getText());
            ter = new Day_doctor(horaDeInicioTerca, horaDeFimTerca, doctor, 20);
            terid = dayDAO.saveDay_doctor(ter, 1);
            ter.setId(terid);
        } catch (NumberFormatException e) {
            //
        }

        try {
            int horaDeInicioQuarta = Integer.parseInt(quaHI.getText());
            int horaDeFimQuarta = Integer.parseInt(quaHF.getText());
            qua = new Day_doctor(horaDeInicioQuarta, horaDeFimQuarta, doctor, 20);
            quaid = dayDAO.saveDay_doctor(qua, 1);
            qua.setId(quaid);
        } catch (NumberFormatException e) {
            //
        }

        try {
            int horaDeInicioQuinta = Integer.parseInt(quiHI.getText());
            int horaDeFimQuinta = Integer.parseInt(quiHF.getText());
            qui = new Day_doctor(horaDeInicioQuinta, horaDeFimQuinta, doctor, 20);
            quiid = dayDAO.saveDay_doctor(qui, 1);
            qui.setId(quiid);
        } catch (NumberFormatException e) {
            //
        }

        try {
            int horaDeInicioSexta = Integer.parseInt(sexHI.getText());
            int horaDeFimSexta = Integer.parseInt(sexHF.getText());
            sex = new Day_doctor(horaDeInicioSexta, horaDeFimSexta, doctor, 20);
            sexid = dayDAO.saveDay_doctor(sex, 1);
            sex.setId(sexid);
        } catch (NumberFormatException e) {
            //
        }

        try {
            int horaDeInicioSabado = Integer.parseInt(sabHI.getText());
            int horaDeFimSabado = Integer.parseInt(sabHF.getText());
            sab = new Day_doctor(horaDeInicioSabado, horaDeFimSabado, doctor, 20);
            sabid = dayDAO.saveDay_doctor(sab, 1);
            sab.setId(sabid);
        } catch (NumberFormatException e) {
            //
        }

        try {
            int horaDeInicioDomingo = Integer.parseInt(domHI.getText());
            int horaDeFimDomingo = Integer.parseInt(domHF.getText());
            dom = new Day_doctor(horaDeInicioDomingo, horaDeFimDomingo, doctor, 20);
            domid = dayDAO.saveDay_doctor(dom, 1);
            dom.setId(domid);
        } catch (NumberFormatException e) {
            //
        }

        Calendar_doctor cal = new Calendar_doctor();
        cal.setDocID(docID);
        cal.setMonday(seg);
        cal.setTuesday(ter);
        cal.setWednesday(qua);
        cal.setThursday(qui);
        cal.setFriday(sex);
        cal.setSaturday(sab);
        cal.setSunday(dom);

        Calendar_doctorDAO cdDAO = new Calendar_doctorDAO();
        cdDAO.saveCalendar_doctor(cal);
        
    }

}
