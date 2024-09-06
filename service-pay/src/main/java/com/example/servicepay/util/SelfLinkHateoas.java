package com.example.servicepay.util;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;


public class SelfLinkHateoas {

	private SelfLinkHateoas() {	}
	
	public static Link getLink(Class<?> modelResourse, Long keyOrId) {
		Link selfLink = WebMvcLinkBuilder.linkTo(modelResourse)
				.slash(keyOrId).withSelfRel();
		
	return selfLink;
	}
}
