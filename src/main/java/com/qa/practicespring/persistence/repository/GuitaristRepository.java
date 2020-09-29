package com.qa.practicespring.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.practicespring.persistence.domain.Guitarist;

@Repository
public interface GuitaristRepository extends JpaRepository<Guitarist, Long> {

	
	//J		 - Java
	//P		 - Persistence
	//A -  A - Application
		// P - Programming
		// I - Interface
}
