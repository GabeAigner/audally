package com.audally.backend.control;

import com.audally.backend.entity.User;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional
public class UserRepository implements PanacheRepositoryBase<User,Long> {

}
