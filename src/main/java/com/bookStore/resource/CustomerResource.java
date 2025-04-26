package com.bookStore.resource;

import com.bookStore.dao.CustomerDAO;
import com.bookStore.entity.Customer;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.util.List;
import com.bookStore.exception.InvalidInputException;

@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource {
    private CustomerDAO customers = CustomerDAO.getInstance();

    @POST
    public Response create(Customer c) {
        customers.add(c);
        return Response.status(201).entity(c).build();
    }

    @GET
    public List<Customer> list() {
        return customers.getAll();
    }

    @GET
    @Path("/{id}")
    public Customer get(@PathParam("id") String id) {
        return customers.get(id);
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") String id, Customer c) {
        if (!id.equals(c.getId()))
            throw new InvalidInputException("ID mismatch");
        customers.update(id, c);
        return Response.ok(c).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") String id) {
        customers.delete(id);
        return Response.noContent().build();
    }
}
