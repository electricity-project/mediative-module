package com.electricity.project.mediativemodule.control;

import com.electricity.project.mediativemodule.changestatuslog.StationStatusChange;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class StationStatusChangeService {
    private final StationStatusChangeRepository stationStatusChangeRepository;

    public void save(StationStatusChange stationStatusChange) {
        stationStatusChangeRepository.save(stationStatusChange);
    }
}
