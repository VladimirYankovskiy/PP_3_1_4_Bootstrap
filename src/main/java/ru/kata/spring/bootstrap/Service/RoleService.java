package ru.kata.spring.bootstrap.Service;

import ru.kata.spring.bootstrap.models.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();

    Role getRoleByName(String role);

    List<Role> getSetOfRoles(String[] roleNames);

    void addRole(Role role);

    void editRole(Role role);

    Role getById(Long id);
}
