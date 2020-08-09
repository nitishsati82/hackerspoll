package com.poll.app.services;

import java.util.List;

import com.poll.app.data.CandidateData;

public interface CandidateService {
	public List<CandidateData> getCandidateDataDetails();
	public List<CandidateData> getLabelsTaksDetails(int userIndex,String toDoLabel,String sessionKey);
	public CandidateData saveCandidate(CandidateData CandidateData);
	public CandidateData updateCandidateData(int userIndex, int toDoId,String status,String sessionKey);
	public String deleteCandidateData(int userIndex);
	public String updateCandidateData(CandidateData CandidateData);
	public String voteToHacker(int userIndex);

}
