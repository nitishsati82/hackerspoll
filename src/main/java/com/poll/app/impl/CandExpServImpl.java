package com.poll.app.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poll.app.data.CandidateExpertArea;
import com.poll.app.repos.CandidateExprRepos;
import com.poll.app.services.CandExpAreaService;

@Service("canexpertarea")
public class CandExpServImpl implements CandExpAreaService{

	@Autowired
	CandidateExprRepos repos;
	
	@Override
	public List<CandidateExpertArea> getAllExpertArea(int candidate_id) {
		// TODO Auto-generated method stub
		return repos.getCandidateList(candidate_id);
	}

	@Override
	public CandidateExpertArea save(CandidateExpertArea expertlData) {
		// TODO Auto-generated method stub
		return repos.save(expertlData);
	}

	@Override
	public List<CandidateExpertArea> saveAll(List<CandidateExpertArea> expertlData) {
		List<CandidateExpertArea> list = repos.saveAll(expertlData);
		return list;
	}

	@Override
	public CandidateExpertArea updateExpt(CandidateExpertArea expertlData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CandidateExpertArea> updateAll(List<CandidateExpertArea> expertlData,int id) {
		List<CandidateExpertArea> list = null;
		int update = repos.updateDelete(id);
		if(update>0) {
			int delete = repos.deleteAll(id);
			if(delete>0) list = repos.saveAll(expertlData);
		}
		return list;
	}

	@Override
	public String deleteExpt(int candId, int id) {
		int delete = repos.deleteSelected(candId, id);
		if(delete>0)return "Y";
		return "N";
	}

	
}
