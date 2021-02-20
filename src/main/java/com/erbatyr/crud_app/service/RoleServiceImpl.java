package com.erbatyr.crud_app.service;

import com.erbatyr.crud_app.dao.RoleDAO;
import com.erbatyr.crud_app.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {
    RoleDAO roleDAO;

    @Autowired
    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    @Transactional
    public Set<Role> rolesSet() {
        return roleDAO.rolesSet();
    }

    @Override
    public Role getRoleById(int id) {
        return roleDAO.getRoleById(id);
    }
}
