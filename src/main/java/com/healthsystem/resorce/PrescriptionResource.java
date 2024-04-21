//package com.healthsystem.resorce;
//
//import com.healthsystem.entity.Prescription;
//
//import javax.ws.rs.*;
//import javax.ws.rs.core.MediaType;
//import java.util.List;
//
//@Path("/prescriptions")
//public class PrescriptionResource<PrescriptionDAO> {
//    private PrescriptionDAO prescriptionDAO = new PrescriptionDAO();
//
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public void addPrescription(Prescription prescription) {
//        prescriptionDAO.addPrescription(prescription);
//    }
//
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public List<Prescription> getAllPrescriptions() {
//        return prescriptionDAO.getAllPrescriptions();
//    }
//
//    @GET
//    @Path("/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Prescription getPrescriptionById(@PathParam("id") int id) {
//        return prescriptionDAO.getPrescriptionById(id);
//    }
//
//    @PUT
//    @Path("/{id}")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public void updatePrescription(@PathParam("id") int id, Prescription updatedPrescription) {
//        prescriptionDAO.updatePrescription(id, updatedPrescription);
//    }
//
//    @DELETE
//    @Path("/{id}")
//    public void deletePrescription(@PathParam("id") int id) {
//        prescriptionDAO.deletePrescription(id);
//    }
//}
