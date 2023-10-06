package org.acme.controller;

import java.util.List;

import org.acme.Repository.ChannelRepository;
import org.acme.Repository.UserRepository;
import org.acme.model.ChannelModel;
import org.acme.model.UserModel;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/channel")
@Transactional
public class ChannelController {

    @Inject
    ChannelRepository repository;
    @Inject
    UserRepository userRepository;

    @GET
   @Path("/save/{hash}")
   @Produces(MediaType.APPLICATION_JSON)
   public ChannelModel save(@PathParam("hash") String hash) {
      ChannelModel channel = new ChannelModel();
      channel.setHash(hash);
      repository.persist(channel);
      return channel;
   }

   @GET
   @Path("/add/{idChannel}/{idUser}")
   @Produces(MediaType.APPLICATION_JSON)
   public UserModel add(@PathParam("idChannel") Long idChannel, @PathParam("idUser") Long idUser) {

      ChannelModel channel = repository.findById(idChannel);
      if (channel == null)
         throw new BadRequestException("Channel not found");

      UserModel user = userRepository.findById(idUser);
      if (user == null)
         throw new BadRequestException("User not found");

      channel.addUser(user);
      user.addChannel(channel);

      userRepository.persist(user);

      return user;
   }

   @GET
   @Path("/list")
   @Produces(MediaType.APPLICATION_JSON)
   public List<ChannelModel> list() {
      return repository.listAll();
   }

}
