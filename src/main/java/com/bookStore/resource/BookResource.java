package com.bookStore.resource;

import com.bookStore.dao.BookDAO;
import com.bookStore.entity.Book;
import com.bookStore.dao.AuthorDAO;
import com.bookStore.exception.InvalidInputException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.util.List;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {
    private BookDAO books = BookDAO.getInstance();
    private AuthorDAO authors = AuthorDAO.getInstance();

    @POST
    public Response create(Book b) {
        // validate author exists
        authors.get(b.getAuthorId());
        books.add(b);
        return Response.status(201).entity(b).build();
    }

    @GET
    public List<Book> list() {
        return books.getAll();
    }

    @GET
    @Path("/{id}")
    public Book get(@PathParam("id") String id) {
        return books.get(id);
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") String id, Book b) {
        if (!id.equals(b.getId()))
            throw new InvalidInputException("ID mismatch");
        // validate author
        authors.get(b.getAuthorId());
        books.update(id, b);
        return Response.ok(b).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") String id) {
        books.delete(id);
        return Response.noContent().build();
    }
}
