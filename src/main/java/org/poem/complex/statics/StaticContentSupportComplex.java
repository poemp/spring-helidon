package org.poem.complex.statics;

import io.helidon.webserver.StaticContentSupport;

import java.nio.file.Paths;

/**
 * @author poem
 */
public class StaticContentSupportComplex {

    /**
     * to pics
     * @return
     */
    public static StaticContentSupport getPicsSupport() {
        return StaticContentSupport.create(Paths.get("webapp/pics"));
    }


    /**
     * builder lets you provide more configuration values
     * welcome index.html
     * @return
     */
    public static StaticContentSupport getWelComeIndex(){
        return StaticContentSupport.builder("static-content")
                .welcomeFileName("/webapp/index.html")
                .build();
    }
}
