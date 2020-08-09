package com.poll.app.services;

import com.poll.app.data.VoterData;

public interface VoterServices {
	public VoterData save(VoterData data);
	public String getVoterIp(String ip,int candidateId);
}
