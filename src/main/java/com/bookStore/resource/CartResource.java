package com.bookStore.resource;

import com.bookStore.dao.CartDAO;
import com.bookStore.entity.CartItem;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.util.List;

@Path("/customers/{customerId}/cart")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CartResource {
    private CartDAO carts = CartDAO.getInstance();

    @POST
    @Path("/items")
    public Response addItem(@PathParam("customerId") String cid, CartItem item) {
        carts.addItem(cid, item);
        return Response.status(201).entity(item).build();
    }

    @GET
    public List<CartItem> viewCart(@PathParam("customerId") String cid) {
        return carts.getCart(cid);
    }

    @PUT
    @Path("/items/{bookId}")
    public Response updateItem(@PathParam("customerId") String cid,
            @PathParam("bookId") String bid,
            CartItem item) {
        carts.updateItem(cid, bid, item.getQuantity());
        return Response.ok(item).build();
    }

    @DELETE
    @Path("/items/{bookId}")
    public Response removeItem(@PathParam("customerId") String cid,
            @PathParam("bookId") String bid) {
        carts.removeItem(cid, bid);
        return Response.noContent().build();
    }
}
