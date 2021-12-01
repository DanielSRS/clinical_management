package com.clinical.management.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.clinical.management.model.doctor.Doctor;
import com.clinical.management.model.specialty.Specialty;
import com.clinical.management.model.users.OrderTypes;

class DoctorTest {

    private Doctor doctor;
    private Specialty specialty;
    /**
     * Este metodo é executado antes de cada teste de unidade (testes a seguir), 
     * e serve para inicializar objetos que são utilizados nos testes.
     */
    @BeforeEach
    void setUp() throws Exception {
        specialty = new Specialty("Oftalmologista");
        doctor = new Doctor("User Full Name", "00145232548", specialty, "password");
    }

    /**
     * Teste de unidade que verifica se os atributos de um medico (Doctor) 
     * são atribuídos corretamente.
     */
    @Test
    public void testAtributos() {
        assertNotNull(doctor);
        assertEquals("User Full Name", doctor.getName());
        assertEquals("00145232548", doctor.getCpf());
        assertEquals(OrderTypes.DOCTOR, doctor.getTypes());
        assertEquals(specialty, doctor.getSpecialty());
        assertNull(doctor.getSub_specialty());
    }
}
