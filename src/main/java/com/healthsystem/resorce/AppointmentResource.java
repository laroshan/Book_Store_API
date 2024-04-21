//package com.healthsystem.resorce;
//
//import com.healthsystem.entity.Appointment;
//import com.healthsystem.dao.AppointmentDAO;
//import javax.ws.rs.*;
//import javax.ws.rs.core.MediaType;
//import java.util.List;
//
//@Path("/appointments")
//public class AppointmentResource {
//    private AppointmentDAO appointmentDAO = new AppointmentDAO();
//
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public void addAppointment(Appointment appointment) {
//        appointmentDAO.addAppointment(appointment);
//    }
//
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public List<Appointment> getAllAppointments() {
//        return appointmentDAO.getAllpointments();
//    }
//
//    @GET
//    @Path("/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Appointment getAppointmentById(@PathParam("id") int id) {
//        return appointmentDAO.getAppointmentById(id);
//    }
//
//    @PUT
//    @Path("/{id}")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public void updateAppointment(@PathParam("id") int id, Appointment updatedAppointment) {
//        appointmentDAO.updateAppointment(id, updatedAppointment);
//    }
//
//    @DELETE
//    @Path("/{id}")
//    public void deleteAppointment(@PathParam("id") int id) {
//        appointmentDAO.deleteAppointment(id);
//    }
//}
