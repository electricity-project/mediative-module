package com.electricity.project.mediativemodule.mediativemqttproducer.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PowerProductionGatewayDev implements PowerProductionGateway {

    @Override
    public void sendToMqtt(String powerProductionMessage) {
        log.info("[MOCKED] Sending data to MQTT broker: {}", powerProductionMessage);
    }
}
