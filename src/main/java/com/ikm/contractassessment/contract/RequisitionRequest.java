package com.ikm.contractassessment.contract;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequisitionRequest {
    
    @NotNull
    private String brn;

    @NotNull
    private Long allotmentId;

    private double amount;
}
