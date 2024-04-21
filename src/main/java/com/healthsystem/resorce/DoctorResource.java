package com.healthsystem.resorce;

import com.healthsystem.dao.DoctorDAO;
import com.healthsystem.entity.Doctor;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/doctors")
public class DoctorResource {
    private DoctorDAO doctorDAO = new DoctorDAO();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void addDoctor(Doctor doctor) {
        doctorDAO.addDoctor(doctor);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Doctor> getAllDoctors() {
        return doctorDAO.getAllDoctors();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Doctor getDoctorById(@PathParam("id") int id) {
        return doctorDAO.getDoctorById(id);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateDoctor(@PathParam("id") int id, Doctor updatedDoctor) {
        doctorDAO.updateDoctor(id, updatedDoctor);
    }

    @DELETE
    @Path("/{id}")
    public void deleteDoctor(@PathParam("id") int id) {
        doctorDAO.deleteDoctor(id);
    }
}
