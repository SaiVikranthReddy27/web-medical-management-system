package com.medical.service;

import com.medical.model.MedicalRecord;
import com.medical.repository.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalRecordService {
    @Autowired
    private MedicalRecordRepository repository;

    public MedicalRecord save(MedicalRecord record) {
        return repository.save(record);
    }

    public List<MedicalRecord> getAll() {
        return repository.findAll();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
