package com.example.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.model.Role;
import com.example.model.User;
import com.example.repository.RoleRepository;
import com.example.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	@Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
    @Override
    @Transactional
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

    @Override
    @Transactional
	public User findUserByEmail(String email, Long id) {
		return userRepository.findByEmail(email, id);
	}
	
	@Override
	@Transactional
	public User findUserById(Long id) {
		return userRepository.findOne(id);
	}

	@Override
	@Transactional
	public User findUserByPassportId(String passportid) {
		return userRepository.findByPassportId(passportid);
	}
	
	@Override
	@Transactional
	public User findUserByPassportId(String passportid, Long id) {
		return userRepository.findByPassportId(passportid, id);
	}
	
	@Override
	@Transactional
	public List<User> findAll() {
		return userRepository.findAll();
	}
	@Override
	@Transactional
	public void saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(2);
        Role userRole = roleRepository.findByRole("USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}
}
