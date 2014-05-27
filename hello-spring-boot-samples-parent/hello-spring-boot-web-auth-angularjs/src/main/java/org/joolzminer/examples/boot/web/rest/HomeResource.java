package org.joolzminer.examples.boot.web.rest;

import java.util.ArrayList;
import java.util.List;

import org.joolzminer.examples.boot.web.rest.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/sip")
public class HomeResource {
	
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(HomeResource.class);
	
	@RequestMapping(value = "/rest/home", method = RequestMethod.GET, produces = "application/json")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<UserDTO> getUserInformation() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String username = securityContext.getAuthentication().getPrincipal().toString();
        
        List<String> roles = new ArrayList<>();
        for (GrantedAuthority authority : securityContext.getAuthentication().getAuthorities()) {
        	roles.add(authority.getAuthority());
        }
        
        return new ResponseEntity<UserDTO>(new UserDTO(username, roles), HttpStatus.OK);
	}
}
