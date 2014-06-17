package org.joolzminer.examples.sip.web.controllers;

import javax.validation.Valid;

import org.joolzminer.examples.sip.domain.Message;
import org.joolzminer.examples.sip.domain.UserDetailsAdapter;
import org.joolzminer.examples.sip.services.ForumsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MessageController {

	@InitBinder("message")
	public void initBinder(WebDataBinder binder) {
		binder.setAllowedFields(new String[] { "subject", "text" });
	}

	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory
			.getLogger(MessageController.class);

	@Autowired
	private ForumsService forumsService;

	@RequestMapping(value = "/forums/{forumId}/messages/{messageId}", method = RequestMethod.GET)
	public String getMessage(@PathVariable("forumId") Long forumId,
			@PathVariable("messageId") Long messageId, Model model) {
		model.addAttribute(getMessageFromForum(forumId, messageId));
		return "forums/message";
	}

	@RequestMapping(value = "/forums/{forumId}/messages/new", method = RequestMethod.GET)
	public String getNewMessageForm(@PathVariable("forumId") Long forumId,
			Model model) {
		Message message = new Message();
		message.setForum(forumsService.getForum(forumId, false));
		model.addAttribute(message);
		return "forums/newMessageForm";
	}

	@RequestMapping(value = "/forums/{forumId}/messages/new", method = RequestMethod.POST)
	public String postMessageForm(@PathVariable("forumId") Long forumId,
			@ModelAttribute @Valid Message message, BindingResult result) {

		message.setForum(forumsService.getForum(forumId, false));

		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		UserDetailsAdapter userDetailsAdapter = (UserDetailsAdapter) authentication
				.getPrincipal();
		message.setAuthor(userDetailsAdapter.getAccount());
		message.setVisible(true);
		forumsService.createMessage(message, result);
		if (result.hasErrors()) {
			return "forums/newMessageForm";
		} else {
			return "redirect:/forums/" + forumId;
		}
	}

	@RequestMapping(value = "/forums/{forumId}/messages/{messageId}/edit", method = RequestMethod.GET)
	public String getEditMessageForm(@PathVariable("forumId") Long forumId,
			@PathVariable("messageId") Long messageId, Model model) {
		Message message = getMessageFromForum(forumId, messageId);
		model.addAttribute("originalMessage", message);
		model.addAttribute(message);
		return "forums/editMessageForm";
	}

	@RequestMapping(value = "/forums/{forumId}/messages/{messageId}", method = RequestMethod.PUT)
	public String putMessageForm(@PathVariable("forumId") Long forumId,
			@PathVariable("messageId") Long messageId,
			@ModelAttribute @Valid Message messageDto, BindingResult result,
			Model model) {
		Message message = getMessageFromForum(forumId, messageId);
		message.setSubject(messageDto.getSubject());
		message.setText(messageDto.getText());
		forumsService.updateMessageSubjectAndText(message);

		if (result.hasErrors()) {
			model.addAttribute("originalMessage", message);
			return "forums/editMessageForm";
		} else {
			return "redirect:/forums/" + forumId + "/messages/" + messageId
					+ "/edit?saved=true";
		}
	}

	@RequestMapping(value = "/forums/{forumId}/messages/{messageId}/visible", method = RequestMethod.GET)
	public String putMessageVisibility(@PathVariable("forumId") Long forumId, @PathVariable("messageId") Long messageId, 
										@RequestParam(value = "block") boolean block, Model model) {
		Message message = getMessageFromForum(forumId, messageId);
		message.setVisible(!block);
		forumsService.updateMessageVisibility(message);
		model.addAttribute(message);
		return "redirect:/forums/" + forumId + "/messages/" + messageId;
	}

	private Message getMessageFromForum(Long forumId, Long messageId) {
		Message unverifiedMessage = forumsService.getMessage(messageId);
		Assert.isTrue(forumId.equals(unverifiedMessage.getForum().getId()),
				"Message is not associated to that Forum");
		return unverifiedMessage;
	}
}
