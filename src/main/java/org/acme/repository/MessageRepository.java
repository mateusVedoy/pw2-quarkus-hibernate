package org.acme.repository;

import org.acme.model.MessageModel;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.Dependent;

@Dependent
public class MessageRepository implements PanacheRepository<MessageModel> {
    
}
