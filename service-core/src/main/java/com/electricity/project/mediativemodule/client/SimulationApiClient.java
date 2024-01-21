package com.electricity.project.mediativemodule.client;

import com.electricity.project.mediativemodule.changestatuslog.StationStatus;
import com.electricity.project.mediativemodule.control.StationStatusChangeMapper;
import com.electricity.project.mediativemodule.control.StationStatusChangeService;
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
public class SimulationApiClient implements SimulationClient {

    private final StationStatusChangeService stationStatusChangeService;
    private final WebClient client;

    public SimulationApiClient(StationStatusChangeService stationStatusChangeService, @Value("${simulation.system.url}") String baseUrl) {
        this.stationStatusChangeService = stationStatusChangeService;
        HttpClient httpClient = HttpClient.create().responseTimeout(Duration.ofSeconds(10));
        client = WebClient.builder()
                .baseUrl(baseUrl)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }

    @Override
    public PowerStationInfoDTO getInfo(@NonNull String ipv6Address) {
        return client.get()
                .uri("/info", uriBuilder -> uriBuilder
                        .queryParam("ipv6Address", ipv6Address)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .acceptCharset(StandardCharsets.UTF_8)
                .retrieve()
                .bodyToMono(PowerStationInfoDTO.class)
                .retry(3)
                .block();
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
                .doOnSuccess(response -> stationStatusChangeService.save(StationStatusChangeMapper.mapToEntity(ipv6Address, StationStatus.CONNECTED)))
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
                .doOnSuccess(response -> stationStatusChangeService.save(StationStatusChangeMapper.mapToEntity(ipv6Address, StationStatus.DISCONNECTED)))
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
                .doOnSuccess(response -> stationStatusChangeService.save(StationStatusChangeMapper.mapToEntity(ipv6Address, StationStatus.STARTED)))
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
                .doOnSuccess(response -> stationStatusChangeService.save(StationStatusChangeMapper.mapToEntity(ipv6Address, StationStatus.STOPPED)))
                .block();
    }
}
