package fr.tharbad.security.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public enum Role {
    ROLE_USER, 
    ROLE_ADMIN, 
    ROLE_BAR, 
    ROLE_SHOP
}
