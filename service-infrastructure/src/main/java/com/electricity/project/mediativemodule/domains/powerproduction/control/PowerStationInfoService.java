package com.electricity.project.mediativemodule.domains.powerproduction.control;

import com.electricity.project.mediativemodule.domains.powerproduction.entity.PowerStationInfo;
import com.electricity.project.mediativemodule.powerproduction.PowerProductionDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j

public class PowerStationInfoService {
    private final PowerStationInfoRepository powerStationInfoRepository;

    public List<PowerStationInfo> getAllEntities(){
        return powerStationInfoRepository.findAll();
    }

    public void saveAll(List<PowerProductionDTO> powerProductionDTOList) {
        powerStationInfoRepository.saveAll(powerProductionDTOList.stream().map(PowerStationInfoMapper::mapToEntity).toList());
    }

}
