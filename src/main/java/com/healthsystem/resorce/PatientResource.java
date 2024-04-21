package com.healthsystem.resorce;

import com.healthsystem.dao.PatientDAO;
import com.healthsystem.entity.Patient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/patients")
public class PatientResource {
    private PatientDAO patientDAO = new PatientDAO();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void addPatient(Patient patient) {
        patientDAO.addPatient(patient);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Patient> getAllPatients() {
        return patientDAO.getAllPatients();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Patient getPatientById(@PathParam("id") int id) {
        return patientDAO.getPatientById(id);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updatePatient(@PathParam("id") int id, Patient updatedPatient) {
        patientDAO.updatePatient(id, updatedPatient);
    }

    @DELETE
    @Path("/{id}")
    public void deletePatient(@PathParam("id") int id) {
        patientDAO.deletePatient(id);
    }
}
