package org.poem.complex.json;

import io.helidon.webserver.Handler;
import io.helidon.webserver.ServerRequest;
import io.helidon.webserver.ServerResponse;

import javax.json.Json;
import javax.json.JsonObject;

/**
 * to json
 * @author poem
 */
public class JsonComplexHandler implements Handler {

    @Override
    public void accept(ServerRequest serverRequest, ServerResponse serverResponse) {
        JsonObject jsonObject = Json.createObjectBuilder()
                .add("hello","hello" + serverRequest.webServer().port())
                .add("userName","spring-helidon")
                .add("id","org.poem")
                .build();
        serverResponse.send(jsonObject);
    }

}
