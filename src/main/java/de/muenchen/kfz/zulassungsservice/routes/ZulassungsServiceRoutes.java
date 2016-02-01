package de.muenchen.kfz.zulassungsservice.routes;

import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ZulassungsServiceRoutes extends SpringRouteBuilder {

    @Value("${uploaded.dir}")
    private String uploadedDir;
    @Value("${processed.dir}")
    private String processedDir;

    private static final String HOME = System.getProperty("user.home");

    @Override
    public void configure() {
        // Calling hello bean
        from("timer:trigger?fixedRate=true&period=10s")
                //.transform().simple("ref:myBean")
                .transform().simple("ref:hello")
                .to("log:out");

        //from("direct:uploaded").toF("file://%s%s", HOME, processedDir + "unknown");
        
        from("direct:uploaded")
                .convertBodyTo(String.class)
                .choice()
                .when().xpath("//Plusservice_Zulassungen")
                .to("direct:service2")
                .when().xpath("//Ausserbetriebsetzungen")
                .to("direct:service1")
                .otherwise()
                .to("direct:unknown");
                  

        from("direct:service1")
                .toF("file://%s%s", HOME, processedDir + "service1");

        from("direct:service2")
                .toF("file://%s%s", HOME, processedDir + "service2");

        from("direct:unknown")
                .toF("file://%s%s", HOME, processedDir + "unknown");

    }

}
