package com.medical.controller;

import com.medical.model.Billing;
import com.medical.repository.BillingRepository;
import com.medical.service.BillingService;
import com.medical.utils.BillingPDFExporter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.ByteArrayOutputStream;

import java.io.OutputStream;
import java.util.List;

@RestController
@RequestMapping("/api/billing")
public class BillingController {
    @Autowired
    private BillingService service;

    @Autowired
    private BillingRepository billingRepository;

    @PostMapping
    public Billing generateBill(@RequestBody Billing billing) {
        return service.create(billing);
    }

    @GetMapping
    public List<Billing> getAllBills() {
        return service.getAll();
    }

    @GetMapping("/patient/{name}")
    public List<Billing> getBillsByPatient(@PathVariable String name) {
        return service.getByPatient(name);
    }

    @DeleteMapping("/{id}")
    public void deleteBill(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/export/pdf")
    public void exportToPDF(HttpServletResponse response) {
        try {
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=\"billings.pdf\"");

            List<Billing> billingList = billingRepository.findAll();

            OutputStream out=response.getOutputStream();
            BillingPDFExporter.export(billingList, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
