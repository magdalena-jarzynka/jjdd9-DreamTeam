package com.infoshareacademy.dreamteam.repository;

import com.infoshareacademy.dreamteam.domain.entity.Role;
import com.infoshareacademy.dreamteam.role.RoleType;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Optional;

@Stateless
public class RoleRepositoryBean {

    @PersistenceContext
    private EntityManager entityManager;

    public Optional<Role> findByRoleType(RoleType roleType) {
        Query query = entityManager.createNamedQuery("Role.findByRoleType");
        query.setParameter("roleType", roleType);
        return query.getResultList().stream().findFirst();
    }

}
