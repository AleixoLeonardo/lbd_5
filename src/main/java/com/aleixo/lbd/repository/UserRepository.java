package com.aleixo.lbd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aleixo.lbd.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

	@Query(value = "SELECT u FROM User u")
	List<User> findAllUsers();
}
