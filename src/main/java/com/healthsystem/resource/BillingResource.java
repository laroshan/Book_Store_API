package com.healthsystem.resource;

import com.healthsystem.dao.*;
import com.healthsystem.entity.*;
import com.healthsystem.exception.HealthSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/billings")
public class BillingResource {
    private static final Logger logger = LoggerFactory.getLogger(BillingResource.class);
    private BillingDAO billingDAO = BillingDAO.getInstance();

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addBilling(Billing billing) {
        try {
            billingDAO.addBilling(billing);
            return Response.status(Response.Status.CREATED).entity(billing). build();
        } catch (Exception e) {
            logger.error("Error adding billing: {}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error adding billing"). build();
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
    public Response getBillingById(@PathParam("id") String id) {
        try {
            Billing billing = billingDAO.getBillingById(id);
            return Response.ok(billing).build();
        } catch (HealthSystemException e) {
            logger.error("Error retrieving billing: {}", e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity("Billing not found"). build();
        }
    }

    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateBilling(@PathParam("id") String id, Billing updatedBilling) {
        try {
            if (!id.equals(updatedBilling.getId())) {
                return Response.status(Response.Status.BAD_REQUEST). entity("ID mismatch"). build();
            }

            billingDAO.updateBilling(id, updatedBilling);
            return Response.ok("Billing updated successfully"). build();
        } catch (HealthSystemException e) {
            logger.error("Error updating billing: {}", e.getMessage());
            return Response.status(Response.Status.NOT_FOUND). entity("Billing not found"). build();
        }
    }

    @DELETE
    @Path("/delete/{id}")
    public Response deleteBilling(@PathParam("id") String id) {
        try {
            billingDAO.deleteBilling(id);
            return Response.ok("Billing deleted successfully"). build();
        } catch (HealthSystemException e) {
            logger.error("Error deleting billing: {}", e.getMessage());
            return Response.status(Response.Status.NOT_FOUND). entity("Billing not found"). build();
        }
    }
}
