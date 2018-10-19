package org.poem.route;

import io.helidon.common.http.Http;
import io.helidon.webserver.Routing;

/**
 * @author poem
 */
public class RouteHandle {


    /**
     * route
     * @return
     */
    public static Routing getRouting(){
        return  Routing.builder()
                //example for java hello
                .any("/hello",(req,res)->{
                    res.status(Http.Status.OK_200);
                    res.send("it\'s ok!");
                    req.next();//no next will exception  'Unhandled exception encountered.'
                })
                .error(Throwable.class,(req,res,ex)->{

                })
                .any((req, res) -> res.send("It works!"))
                .build()
                ;
    }
}
