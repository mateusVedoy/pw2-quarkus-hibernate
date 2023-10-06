package org.acme.controller;

import java.util.List;

import org.acme.Repository.UserRepository;
import org.acme.model.UserModel;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/user")
@Transactional
public class UserController {

    @Inject
    UserRepository repository;

    @GET
    @Path("/save/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserModel save(@PathParam("name") String name) {
        UserModel user = new UserModel();
        user.setName(name);
        repository.persist(user);
        return user;
    }

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public List<UserModel> list() {
        return repository.listAll();
    }

    @GET
    @Path("/list/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public UserModel list(@PathParam("id") Long id) {
        return repository.findById(id);
    }

}
