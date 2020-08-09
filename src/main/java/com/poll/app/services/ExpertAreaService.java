package com.poll.app.services;

import java.util.List;

import com.poll.app.data.ExpertAreaData;

public interface ExpertAreaService {
	public List<ExpertAreaData> getExpertArea(int userId, String session);
	public List<ExpertAreaData> getAllExpertArea(int userId);
	public int addNewExtArea(ExpertAreaData expertlData);
	public ExpertAreaData save(ExpertAreaData expertlData);
	public ExpertAreaData updateExpt(ExpertAreaData expertlData);
	public ExpertAreaData deleteExpt(ExpertAreaData expertlData);

}
