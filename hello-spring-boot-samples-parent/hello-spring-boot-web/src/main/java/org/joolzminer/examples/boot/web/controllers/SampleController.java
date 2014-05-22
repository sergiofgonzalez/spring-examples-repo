package org.joolzminer.examples.boot.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController {

	@RequestMapping("/")
	public @ResponseBody String home() {
		return "Hello to Jason Isaacs!!!";
	}
}
