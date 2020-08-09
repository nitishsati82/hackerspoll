package com.poll.app.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poll.app.data.ExpertAreaData;
import com.poll.app.repos.ExptAreaRepos;
import com.poll.app.services.ExpertAreaService;

@Service("expertAreaServices")
public class ExptAreaServiceImpl implements ExpertAreaService {

	@Autowired
	ExptAreaRepos expRepos;
	
	@Override
	public List<ExpertAreaData> getExpertArea(int userId, String session) {
		List<ExpertAreaData> list = expRepos.getExpertAreas();
		Collections.sort(list, new ExpertAreaData());
		System.out.println(expRepos.getExpertAreas().size());
		return list;
	}

	@Override
	public int addNewExtArea(ExpertAreaData expertlData) {
		
		return 0;
	}

	@Override
	public ExpertAreaData save(ExpertAreaData expertlData) {
		ExpertAreaData data = expRepos.matchExpertArea(expertlData.getExpertarea());;
		if(data!=null && data.getExpertarea().equalsIgnoreCase(expertlData.getExpertarea())) {
			data.setId(-1);
			return data;
		}
		return expRepos.save(expertlData);
	}

	@Override
	public ExpertAreaData updateExpt(ExpertAreaData expertlData) {
		ExpertAreaData data = new ExpertAreaData();
		int update = expRepos.updateExprtArea(expertlData.getExpertarea(), expertlData.getId());
		if(update>0) {
			data = expRepos.getExpertArea();
		}else {
			data.setId(-1);
		}
		return data;
	}

	@Override
	public ExpertAreaData deleteExpt(ExpertAreaData expertlData) {
		ExpertAreaData data = new ExpertAreaData();
		int delete = expRepos.deleteExprtArea(expertlData.getId());
		if(delete>0) {
			data = expRepos.getExpertArea();
		}else {
			data.setId(-1);
		}
		return data;
	}

	@Override
	public List<ExpertAreaData> getAllExpertArea(int userId) {
		// TODO Auto-generated method stub
		return expRepos.getAllExpertAreas(userId);
	}

}
