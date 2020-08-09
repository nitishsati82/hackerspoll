package com.poll.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path = "/poll")
public class PageController {
	@RequestMapping(path = "/admin",method = RequestMethod.GET)
	public String adminUrl() {
		return "admin";
	}
	@RequestMapping(path = "/voting",method = RequestMethod.GET)
	public String votingUrl() {
		return "voting";
	}
}
