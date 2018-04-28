package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.model.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);

	@Query(value = "SELECT * FROM USER WHERE EMAIL = ?1 AND NOT USER_ID = ?2", nativeQuery = true)
	User findByEmail(String email, Long id);

	@Query(value = "SELECT * FROM USER WHERE PASSPORTID = ?1", nativeQuery = true)
	User findByPassportId(String passportid);

	@Query(value = "SELECT * FROM USER WHERE PASSPORTID = ?1 AND NOT USER_ID = ?2", nativeQuery = true)
	User findByPassportId(String passportid, Long id);

}
