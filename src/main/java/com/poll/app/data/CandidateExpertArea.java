package com.poll.app.data;

import java.util.Comparator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="candidate_expert_area")
public class CandidateExpertArea implements Comparator<CandidateExpertArea> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="candidate_id")
	private int candidate_id;
	
	@Column(name="expertarea")
	private String expertarea;
	
	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	@Column(name="deleted")
	private String deleted;
	
	@Column(name="rating")
	private int rating;

	public int getId() {
		return id;
	}

	public int getCandidate_id() {
		return candidate_id;
	}

	public String getExpertarea() {
		return expertarea;
	}

	public int getRating() {
		return rating;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCandidate_id(int candidate_id) {
		this.candidate_id = candidate_id;
	}

	public void setExpertarea(String expertarea) {
		this.expertarea = expertarea;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	@Override
	public int compare(CandidateExpertArea o1, CandidateExpertArea o2) {
		return 0;
	}
	
	
}
