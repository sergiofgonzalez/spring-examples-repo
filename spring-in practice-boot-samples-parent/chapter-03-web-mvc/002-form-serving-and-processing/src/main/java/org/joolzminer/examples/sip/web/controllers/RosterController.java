package org.joolzminer.examples.sip.web.controllers;

import java.util.ArrayList;
import java.util.List;

import org.joolzminer.examples.sip.domain.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/roster")
public class RosterController {
	
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(RosterController.class);
	
	private List<Member> members = new ArrayList<>();
	
	public RosterController() {
		members.add(new Member("John", "Lennon"));
		members.add(new Member("Paul", "McCartney"));
		members.add(new Member("George", "Harrison"));
		members.add(new Member("Ringo", "Starr"));
		members.add(new Member("Brian", "Epstein"));
	}
	
	
	@RequestMapping("/list")
	public void list(Model model) {
		model.addAttribute(members);
	}

	@RequestMapping("/member")
	public void member(@RequestParam("id") int id, Model model) {
		model.addAttribute(members.get(id));
	}
}


