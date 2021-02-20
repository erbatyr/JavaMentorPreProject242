package com.erbatyr.crud_app.dao;

import com.erbatyr.crud_app.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleDAO {
    Set<Role> rolesSet();
    Role getRoleById(int id);
}
