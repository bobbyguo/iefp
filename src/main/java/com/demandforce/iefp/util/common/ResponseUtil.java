package com.demandforce.iefp.util.common;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.hash.Hashing;
import com.sun.jersey.core.spi.factory.ResponseBuilderImpl;

public final class ResponseUtil {

	private static final ObjectMapper mapper = new ObjectMapper();
	
	private ResponseUtil() {
	}

	public static void buildBadRequestErrorResponse(final String msg) {
		buildErrorResponse(Response.Status.BAD_REQUEST, msg);
	}

	public static void buildErrorResponse(final Response.Status status, final String msg) {
		throw new WebApplicationException(new ResponseBuilderImpl().status(status).entity(msg).build());
	}

	public static Response buildTaggedResponse(Object entity, Request request) {
		EntityTag etag = null;
		try {
			etag = new EntityTag(Hashing.murmur3_128().hashBytes(mapper.writeValueAsBytes(entity)).toString());
		} catch (Exception e) {
			buildBadRequestErrorResponse("Unable to compute Etag");
		}
		Response.ResponseBuilder responseBuilder = request.evaluatePreconditions(etag);
		return responseBuilder!=null?responseBuilder.build():Response.ok(entity, MediaType.APPLICATION_JSON).tag(etag).build();
	}
}
