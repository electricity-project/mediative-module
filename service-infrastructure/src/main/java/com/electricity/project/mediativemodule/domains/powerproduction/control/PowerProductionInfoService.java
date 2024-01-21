package com.electricity.project.mediativemodule.domains.powerproduction.control;

import com.electricity.project.mediativemodule.powerproduction.PowerProductionDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j

public class PowerProductionInfoService {
    private final PowerProductionInfoRepository powerProductionInfoRepository;

    public void saveAll(List<PowerProductionDTO> powerProductionDTOList) {
        powerProductionInfoRepository.saveAll(powerProductionDTOList.stream().map(PowerProductionInfoMapper::mapToEntity).toList());
    }

}
