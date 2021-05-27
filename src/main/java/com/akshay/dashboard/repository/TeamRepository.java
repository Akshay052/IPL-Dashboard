package com.akshay.dashboard.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.akshay.dashboard.data.Model.Team;

public interface TeamRepository extends CrudRepository< Team , Long> {

	Team findByTeamName(String teamName);
	List<Team> findAll();
	
}
