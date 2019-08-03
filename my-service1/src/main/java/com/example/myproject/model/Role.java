package com.example.myproject.model;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="role")
public class Role extends BaseEntity {

    @Column(name = "role_name")
    private String roleName;
    
    public Role() {
    }

    public Role(int id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName.toUpperCase();
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName.toUpperCase();
    }
}
