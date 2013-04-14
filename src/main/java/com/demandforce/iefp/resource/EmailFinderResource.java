package com.demandforce.iefp.resource;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demandforce.iefp.dto.CustomerDTO;
import com.demandforce.iefp.matcher.Matcher;
import com.demandforce.iefp.model.MatchResult;
import com.demandforce.iefp.model.User;
import com.yammer.dropwizard.auth.Auth;
import com.yammer.metrics.annotation.Timed;

@Produces(MediaType.APPLICATION_JSON)
@Path("/")
@Component("emailFinderResource")
public class EmailFinderResource {
	
	@Autowired
	Matcher matcher;
	
	@POST
	@Path("check")
	@Timed
	public Response check(@Auth(required = false) User user, @Valid CustomerDTO customerDto, @Context Request request) {
		Map<String, String> result  = new HashMap<String, String>();
		if (matcher.isMatch(customerDto)) {
			result.put("exists", "true");
		} else {
			result.put("exists", "false");
		}
		return Response.ok(result, MediaType.APPLICATION_JSON).build();
	}

	@POST
	@Path("apply")
	@Timed
	public Response apply(@Auth User user, @Valid CustomerDTO customerDto, @Context Request request) {
		
		MatchResult result = matcher.match(customerDto);
		return Response.ok(result, MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("get/{id}")
	@Timed
	public Response get(@Auth User user, @PathParam("id") Long id, @Context Request request) {
		
		return Response.ok("", MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("reply/{id}/{flag}")
	@Timed
	public Response reply(@PathParam("id") Long id, @PathParam("flag") String flag, @Context Request request) {
		return Response.ok("", MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("test/{name}")
	@Timed
	public Response test(@Auth User user, @PathParam("name") String name) {
		return Response.ok(new User(name, "****"), MediaType.APPLICATION_JSON).build();
	}

}
