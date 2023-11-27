package com.electricity.project.mediativemodule.domains.powerproduction.configuration;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.builder.component.ComponentsBuilderFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqDefaultConfiguration extends RouteBuilder {
    @Value("${mqtt.broker.url}")
    private String rabbitmqBrokerUrl;
    @Value("${mqtt.client.username}")
    private String rabbitmqClientUsername;
    @Value("${mqtt.client.password}")
    private String rabbitmqClientPassword;
    @Value("${mqtt.client.id}")
    private String rabbitmqClientId;


    public static final String RABBITMQ_COMPONENT_NAME = "rabbitmq";


    @Override
    public void configure() {
        ComponentsBuilderFactory.paho()
                .brokerUrl(rabbitmqBrokerUrl)
                .clientId(rabbitmqClientId)
                .serverURIs(rabbitmqBrokerUrl)
                .userName(rabbitmqClientUsername)
                .password(rabbitmqClientPassword)
                .register(getCamelContext(), RABBITMQ_COMPONENT_NAME);
    }

}
