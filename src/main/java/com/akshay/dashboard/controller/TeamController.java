package com.akshay.dashboard.controller;

import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.akshay.dashboard.data.Model.Match;
import com.akshay.dashboard.data.Model.Team;
import com.akshay.dashboard.repository.MatchRepository;
import com.akshay.dashboard.repository.TeamRepository;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

@RestController
@CrossOrigin
public class TeamController {

	private TeamRepository teamRepository;
	private MatchRepository matchRepository;

	
	public TeamController(TeamRepository teamRepository, MatchRepository matchRepository) {
		super();
		this.teamRepository = teamRepository;
		this.matchRepository=matchRepository;
	}
	@GetMapping("/team")
	public Iterable<Team> getTeam() {
		
		try {
		 return this.teamRepository.findAll();
		}
		catch(Exception e) {
			System.out.println(e);
			return null;
		}	
	}

	@GetMapping("/team/{teamName}")
	public Team getTeam(@PathVariable String teamName) {
		Team team = new Team();
		try {
		System.out.println("TeamName:="+teamName);
		 team = this.teamRepository.findByTeamName(teamName);
		team.setMatches(this.matchRepository.findLatestMatchesByTeam(teamName, 4));
		System.out.println("Team:="+team);
		return team;
		}
		catch(Exception e) {
			System.out.println(e);
			return null;
		}	
	}
	
	@GetMapping("/team/{teamName}/matches")
	public List<Match> getMatchsForTeam(@PathVariable String teamName,@RequestParam int year) {
		try {
		System.out.println("TeamName+year:="+teamName+"+"+year);
		LocalDate date1 = LocalDate.of(year,1,1);	
		LocalDate date2 = LocalDate.of(year+1,1,1);		

		// return this.matchRepository.getByTeam1AndDateBetweenOrTeam2AndDateBetweenOrderByDateDesc(teamName, date1, date2, teamName, date1, date2);
		return this.matchRepository.getMatchesByTeamBetweenDates(teamName, date1, date2);
		
		}
		catch(Exception e) {
			System.out.println(e);
			return null;
		}	
	}
}
