package fr.tharbad.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.tharbad.security.models.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String role);

}
