package com.electricity.project.mediativemodule.domains.powerproduction.control;

import com.electricity.project.mediativemodule.domains.powerproduction.entity.PowerStationInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PowerStationInfoRepository extends JpaRepository<PowerStationInfo, Long> {
    Optional<PowerStationInfo> findFirstByIpv6Address(String ipv6Address);
}