package com.medical.controller;

import com.medical.model.MedicalRecord;
import com.medical.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/records")
public class MedicalRecordController {
    @Autowired
    private MedicalRecordService service;

    @PostMapping
    public MedicalRecord addRecord(@RequestBody MedicalRecord record) {
        return service.save(record);
    }

    @GetMapping
    public List<MedicalRecord> getAllRecords() {
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    public void deleteRecord(@PathVariable Long id) {
        service.delete(id);
    }
}
