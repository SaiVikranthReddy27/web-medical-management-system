package com.medical.controller;

import com.medical.model.Appointment;
import com.medical.model.MedicalRecord;
import com.medical.repository.AppointmentRepository;
import com.medical.repository.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
    @Autowired
    private AppointmentRepository appointmentRepo;

    @Autowired
    private MedicalRecordRepository recordRepo;

    // 📄 Patient Dashboard: See own appointments and medical records
    @GetMapping("/patient/{name}")
    public PatientDashboard getPatientDashboard(@PathVariable String name) {
        List<Appointment> appointments = appointmentRepo.findAll()
                .stream()
                .filter(a -> a.getPatientName().equalsIgnoreCase(name))
                .collect(Collectors.toList());

        List<MedicalRecord> records = recordRepo.findAll()
                .stream()
                .filter(r -> r.getPatientName().equalsIgnoreCase(name))
                .collect(Collectors.toList());

        return new PatientDashboard(appointments, records);
    }

    // 📄 Doctor Dashboard: See appointments and records assigned to them
    @GetMapping("/doctor/{name}")
    public DoctorDashboard getDoctorDashboard(@PathVariable String name) {
        List<Appointment> appointments = appointmentRepo.findAll()
                .stream()
                .filter(a -> a.getDoctorName().equalsIgnoreCase(name))
                .collect(Collectors.toList());

        List<MedicalRecord> records = recordRepo.findAll()
                .stream()
                .filter(r -> r.getDoctorName().equalsIgnoreCase(name))
                .collect(Collectors.toList());

        return new DoctorDashboard(appointments, records);
    }

    // Inner Classes to send grouped data
    public static class PatientDashboard {
        public List<Appointment> appointments;
        public List<MedicalRecord> medicalRecords;

        public PatientDashboard(List<Appointment> appointments, List<MedicalRecord> medicalRecords) {
            this.appointments = appointments;
            this.medicalRecords = medicalRecords;
        }
    }

    public static class DoctorDashboard {
        public List<Appointment> appointments;
        public List<MedicalRecord> medicalRecords;

        public DoctorDashboard(List<Appointment> appointments, List<MedicalRecord> medicalRecords) {
            this.appointments = appointments;
            this.medicalRecords = medicalRecords;
        }
    }
}
