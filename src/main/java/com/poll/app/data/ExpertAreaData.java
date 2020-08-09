package com.poll.app.data;

import java.util.Comparator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="expert_area")
public class ExpertAreaData implements Comparator<ExpertAreaData> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="expertarea")
	private String expertarea;

	public int getId() {
		return id;
	}

	public String getExpertarea() {
		return expertarea;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setExpertarea(String expertarea) {
		this.expertarea = expertarea;
	}

	@Override
	public int compare(ExpertAreaData o1, ExpertAreaData o2) {
		return o1.getExpertarea().compareTo(o2.getExpertarea());
	}
}
