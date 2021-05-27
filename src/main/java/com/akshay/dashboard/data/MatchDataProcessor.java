package com.akshay.dashboard.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.akshay.dashboard.data.Model.Match;

public class MatchDataProcessor implements ItemProcessor<MatchInput, Match>{

	private static final Logger log = LoggerFactory.getLogger(MatchDataProcessor.class);

	  @Override
	  public Match process(final MatchInput matchInput) throws Exception {
	   
		  Match match= new Match();
		  match.setId(Long.parseLong(matchInput.getId()));
		  match.setCity(matchInput.getCity());
		  //DateTimeFormatter df = DateTimeFormatter.ofPattern("YYYY-MM-DD");
		  //System.out.println("LocalDate.parse(matchInput.getDate(),df)"+LocalDate.parse(matchInput.getDate(),df));
		  match.setDate(LocalDate.parse(matchInput.getDate()));
		  match.setPlayerOfMatch(matchInput.getPlayer_of_match());
		  match.setMatchWinner(matchInput.getWinner());
		  match.setVenue(matchInput.getVenue());
		  
		  //set Team1 and Team2 depending on Innings Order
		  String firstInningsTeam, secondInningsTeam;
		  if(matchInput.getToss_decision()=="bat") {
			  firstInningsTeam = matchInput.getToss_winner();
			  secondInningsTeam = matchInput.getToss_winner().equals(matchInput.getTeam1())
					  			? matchInput.getTeam2() : matchInput.getTeam1();
		  }  else {
			  secondInningsTeam = matchInput.getToss_winner();
			  firstInningsTeam = matchInput.getToss_winner().equals(matchInput.getTeam1())
			  			? matchInput.getTeam2() : matchInput.getTeam1();
		  }
		  
		  match.setTeam1(firstInningsTeam);
		  match.setTeam2(secondInningsTeam);
		  match.setTossWinner(matchInput.getToss_winner());
		  match.setTossDecision(matchInput.getToss_decision());
		  match.setResult(matchInput.getResult());
		  match.setResultMargin(matchInput.getResult_margin());
		  match.setUmpire1(matchInput.getUmpire1());
		  match.setUmpire2(matchInput.getUmpire2());

		 
	    return match;
	  }
}
