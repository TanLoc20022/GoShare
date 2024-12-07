package com.example.GoShare.service.impl;

import org.springframework.stereotype.Service;

import com.example.GoShare.model.Role;
import com.example.GoShare.repository.RoleRepository;
import com.example.GoShare.service.RoleService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role getRoleById(Integer roleId) {
        return roleRepository.findById(roleId).get();
    }

    @Override
    public Role getRoleByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName).get();
    }

    @Override
    public Role addRole(String roleName) {
        Role role = Role.builder().roleName(roleName).build();
        return roleRepository.save(role);
    }

    @Override
    public boolean existsByRoleName(String roleName) {
        return roleRepository.existsByRoleName(roleName);
    }
}
