package com.bookStore.resource;

import com.bookStore.dao.OrderDAO;
import com.bookStore.dao.CartDAO;
import com.bookStore.entity.Order;
import com.bookStore.entity.CartItem;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.util.*;
import com.bookStore.exception.CartNotFoundException;

@Path("/customers/{customerId}/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {
    private OrderDAO orders = OrderDAO.getInstance();
    private CartDAO carts = CartDAO.getInstance();

    @POST
    public Response placeOrder(@PathParam("customerId") String cid) {
        List<CartItem> cart = carts.getCart(cid);
        if (cart.isEmpty())
            throw new CartNotFoundException("Cart is empty");
        double total = cart.stream().mapToDouble(i -> /* lookup price */0).sum();
        Order o = new Order(UUID.randomUUID().toString(), cid, cart, total);
        orders.addOrder(cid, o);
        carts.clearCart(cid);
        return Response.status(201).entity(o).build();
    }

    @GET
    public List<Order> getAll(@PathParam("customerId") String cid) {
        return orders.getOrders(cid);
    }

    @GET
    @Path("/{orderId}")
    public Order getOne(@PathParam("customerId") String cid,
            @PathParam("orderId") String oid) {
        return orders.getOrder(cid, oid);
    }
}
