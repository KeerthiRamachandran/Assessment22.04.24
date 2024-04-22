package com.ikm.contractassessment.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ikm.contractassessment.model.Requisition;

public interface RequisitionRepository extends JpaRepository<Requisition,Integer>{
    Optional<Requisition> findByAllotmentId(Long allotmentId);
    
    @Query("SELECT r FROM Requisition r WHERE r.amount = :amount")
    Stream<Requisition> findByAmountAsStream(double amount);

    Stream<Requisition> findByAmountBetween(double minAmount, double maxAmount);
}

