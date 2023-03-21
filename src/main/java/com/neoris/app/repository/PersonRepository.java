package com.neoris.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.neoris.app.entity.Person;

@NoRepositoryBean
public interface PersonRepository extends JpaRepository<Person, Long>{

}
