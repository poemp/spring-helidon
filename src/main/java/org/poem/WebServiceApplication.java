package org.poem;

import io.helidon.config.Config;
import io.helidon.webserver.ServerConfiguration;
import io.helidon.webserver.WebServer;
import io.helidon.webserver.zipkin.ZipkinTracerBuilder;
import org.poem.route.RouteHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * @author poem
 */
public class WebServiceApplication {

    private static final Logger logger = LoggerFactory.getLogger(WebServiceApplication.class);


    /**
     * get server config
     *
     * @return
     */
    private static ServerConfiguration getServerConfiguration() throws UnknownHostException {
        return ServerConfiguration.builder()
                .bindAddress(InetAddress.getLocalHost())
                .port(8080)
                .tracer(ZipkinTracerBuilder.forService("spring-helidon")
                        .zipkin("http://127.0.0.1:9411")
                        .build())
                .build();
    }

    /**
     * or get by application.cnf
     *
     * @return
     */
    private static ServerConfiguration getByApplicationConf() {
        return ServerConfiguration.fromConfig(
                Config.builder().sources(Config.loadSources(new Supplier() {
                    @Override
                    public Object get() {
                        return "application.conf";
                    }
                }).build().asSupplier(List.class).get())
                        .build());

    }

    /**
     * start app
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        WebServer webServer = WebServer.create(getServerConfiguration(), RouteHandle.getRouting())
                .start()
                .toCompletableFuture()
                .get(10, TimeUnit.SECONDS);
        logger.info("Server started at: http://localhost:" + webServer.port());
    }
}
