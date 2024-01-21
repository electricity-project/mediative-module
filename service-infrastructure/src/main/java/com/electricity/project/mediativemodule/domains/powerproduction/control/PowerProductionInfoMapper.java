package com.electricity.project.mediativemodule.domains.powerproduction.control;


import com.electricity.project.mediativemodule.domains.powerproduction.entity.PowerProductionInfo;
import com.electricity.project.mediativemodule.powerproduction.PowerProductionDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PowerProductionInfoMapper {

    public static PowerProductionInfo mapToEntity(PowerProductionDTO powerProductionDTO) {
        return PowerProductionInfo.builder()
                .ipv6Address(powerProductionDTO.getIpv6Address())
                .state(powerProductionDTO.getState())
                .producedPower(powerProductionDTO.getProducedPower())
                .timestamp(powerProductionDTO.getTimestamp())
                .build();
    }
}