package com.bookStore.resource;

import com.bookStore.dao.AuthorDAO;
import com.bookStore.dao.BookDAO;
import com.bookStore.entity.Author;
import com.bookStore.entity.Book;
import com.bookStore.exception.InvalidInputException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.util.List;
import java.util.stream.Collectors;

@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthorResource {
    private AuthorDAO authors = AuthorDAO.getInstance();
    private BookDAO books = BookDAO.getInstance();

    @POST
    public Response create(Author a) {
        authors.add(a);
        return Response.status(201).entity(a).build();
    }

    @GET
    public List<Author> list() {
        return authors.getAll();
    }

    @GET
    @Path("/{id}")
    public Author get(@PathParam("id") String id) {
        return authors.get(id);
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") String id, Author a) {
        if (!id.equals(a.getId()))
            throw new InvalidInputException("ID mismatch");
        authors.update(id, a);
        return Response.ok(a).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") String id) {
        authors.delete(id);
        return Response.noContent().build();
    }

    @GET
    @Path("/{id}/books")
    public List<Book> getBooksByAuthor(@PathParam("id") String id) {
        // ensure author exists
        authors.get(id);
        return books.getAll().stream()
                .filter(b -> b.getAuthorId().equals(id))
                .collect(Collectors.toList());
    }
}
