package com.poll.app.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poll.app.data.VoterData;
import com.poll.app.repos.VoterRepos;
import com.poll.app.services.VoterServices;

@Service("voterservice")
public class VoterServImpl implements VoterServices {

	@Autowired
	VoterRepos repos;
	
	@Override
	public VoterData save(VoterData data) {
		return repos.saveAndFlush(data);
	}

	@Override
	public String getVoterIp(String ip, int candidateId) {
		return repos.getVote(ip, candidateId);
	}

}
