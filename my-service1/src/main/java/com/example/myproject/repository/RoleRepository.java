package com.example.myproject.repository;

import java.util.Optional;

import com.example.myproject.model.Role;

public interface RoleRepository extends BaseRepository<Role> {
	Optional<Role> findByRoleName(String roleName);
}
