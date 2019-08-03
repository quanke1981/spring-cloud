package com.example.myproject.model;

import java.util.Optional;
import java.util.Set;

import javax.persistence.*;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.myproject.repository.RoleRepository;

@Entity
@Table(name="role")
public class Role extends BaseEntity {

	
    @Column(name = "role_name")
    private String roleName;
    
    public Role() {
    }

    @ManyToMany(fetch=FetchType.LAZY, mappedBy="roles")
    private Set<User> users;
    
    
    public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Role(int id, String roleName) {
        this.id = id;
        this.setRoleName(roleName);
    }

    public String getRoleName() {
        return roleName.toUpperCase();
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName.toUpperCase();
        
    }
    
    
}
