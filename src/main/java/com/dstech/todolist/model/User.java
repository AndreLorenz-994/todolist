package com.dstech.todolist.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.dstech.todolist.model.Role;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String email;
    private String password;
	
	// Upload files. 
	@Lob
	private byte[] image;
	private String typeImage;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;

    public User() {}

    public User(String email, String password, byte[] image, String typeImage) {
        this.email = email;
        this.password = password;
        this.image= image;
        this.typeImage=typeImage;
    }

    public User(String email, String password, byte[] image , String typeImage, Collection<Role> roles) {
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.image= image;
        this.typeImage=typeImage;
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
	
	public byte[] getImage() {
		return image;
	}
	
	public void setImage(byte[] image) {
		this.image= image;
	}
	
	public String getTypeImage() {
		return typeImage;
	}

	public void setTypeImage(String typeImage) {
		this.typeImage = typeImage;
	}

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }	
	
}
