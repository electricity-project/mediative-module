package com.electricity.project.mediativemodule.control;

import com.electricity.project.mediativemodule.changestatuslog.StationStatusChange;
import com.electricity.project.mediativemodule.changestatuslog.StationStatus;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class StationStatusChangeMapper {

    public static StationStatusChange mapToEntity(String ipv6Address, StationStatus stationStatus){
        return StationStatusChange.builder()
                .ipv6Address(ipv6Address)
                .statusChange(stationStatus)
                .timestamp(ZonedDateTime.now())
                .build();
    }
}
