package com.ikm.contractassessment.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ikm.contractassessment.common.contract.Response;
import com.ikm.contractassessment.contract.RequisitionRequest;
import com.ikm.contractassessment.contract.RequisitionResponse;
import com.ikm.contractassessment.contract.RequisitionUpdateRequest;
import com.ikm.contractassessment.model.Requisition;
import com.ikm.contractassessment.service.RequisitionService;

@RestController
@RequestMapping("/assessment")
@RequiredArgsConstructor
public class RequisitionController {
    private final RequisitionService requisitionService;

    @SneakyThrows
    @Operation(summary = "Save Requisition Details request")
    @PostMapping(value = { "/save-Requisition-details" })
    public ResponseEntity<?> saveVoucherDetails(@Valid @RequestBody RequisitionRequest request) {
        Requisition requisitionDetails = requisitionService.saveRequisitionDetails(request);
        RequisitionResponse voucherDetailsResponse = RequisitionResponse.builder()
                .id(requisitionDetails.getId())
                .brn(requisitionDetails.getBrn())
                .allotmentId(requisitionDetails.getAllotmentId())
                .amount(requisitionDetails.getAmount())
                .build();
        return new ResponseEntity<>(
                Response.builder()
                        .payload(voucherDetailsResponse)
                        .message("Successfully saved")
                        .build(),
                HttpStatus.OK);
    }

    @PutMapping("/{allotmentId}")
    public ResponseEntity<String> updateRequisitionByAllotmentId(
            @PathVariable Long allotmentId,
            @RequestBody RequisitionUpdateRequest requisitionRequest) {

        boolean updated = requisitionService.updateRequisitionByAllotmentId(allotmentId, requisitionRequest);

        if (updated) {
            return ResponseEntity.ok("Requisition updated successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Requisition not found for allotmentId: " + allotmentId);
        }
    }

    @GetMapping("/search/amount/{amount}")
    public List<Requisition> searchRequisitionsByAmount(@PathVariable double amount) {
        return requisitionService.searchRequisitionsByAmount(amount);
    }

    @GetMapping("/searchByAmountRange")
    public List<Requisition> searchRequisitionsByAmountRange(
            @RequestParam("minAmount") double minAmount,
            @RequestParam("maxAmount") double maxAmount) {
        return requisitionService.searchRequisitionsByAmountRange(minAmount, maxAmount);
    }
}
