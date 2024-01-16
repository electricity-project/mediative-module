package com.electricity.project.mediativemodule.control;

import com.electricity.project.mediativemodule.changestatuslog.StationStatusChange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationStatusChangeRepository extends JpaRepository<StationStatusChange, Long> {
}
