package com.aleixo.lbd.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aleixo.lbd.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

	@Query(value = "SELECT u FROM User u")
	List<User> findAllUsers();

	@Query(value = "SELECT u FROM User u WHERE u.name = :name")
	Optional<User> findByName(@Param("name") String name);
}
