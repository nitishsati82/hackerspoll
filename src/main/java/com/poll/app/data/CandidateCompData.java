package com.poll.app.data;

import java.util.List;

public class CandidateCompData {
	private CandidateData candData;
	private List<CandidateExpertArea> candidateExpertArea;
	public CandidateData getCandData() {
		return candData;
	}
	public List<CandidateExpertArea> getCandidateExpertArea() {
		return candidateExpertArea;
	}
	public void setCandData(CandidateData candData) {
		this.candData = candData;
	}
	public void setCandidateExpertArea(List<CandidateExpertArea> candidateExpertArea) {
		this.candidateExpertArea = candidateExpertArea;
	}


}
