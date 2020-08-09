package com.poll.app.services;

import java.util.List;

import com.poll.app.data.CandidateExpertArea;

public interface CandExpAreaService {
	public List<CandidateExpertArea> getAllExpertArea(int userId);
	public CandidateExpertArea save(CandidateExpertArea expertlData);
	public List<CandidateExpertArea> saveAll(List<CandidateExpertArea> expertlData);
	public CandidateExpertArea updateExpt(CandidateExpertArea expertlData);
	public List<CandidateExpertArea> updateAll(List<CandidateExpertArea> expertlData,int candId);

	public String deleteExpt(int candId,int id);

}
