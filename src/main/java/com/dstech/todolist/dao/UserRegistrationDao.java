package com.dstech.todolist.dao;


import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class UserRegistrationDao {

    @NotEmpty
    private String password;
    
    @Email
    @NotEmpty
    private String email;
    
    @Transient
    private byte[] image;
    
    @NotEmpty
    private String typeImage;    

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
	
}
