package com.poll.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/")
public class DefaultController {
	@RequestMapping(path = "/admin",method = RequestMethod.GET)
	public String adminUrl() {
		return "admin";
	}
	@RequestMapping(path = "/voting",method = RequestMethod.GET)
	public String votingUrl() {
		return "voting";
	}
}
