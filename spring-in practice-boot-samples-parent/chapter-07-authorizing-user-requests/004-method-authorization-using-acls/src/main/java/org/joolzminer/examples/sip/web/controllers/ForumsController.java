package org.joolzminer.examples.sip.web.controllers;

import org.joolzminer.examples.sip.services.ForumsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ForumsController {
	@Autowired
	private ForumsService forumsService;
	
	@RequestMapping(value = "/forums", method = RequestMethod.GET)
	public String getForums(Model model) {
		model.addAttribute("forums", forumsService.getForums());
		return "forums/forumsList";
	}
	
	@RequestMapping(value = "/forums/{id}", method = RequestMethod.GET)
	public String getForum(@PathVariable("id") Long id, Model model) {
		model.addAttribute(forumsService.getForum(id, true));
		return "forums/forum";
	}
}
