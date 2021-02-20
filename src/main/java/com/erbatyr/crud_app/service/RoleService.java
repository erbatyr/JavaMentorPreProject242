package com.erbatyr.crud_app.service;

import com.erbatyr.crud_app.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    Set<Role> rolesSet();
    Role getRoleById(int id);
}
