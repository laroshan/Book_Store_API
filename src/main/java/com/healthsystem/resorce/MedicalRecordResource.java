//package com.healthsystem.resorce;
//
//import com.healthsystem.entity.MedicalRecord;
//
//import javax.ws.rs.*;
//import javax.ws.rs.core.MediaType;
//import java.util.List;
//
//@Path("/medicalrecords")
//public class MedicalRecordResource<MedicalRecordDAO> {
//    private MedicalRecordDAO medicalRecordDAO = new MedicalRecordDAO();
//
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public void addMedicalRecord(MedicalRecord medicalRecord) {
//        medicalRecordDAO.addMedicalRecord(medicalRecord);
//    }
//
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public List<MedicalRecord> getAllMedicalRecords() {
//        return medicalRecordDAO.getAllMedicalRecords();
//    }
//
//    @GET
//    @Path("/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public MedicalRecord getMedicalRecordById(@PathParam("id") int id) {
//        return medicalRecordDAO.getMedicalRecordById(id);
//    }
//
//    @PUT
//    @Path("/{id}")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public void updateMedicalRecord(@PathParam("id") int id, MedicalRecord updatedMedicalRecord) {
//        medicalRecordDAO.updateMedicalRecord(id, updatedMedicalRecord);
//    }
//
//    @DELETE
//    @Path("/{id}")
//    public void deleteMedicalRecord(@PathParam("id") int id) {
//        medicalRecordDAO.deleteMedicalRecord(id);
//    }
//}
