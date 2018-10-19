package org.poem.complex.hello;

import io.helidon.webserver.ServerRequest;
import io.helidon.webserver.ServerResponse;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * @author poem
 */
@Path("/")
public class JaxRsHelloWorldComplex {

    @Inject
    private ServerRequest request;

    @Inject
    private ServerResponse response;

    @GET
    @Path("hello")
    public Response hello() {
        return Response.ok("Hello World!").build();
    }
}
