package com.healthsystem.resorce;

import com.healthsystem.dao.BillingDAO;
import com.healthsystem.entity.Billing;
import com.healthsystem.exception.HealthSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/billing")
public class BillingResource {
    private BillingDAO billingDAO = new BillingDAO();
    private static final Logger logger = LoggerFactory.getLogger(BillingResource.class);


    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addBilling(Billing billing) {
        try {
            billingDAO.addBilling(billing);
            logger.info("Billing record added: {}", billing);
            return Response.status(Response.Status.CREATED).entity(billing).build();
        } catch (HealthSystemException e) {
            logger.error("Error adding billing record: {}", e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Billing> getAllBillings() {
        return billingDAO.getAllBillings();
    }

    @GET
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Billing getBillingById(@PathParam("id") String id) {
        try {
            Billing billing = billingDAO.getBillingById(id);
            logger.info("Retrieved billing record: {}", billing);
            return billing;
        } catch (HealthSystemException e) {
            logger.error("Error retrieving billing record: {}", e.getMessage());
            throw  e;
        }
    }
    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateBilling(@PathParam("id") String id, Billing updatedBilling) {
        try {
            billingDAO.updateBilling(id, updatedBilling);
            logger.info("Billing record updated: {}", updatedBilling);
            return Response.ok().entity("Billing record updated successfully.").build();
        } catch (HealthSystemException e) {
            logger.error("Error updating billing record: {}", e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/delete/{id}")
    public Response deleteBilling(@PathParam("id") String id) {
        try {
            billingDAO.deleteBilling(id);
            logger.info("Billing record deleted with ID: {}", id);
            return Response.ok().entity("Billing record deleted successfully.").build();
        } catch (HealthSystemException e) {
            logger.error("Error deleting billing record: {}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }}
