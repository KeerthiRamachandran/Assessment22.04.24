package com.ikm.contractassessment.contract;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequisitionResponse {
    private Long id;

    private String brn;

    private Long allotmentId;

    private double amount; 
}
