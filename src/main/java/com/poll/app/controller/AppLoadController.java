package com.poll.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.poll.app.data.ExpertAreaData;
import com.poll.app.services.ExpertAreaService;

@RestController
@RequestMapping(path = "/data/")
public class AppLoadController {
	@Autowired
	ExpertAreaService expArea;
	
	@RequestMapping(value = "/expertArea", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<ExpertAreaData> goToHomePage() {
		List<ExpertAreaData> list =  expArea.getExpertArea(1, "");
		return list;
	}
	
	@RequestMapping(value = "/saveExprtArea/", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody ExpertAreaData saveExprtArea(@RequestBody ExpertAreaData labelData) {
		System.out.println("called to save controller.");
		return expArea.save(labelData);
	}
	
	@RequestMapping(value = "/updateExprtArea/", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody ExpertAreaData updateExprtArea(@RequestBody ExpertAreaData expertlData) {
		System.out.println("called to update controller.");
		return expArea.updateExpt(expertlData);
	}
	
	@RequestMapping(value = "/deleteExprtArea/", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody ExpertAreaData deleteExprtArea(@RequestBody ExpertAreaData expertlData) {
		System.out.println("called to delete controller.");
		return expArea.deleteExpt(expertlData);
	}
	
	@RequestMapping(value = "/getAllExpertArea/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<ExpertAreaData> getExpertAreas(@PathVariable("id") String id) {
		System.out.println("called to get controller.");
		return expArea.getAllExpertArea(Integer.parseInt(id));
	}
}
