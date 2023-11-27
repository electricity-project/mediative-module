package com.electricity.project.mediativemodule.mediativemqttproducer.mqtt;

import com.electricity.project.mediativemodule.mediativemqttproducer.api.PowerStationState;
import com.electricity.project.mediativemodule.mediativemqttproducer.configuration.PowerProductionGateway;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
@EnableScheduling
@Slf4j
public class MqttProducer {

    private final List<String> ipv6List = List.of("192.168.0.1", "192.168.0.2", "192.168.1.1", "192.168.2.2");
    private final PowerProductionGateway powerProductionGateway;
    private final Random random = new Random();

    private final ObjectMapper objectMapper = JsonMapper.builder()
            .addModule(new JavaTimeModule())
            .addModule(new Jdk8Module())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .build();

    @Value("${mqtt.producer.topic}")
    private String powerStationTopic;

    @Scheduled(fixedRateString = "${mqtt.message.producer.rate.ms}")
    public void sendMessage() {
        ipv6List.forEach(ipv6 -> {
            PowerProductionMessage powerProductionMessage = PowerProductionMessage.builder()
                    .ipv6Address(ipv6)
                    .power(random.nextLong() % 100000)
                    .timestamp(ZonedDateTime.now())
                    .state(PowerStationState.WORKING)
                    .build();
            try {
                log.info("[MOCKED] Sending data to MQTT broker: {}", powerProductionMessage);
                log.info("WIN 3");
                powerProductionGateway.sendToMqtt(objectMapper.writeValueAsString(powerProductionMessage));
            } catch (JsonProcessingException e) {
                log.error("Error processing object: {}", powerProductionMessage, e);
            }
        });

    }
    @Builder
    public record PowerProductionMessage(
            String ipv6Address,
            PowerStationState state,
            double power,
            ZonedDateTime timestamp
    ) {
        @Override
        public String toString() {
            return "PowerProductionMessage{" +
                    "ipv6Address='" + ipv6Address + '\'' +
                    ", state=" + state +
                    ", power=" + power +
                    ", timestamp=" + timestamp +
                    '}';
        }
    }
}
