package com.electricity.project.mediativemodule.domains.powerproduction.entity;

import com.electricity.project.mediativemodule.powerstation.PowerStationState;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public class PowerProductionInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @Column
    private String ipv6Address;

    @Column
    @Enumerated(EnumType.STRING)
    private PowerStationState state;

    @Column
    private ZonedDateTime timestamp;

    @Column
    private double producedPower;
}
