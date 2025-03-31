package com.medical.repository;

import com.medical.model.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {

}
