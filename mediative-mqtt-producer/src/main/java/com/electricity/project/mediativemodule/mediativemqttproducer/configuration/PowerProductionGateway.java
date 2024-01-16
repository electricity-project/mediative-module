package com.electricity.project.mediativemodule.mediativemqttproducer.configuration;

import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
public interface PowerProductionGateway {
    void sendToMqtt(String powerProductionMessage);
}
