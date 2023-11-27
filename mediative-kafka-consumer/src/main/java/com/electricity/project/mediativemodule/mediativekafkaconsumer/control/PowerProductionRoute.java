package com.electricity.project.mediativemodule.mediativekafkaconsumer.control;

import com.electricity.project.mediativemodule.mediativekafkaconsumer.api.PowerProductionDTO;
import com.electricity.project.mediativemodule.mediativekafkaconsumer.configuration.KafkaDefaultConfiguration;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.AggregationStrategies;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Slf4j
public class PowerProductionRoute extends RouteBuilder {

    @Value("${kafka.power.station.topic}")
    private String kafkaTopic;

    @Override
    public void configure() {
        from(KafkaDefaultConfiguration.KAFKA_COMPONENT_NAME + ":" + kafkaTopic)
                .aggregate(constant(true), AggregationStrategies
                        .flexible(String.class)
                        .accumulateInCollection(ArrayList.class)
                        .pick(body()))
                .completionSize(2)
                .log(body().toString());
//            .unmarshal().json(JsonLibrary.Jackson, PowerProductionDTO.class)
//            .aggregate(constant(true), AggregationStrategies
//                        .flexible(PowerProductionDTO.class)
//                        .accumulateInCollection(ArrayList.class)
//                        .pick(body())
//            ).completionInterval(2000)
//                .completionTimeout(1000)
//                .completionSize(300)
//            .parallelProcessing(true)
//            .marshal().json(JsonLibrary.Jackson, String.class)
//            .setHeader(Exchange.HTTP_METHOD, simple("POST"))
//            .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
//            .log(LoggingLevel.DEBUG, log, "The body was - ${body}");
//            .to("{{calculation.database.application.address}}" + CALCULATIONS_POWER_PRODUCTION_ENDPOINT);
    }
}
