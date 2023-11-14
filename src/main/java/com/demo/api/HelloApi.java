package com.demo.api;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/hello")
public class HelloApi {

    //  http GET http://localhost:8080/jcd-starter-jakarta10-ws/api/hello
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayHello() {
        return "Hello, World!";
    }

    //  http POST http://localhost:8080/jcd-starter-jakarta10-ws/api/hello name="James" message="Hello"
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getName(HelloMessage message) {
        System.out.println(message);
        return Response.ok().build();
    }
}