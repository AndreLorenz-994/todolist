package com.dstech.todolist.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dstech.todolist.dao.UserRegistrationDao;
import com.dstech.todolist.model.Activity;
import com.dstech.todolist.model.Role;
import com.dstech.todolist.model.User;
import com.dstech.todolist.repository.UserRepository;

@Service
public class UserServiceImp implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;	

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
	    User user = userRepository.findByEmail(email);
	    if (user == null) {
	        throw new UsernameNotFoundException("Invalid username or password.");
	    }
	    return new org.springframework.security.core.userdetails.User(user.getEmail(),
	        user.getPassword(),
	        mapRolesToAuthorities(user.getRoles()));
	}

	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.findByEmail(email);
	}

	@Override
	public User save(UserRegistrationDao registration) {
		// TODO Auto-generated method stub
	    User user = new User();
	    user.setEmail(registration.getEmail()); 
	    user.setPassword(passwordEncoder.encode(registration.getPassword()));
	    user.setImage(registration.getImage());
	    user.setRoles(Arrays.asList(new Role("USER")));
	    return userRepository.save(user);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return userRepository.count();
	}

	@Override
	public void deleteById(Long userId) {
		// TODO Auto-generated method stub
		userRepository.deleteById(userId);
	}

	private Collection < ? extends GrantedAuthority > mapRolesToAuthorities(Collection<Role> roles) {
	    return roles.stream()
	        .map(role -> new SimpleGrantedAuthority(role.getName()))
	        .collect(Collectors.toList());
	}

	@Override
	public void addActivities(User user, List<Activity> activities) {
		// TODO Auto-generated method stub
		user.setActivities(activities);
		userRepository.save(user);
	}
	
}