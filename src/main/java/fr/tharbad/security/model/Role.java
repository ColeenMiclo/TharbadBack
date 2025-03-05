package fr.tharbad.security.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public enum Role {
    USER, 
    ADMIN, 
    BAR, 
    SHOP
}
