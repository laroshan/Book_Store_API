//package com.healthsystem.resorce;
//
//import javax.ws.rs.*;
//import javax.ws.rs.core.MediaType;
//import java.util.List;
//
//@Path("/billing")
//public class BillingResource {
//    private BillingDAO billingDAO = new BillingDAO();
//
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public void addBilling(Billing billing) {
//        billingDAO.addBilling(billing);
//    }
//
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public List<Billing> getAllBillings() {
//        return billingDAO.getAllBillings();
//    }
//
//    @GET
//    @Path("/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Billing getBillingById(@PathParam("id") int id) {
//        return billingDAO.getBillingById(id);
//    }
//
//    @PUT
//    @Path("/{id}")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public void updateBilling(@PathParam("id") int id, Billing updatedBilling) {
//        billingDAO.updateBilling(id, updatedBilling);
//    }
//
//    @DELETE
//    @Path("/{id}")
//    public void deleteBilling(@PathParam("id") int id) {
//        billingDAO.deleteBilling(id);
//    }
//}
