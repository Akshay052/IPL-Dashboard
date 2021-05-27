package com.akshay.dashboard.data.Model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Team {

	@Override
	public String toString() {
		return "Team [teamName=" + teamName + ", teamMatches=" + teamMatches + ", totalWins=" + totalWins + "]";
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String teamName;
	private Long teamMatches;
	private Long totalWins;
	
	@Transient
	private List<Match> matches;
	
	public List<Match> getMatches() {
		return matches;
	}
	public void setMatches(List<Match> matches) {
		this.matches = matches;
	}
	public Team() {
		super();
	}
	public Team(String teamName, Long teamMatches) {
		super();
		this.teamName = teamName;
		this.teamMatches = teamMatches;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public Long getTeamMatches() {
		return teamMatches;
	}
	public void setTeamMatches(Long teamMatches) {
		this.teamMatches = teamMatches;
	}
	public Long getTotalWins() {
		return totalWins;
	}
	public void setTotalWins(Long totalWins) {
		this.totalWins = totalWins;
	}
}
