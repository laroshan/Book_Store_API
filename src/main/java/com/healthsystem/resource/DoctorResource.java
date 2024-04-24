package com.healthsystem.resource;

import com.healthsystem.dao.DoctorDAO;
import com.healthsystem.entity.Doctor;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.*;

@Path("/doctors")
public class DoctorResource {
    private DoctorDAO doctorDAO = DoctorDAO.getInstance(); // Use the singleton instance

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addDoctor(Doctor doctor) {
        doctorDAO.addDoctor(doctor);
        return Response.status(Response.Status.CREATED).entity(doctor).build();
    }

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Doctor> getAllDoctors() {
        return doctorDAO.getAllDoctors();
    }

    @GET
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDoctorById(@PathParam("id") String id) {
        try {
            Doctor doctor = doctorDAO.getDoctorById(id);
            return Response.ok(doctor).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Doctor not found").build();
        }
    }

    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateDoctor(@PathParam("id") String id, Doctor updatedDoctor) {
        try {
            doctorDAO.updateDoctor(id, updatedDoctor);
            return Response.noContent().build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Doctor not found").build();
        }
    }

    @DELETE
    @Path("/delete/{id}")
    public Response deleteDoctor(@PathParam("id") String id) {
        try {
            doctorDAO.deleteDoctor(id);
            return Response.noContent().build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Doctor not found").build();
        }
    }
}
