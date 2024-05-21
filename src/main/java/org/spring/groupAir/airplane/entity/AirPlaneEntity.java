package org.spring.groupAir.airplane.entity;

import lombok.*;
import org.spring.groupAir.contraint.BaseTimeEntity;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "airplane")
public class AirPlaneEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "airplane_id")
    private Long id;

    @Column(nullable = false)
    private String fromTime;

    @Column(nullable = false)
    private String fromArea;

    @Column(nullable = false)
    private String ToTime;

    @Column(nullable = false)
    private String ToArea;

    @Column(nullable = false)
    private String airplane;
}
