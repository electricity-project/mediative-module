package com.electricity.project.mediativemodule.client;

import com.electricity.project.mediativemodule.systemapi.PowerStationInfoDTO;
import lombok.NonNull;
public interface SimulationClient {
    PowerStationInfoDTO getInfo(@NonNull String ipv6Address);
    Void connectToSystem(@NonNull String ipv6Address);
    Void disconnectFromSystem(@NonNull String ipv6Address);
    Void startPowerStation(@NonNull String ipv6Address);
    Void stopPowerStation(@NonNull String ipv6Address);
}
