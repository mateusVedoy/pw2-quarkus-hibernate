package org.acme.controller;

import org.acme.Repository.MessageRepository;
import org.acme.Repository.UserRepository;
import org.acme.model.MessageModel;
import org.acme.model.UserModel;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/message")
@Transactional
public class MessageController {

    @Inject
    MessageRepository repository;
    @Inject
    UserRepository userRepository;

   @GET
   @Path("/save/{text}/{idUser}")
   @Produces(MediaType.APPLICATION_JSON)
   public MessageModel save(@PathParam("text") String text, @PathParam("idUser") Long idUser) {

      MessageModel message = new MessageModel();
      message.setText(text);
      repository.persistAndFlush(message);

      UserModel user = userRepository.findById(idUser);
      if (user == null)
         throw new BadRequestException("User not found");

      user.addMessage(message);
      userRepository.persistAndFlush(user);

      return message;
   }

}