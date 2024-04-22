package com.ikm.contractassessment.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@Builder
@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "requisition_details")
public class Requisition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brn;

    private Long allotmentId;

    private double amount;
}
