package org.joolzminer.examples.sip.web.controllers;

import org.joolzminer.examples.sip.domain.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/nominee")
public class NomineeController {
	private static final Logger LOGGER = LoggerFactory.getLogger(NomineeController.class);
	
	@Value("${application.viewNames.thanks}")
	private String thanksViewName;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView form() {
		return new ModelAndView("/nominee/form", "nominee", new Member());
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String processFormData(@ModelAttribute("nominee") Member member, final RedirectAttributes redirectAttributes) {
		LOGGER.debug("processing vote for {}", member);
		redirectAttributes.addFlashAttribute("nominee", member);
		return thanksViewName;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAllowedFields(new String[] {
				"firstName",
				"lastName"
		});
	}
}
