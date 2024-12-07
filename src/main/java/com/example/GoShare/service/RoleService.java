package com.example.GoShare.service;

import com.example.GoShare.model.Role;

public interface RoleService {
        public Role getRoleById(Integer roleId);

        public Role getRoleByRoleName(String roleName);

        public boolean existsByRoleName(String roleName);

        public Role addRole(String roleName);
}
