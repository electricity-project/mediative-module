package com.electricity.project.mediativemodule.domains.powerproduction.control;

import com.electricity.project.mediativemodule.domains.powerproduction.configuration.RabbitmqDefaultConfiguration;
import com.electricity.project.mediativemodule.powerproduction.PowerProductionDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.builder.AggregationStrategies;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.kafka.KafkaConstants;
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

    private final ObjectMapper objectMapper = JsonMapper.builder()
            .addModule(new JavaTimeModule())
            .addModule(new Jdk8Module())
            .build();

    private Integer messageNumber = 0;

    @Override
    public void configure() {
        from(RabbitmqDefaultConfiguration.RABBITMQ_COMPONENT_NAME + rabbitmqConsumerTopic)
                .unmarshal()
                .json(JsonLibrary.Jackson, PowerProductionDTO.class)
                .aggregate(constant(true), AggregationStrategies.flexible(String.class)
                    .accumulateInCollection(ArrayList.class)
                    .pick(body()))
                .completionSize(5)
                .parallelProcessing(true)
                .marshal()
                .json(JsonLibrary.Jackson, String.class)
                .multicast()
                .split(body()).parallelProcessing()
                .to("kafka:" + kafkaProducerTopic + "?brokers="+ kafkaBrokerUrl);
    }
}