package com.medical.service;

import com.medical.model.Billing;
import com.medical.repository.BillingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillingService {
    @Autowired
    private BillingRepository repository;

    public Billing create(Billing billing) {
        return repository.save(billing);
    }

    public List<Billing> getAll() {
        return repository.findAll();
    }

    public List<Billing> getByPatient(String patientName) {
        return repository.findByPatientName(patientName);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
