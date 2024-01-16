package com.electricity.project.mediativemodule.domains.powerproduction.control;

import com.electricity.project.mediativemodule.domains.powerproduction.entity.PowerProductionInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PowerProductionInfoRepository extends JpaRepository<PowerProductionInfo, Long> {
}