package com.example.myproject.model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="role")
public class Role extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 7669272186961288831L;
	
	@Column(name = "role_name", nullable = false, length=20)
    private String roleName;
    
    public Role() {
    }

 
    @JsonIgnore
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
