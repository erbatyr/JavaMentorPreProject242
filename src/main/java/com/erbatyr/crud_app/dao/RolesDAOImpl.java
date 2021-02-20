package com.erbatyr.crud_app.dao;

import com.erbatyr.crud_app.model.Role;
import com.erbatyr.crud_app.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class RolesDAOImpl implements RoleDAO{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Set<Role> rolesSet() {
        Set<Role> roles = new HashSet<>(entityManager.createQuery("select role from Role role", Role.class).getResultList());
        return roles;
    }

    @Override
    public Role getRoleById(int id) {
        Role role = entityManager.find(Role.class, id);
        entityManager.detach(role);
        return role;
    }
}
