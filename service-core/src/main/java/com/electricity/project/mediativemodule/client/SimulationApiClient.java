package com.electricity.project.mediativemodule.client;

import com.electricity.project.mediativemodule.systemapi.PowerStationInfoDTO;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.nio.charset.StandardCharsets;
import java.time.Duration;

@Component
public class SimulationApiClient implements SimulationClient{

    private final WebClient client;

    public SimulationApiClient(@Value("${simulation.system.url}") String baseUrl) {
        HttpClient httpClient = HttpClient.create().responseTimeout(Duration.ofSeconds(10));
        client = WebClient.builder()
                .baseUrl(baseUrl)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }

    @Override
    public PowerStationInfoDTO getInfo(@NonNull String ipv6Address) {
        PowerStationInfoDTO dto = client.get()
                .uri("/info", uriBuilder -> uriBuilder
                        .queryParam("ipv6Address", ipv6Address)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .acceptCharset(StandardCharsets.UTF_8)
                .retrieve()
                .bodyToMono(PowerStationInfoDTO.class)
                .retry(3)
                .block();
        System.out.println(dto);
        return dto;
    }

    @Override
    public Void connectToSystem(@NonNull String ipv6Address) {
        return client.get()
                .uri("/connect", uriBuilder -> uriBuilder
                        .queryParam("ipv6Address", ipv6Address)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .acceptCharset(StandardCharsets.UTF_8)
                .retrieve()
                .bodyToMono(Void.class)
                .retry(3)
                .block();
    }

    @Override
    public Void disconnectFromSystem(@NonNull String ipv6Address) {
        return client.get()
                .uri("/disconnect", uriBuilder -> uriBuilder
                        .queryParam("ipv6Address", ipv6Address)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .acceptCharset(StandardCharsets.UTF_8)
                .retrieve()
                .bodyToMono(Void.class)
                .retry(3)
                .block();
    }

    @Override
    public Void startPowerStation(@NonNull String ipv6Address) {
        return client.get()
                .uri("/start", uriBuilder -> uriBuilder
                        .queryParam("ipv6Address", ipv6Address)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .acceptCharset(StandardCharsets.UTF_8)
                .retrieve()
                .bodyToMono(Void.class)
                .retry(3)
                .block();
    }

    @Override
    public Void stopPowerStation(@NonNull String ipv6Address) {
        return client.get()
                .uri("/stop", uriBuilder -> uriBuilder
                        .queryParam("ipv6Address", ipv6Address)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .acceptCharset(StandardCharsets.UTF_8)
                .retrieve()
                .bodyToMono(Void.class)
                .retry(3)
                .block();
    }
}
