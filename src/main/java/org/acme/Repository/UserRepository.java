package org.acme.Repository;

import org.acme.model.UserModel;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.Dependent;

@Dependent
public class UserRepository implements PanacheRepository<UserModel>{}
