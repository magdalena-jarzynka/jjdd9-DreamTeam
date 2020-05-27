package com.infoshareacademy.dreamteam.dao;

import com.infoshareacademy.dreamteam.cdi.RoleType;
import com.infoshareacademy.dreamteam.domain.entity.Role;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class RoleDaoBean {

    @PersistenceContext
    private EntityManager entityManager;


    public Role findByRoleType(RoleType roleType) {
        Query query = entityManager.createNamedQuery("Role.findByRoleType");
        query.setParameter("roleType", roleType);
        return (Role) query.getResultList().get(0);
    }

}
