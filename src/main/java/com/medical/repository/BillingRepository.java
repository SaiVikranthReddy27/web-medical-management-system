package com.medical.repository;

import com.medical.model.Billing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface BillingRepository extends JpaRepository<Billing, Long>{
    List<Billing> findByPatientName(String patientName);
}
