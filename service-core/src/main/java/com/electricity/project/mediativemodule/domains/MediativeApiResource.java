package com.electricity.project.mediativemodule.domains;

import com.electricity.project.mediativemodule.client.SimulationApiClient;
import com.electricity.project.mediativemodule.systemapi.PowerStationInfoDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MediativeApiResource {
    private final SimulationApiClient simulationApiClient;

    @GetMapping("/info")
    public ResponseEntity<PowerStationInfoDTO> getInfo(@RequestParam String ipv6Address) {
        return ResponseEntity.ok(simulationApiClient.getInfo(ipv6Address));
    }

    @GetMapping("/connect")
    public ResponseEntity<Void> connectToSystem(@RequestParam String ipv6Address) {
        return ResponseEntity.ok(simulationApiClient.connectToSystem(ipv6Address));
    }

    @GetMapping("/disconnect")
    public ResponseEntity<Void> disconnectFromSystem(@RequestParam String ipv6Address) {
        return ResponseEntity.ok(simulationApiClient.disconnectFromSystem(ipv6Address));
    }

    @GetMapping("/start")
    public ResponseEntity<Void> startPowerStation(@RequestParam String ipv6Address) {
        return ResponseEntity.ok(simulationApiClient.startPowerStation(ipv6Address));
    }
    @GetMapping("/stop")
    public ResponseEntity<Void> stopPowerStation(@RequestParam String ipv6Address) {
        return ResponseEntity.ok(simulationApiClient.stopPowerStation(ipv6Address));
    }

}
