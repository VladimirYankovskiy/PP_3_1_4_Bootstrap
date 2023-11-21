package ru.kata.spring.bootstrap.models;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String role;

    public Role() {
    }

    public Role(String role) {
        this.role = role;
    }

    public void setRole(String role) {

        this.role = role;
    }

    @Override
    public String getAuthority() {
        return getRole();
    }
}
