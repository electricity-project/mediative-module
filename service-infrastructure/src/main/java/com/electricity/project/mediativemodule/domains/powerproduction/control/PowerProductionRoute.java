package com.electricity.project.mediativemodule.domains.powerproduction.control;

import com.electricity.project.mediativemodule.domains.powerproduction.configuration.RabbitmqDefaultConfiguration;
import com.electricity.project.mediativemodule.powerproduction.PowerProductionDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.builder.AggregationStrategies;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;


@Component
@Slf4j
public class PowerProductionRoute extends RouteBuilder {
    @Value("${mqtt.consumer.topic}")
    private String rabbitmqConsumerTopic;

    @Value("${kafka.broker.address}")
    private String kafkaBrokerUrl;
    @Value("${kafka.power.station.topic}")
    private String kafkaProducerTopic;

    @Override
    public void configure() {
        from(RabbitmqDefaultConfiguration.RABBITMQ_COMPONENT_NAME + rabbitmqConsumerTopic)
                .unmarshal()
                .json(JsonLibrary.Jackson, PowerProductionDTO.class)
                .aggregate(constant(true), AggregationStrategies.flexible(PowerProductionDTO.class)
                    .accumulateInCollection(ArrayList.class)
                    .pick(body()))
                .completionInterval(1000)
                .parallelProcessing(true)
                .bean(PowerProductionInfoService.class, "saveAll(${body})")
                .split(body()).parallelProcessing()
                .marshal()
                .json(JsonLibrary.Jackson, String.class)
                .to("kafka:" + kafkaProducerTopic + "?brokers="+ kafkaBrokerUrl);

    }
}