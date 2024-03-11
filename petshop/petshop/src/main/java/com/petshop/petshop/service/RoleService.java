package com.petshop.petshop.service;

import com.petshop.petshop.mappper.dto.RoleDto;
import com.petshop.petshop.model.Role;

import java.util.List;

public interface RoleService {

    public RoleDto getRoleDtoById(Long id);

    public RoleDto findByRoleDto(String role);

    public List<RoleDto> getAllRoleDtos();
    public List<Role> getAllRoles();

    public RoleDto createRoleDto(Role role);

    public RoleDto updateRoleDto(Role role);

    public void deleteRole(Role role);

}