package com.mycompany.web.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * This is a JakartaEE8Resource class that represents a REST resource. It
 * contains a simple ping method that responds with "ping".
 *
 * @author Remigiusz Kocialski
 * @version 1.0
 */
@Path("rest")
public class JakartaEE8Resource {

    /**
     * This method handles HTTP GET requests and responds with a "ping" message.
     *
     * @return Response object with "ping" message and HTTP 200 OK status.
     */
    @GET
    public Response ping() {
        return Response
                .ok("ping")
                .build();
    }
}
