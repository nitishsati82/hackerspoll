package com.poll.app.data;

import java.util.Comparator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="candidate_list")
public class CandidateData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="hackername")
	private String hackername;
	
	@Column(name="solved_challanges")
	private Integer solved_challanges;
	
	@Column(name="explevel")
	private Integer explevel;

	@Column(name="vote")
	private int vote=0;
	
	public Integer getSolved_challanges() {
		return solved_challanges;
	}

	public Integer getExplevel() {
		return explevel;
	}

	public int getVote() {
		return vote;
	}

	public void setSolved_challanges(Integer solved_challanges) {
		this.solved_challanges = solved_challanges;
	}

	public void setExplevel(Integer explevel) {
		this.explevel = explevel;
	}

	public void setVote(int vote) {
		this.vote = vote;
	}

	public int getId() {
		return id;
	}

	public String getHackername() {
		return hackername;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setHackername(String hackername) {
		this.hackername = hackername;
	}

	
	public static Comparator<CandidateData> byName = (CandidateData can1,CandidateData can2)->can1.getHackername().compareTo(can2.getHackername());
	public static Comparator<CandidateData> byChallange = (CandidateData can1,CandidateData can2)->can1.getSolved_challanges().compareTo(can2.getSolved_challanges());
	public static Comparator<CandidateData> byExp = (CandidateData can1,CandidateData can2)->can1.getExplevel().compareTo(can2.getExplevel());
}
