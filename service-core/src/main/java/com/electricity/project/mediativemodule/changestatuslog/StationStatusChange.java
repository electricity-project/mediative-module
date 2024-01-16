package com.electricity.project.mediativemodule.changestatuslog;

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
public class StationStatusChange {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String ipv6Address;

    @Column
    @Enumerated(EnumType.STRING)
    private StationStatus statusChange;

    @Column
    private ZonedDateTime timestamp;
}
