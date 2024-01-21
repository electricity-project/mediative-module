package com.electricity.project.mediativemodule.systemapi;

import com.electricity.project.mediativemodule.powerstation.PowerStationState;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.time.ZonedDateTime;

@Value.Immutable
@Value.Style
@JsonSerialize(as = ImmutablePowerStationInfoDTO.class)
@JsonDeserialize(as = ImmutablePowerStationInfoDTO.class)
@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({
        @JsonSubTypes.Type(value = WindTurbineInfoDTO.class),
        @JsonSubTypes.Type(value = SolarPanelInfoDTO.class)
})
public interface PowerStationInfoDTO {

    static ImmutablePowerStationInfoDTO.Builder builder() {
        return ImmutablePowerStationInfoDTO.builder();
    }

    @JsonProperty(value = "ipv6", required = true)
    String getIpv6Address();

    @JsonProperty(value = "state", required = true)
    PowerStationState getState();

    @JsonProperty(value = "creationTime", required = true)
    ZonedDateTime getCreationTime();

    @JsonProperty(value = "maxPower", required = true)
    double getMaxPower();
}
