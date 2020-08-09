package com.poll.app.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="voter_details")
public class VoterData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="voter_ip")
	private String voter_ip;
	
	@Column(name="candidate_id")
	private int candidate_id;

	public int getId() {
		return id;
	}

	public String getVoter_ip() {
		return voter_ip;
	}

	public int getCandidate_id() {
		return candidate_id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setVoter_ip(String voter_ip) {
		this.voter_ip = voter_ip;
	}

	public void setCandidate_id(int candidate_id) {
		this.candidate_id = candidate_id;
	}
	
}
