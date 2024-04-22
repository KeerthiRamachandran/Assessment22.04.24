package com.ikm.contractassessment.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.ikm.contractassessment.contract.RequisitionRequest;
import com.ikm.contractassessment.contract.RequisitionUpdateRequest;
import com.ikm.contractassessment.model.Requisition;
import com.ikm.contractassessment.repository.RequisitionRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class RequisitionService {
    private final RequisitionRepository requisitionRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    public Requisition saveRequisitionDetails(RequisitionRequest request) {
        Requisition requisitionDetails = modelMapper.map(request, Requisition.class);
        return requisitionRepository.save(requisitionDetails);

    }

    public boolean updateRequisitionByAllotmentId(Long allotmentId, RequisitionUpdateRequest requisitionRequest) {
        Optional<Requisition> optionalRequisition = requisitionRepository.findByAllotmentId(allotmentId);
        if (optionalRequisition.isPresent()) {
            Requisition requisition = optionalRequisition.get();
            requisition.setBrn(requisitionRequest.getBrn());
            requisition.setAmount(requisitionRequest.getAmount());
            requisitionRepository.save(requisition);
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public List<Requisition> searchRequisitionsByAmount(double amount) {
        try (Stream<Requisition> requisitionStream = requisitionRepository.findByAmountAsStream(amount)) {
            return requisitionStream.collect(Collectors.toList());
        }
    }

    @Transactional
    public List<Requisition> searchRequisitionsByAmountRange(double minAmount, double maxAmount) {
        try (Stream<Requisition> requisitionStream = requisitionRepository.findByAmountBetween(minAmount, maxAmount)) {
            return requisitionStream.collect(Collectors.toList());
        }
    }
}
