package org.acme.Repository;

import org.acme.model.ChannelModel;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.Dependent;

@Dependent
public class ChannelRepository implements PanacheRepository<ChannelModel>{
    
}
