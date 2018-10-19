package org.poem.route;

import io.helidon.common.http.Http;
import io.helidon.webserver.Routing;
import io.helidon.webserver.Service;
import io.helidon.webserver.jersey.JerseySupport;
import io.helidon.webserver.json.JsonSupport;
import org.poem.complex.hello.JaxRsHelloWorldComplex;
import org.poem.complex.json.JsonComplexHandler;

import static org.poem.complex.statics.StaticContentSupportComplex.getPicsSupport;
import static org.poem.complex.statics.StaticContentSupportComplex.getWelComeIndex;

/**
 * @author poem
 */
public class RouteHandle {


    /**
     * route
     *
     * @return
     */
    public static Routing getRouting() {
        return Routing.builder()
                //example for java hello
                .any("/hello", (req, res) -> {
                    res.status(Http.Status.OK_200);
                    res.send("it\'s ok!");
                    req.next();//no next will exception  'Unhandled exception encountered.'
                })
                .error(Throwable.class, (req, res, ex) -> {

                })
                .register("/context-root", new Service() {
                    @Override
                    public void update(Routing.Rules rules) {
                        rules.get("/complex", (req, res) -> {
                            res.send("/context-root/complex");
                        });
                    }
                })
                //static
                .register("/pictures", getPicsSupport())
                .register("/", getWelComeIndex())
                //jax-rs
                .register("/jersey", JerseySupport.builder()
                        .register(JaxRsHelloWorldComplex.class)
                        .build())
                //json support
                .register(JsonSupport.get()).get("/json",new JsonComplexHandler())
                //no path ,return this
                .any((req, res) -> res.send("It works!"))
                .build()
                ;
    }
}
