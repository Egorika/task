package com.example.service;

import java.util.List;

import com.example.model.User;

public interface UserService {
	public User findUserByEmail(String email);
	public User findUserByEmail(String email, Long id);
	public User findUserById(Long id);
	public User findUserByPassportId(String passportid);
	public User findUserByPassportId(String passportid, Long id);
	public void saveUser(User user);
	public List<User> findAll();
}
