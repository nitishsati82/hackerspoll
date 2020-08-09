package com.poll.app.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poll.app.data.CandidateData;
import com.poll.app.repos.CandidateRepos;
import com.poll.app.services.CandidateService;

@Service("candidateservice")
public class CandidateServImpl implements CandidateService{

	@Autowired
	CandidateRepos repos;
	@Override
	public List<CandidateData> getCandidateDataDetails() {
		// TODO Auto-generated method stub
		return repos.getCandidateList();
	}

	@Override
	public List<CandidateData> getLabelsTaksDetails(int userIndex, String toDoLabel, String sessionKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CandidateData saveCandidate(CandidateData CandidateData) {
		CandidateData data = repos.saveAndFlush(CandidateData);
		return data;
	}

	@Override
	public CandidateData updateCandidateData(int userIndex, int toDoId, String status, String sessionKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteCandidateData(int id) {
		int delete = repos.deleteCandidate(id);
		if(delete>0) return "Y";
		return "N";
	}

	@Override
	public String updateCandidateData(CandidateData CandidateData) {
		int update = repos.updatesUserLabel(CandidateData.getHackername(), CandidateData.getSolved_challanges(), CandidateData.getExplevel(), CandidateData.getId());
		if(update>0)return "Y";
		return "N";
	}

	@Override
	public String voteToHacker(int id) {
		int vote = repos.getVote(id);
		int voteSuccess = repos.voteToCandidate(vote+1, id);
		if(voteSuccess>0) {
			return "Y";
		}
		return "N";
	}

}
