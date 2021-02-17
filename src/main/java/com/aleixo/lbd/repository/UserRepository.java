package com.aleixo.lbd.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aleixo.lbd.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{

}
