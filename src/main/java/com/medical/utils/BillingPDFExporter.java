package com.medical.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.medical.model.Billing;
import java.util.stream.Stream;
import java.io.OutputStream;
import java.util.List;

public class BillingPDFExporter {
    public static void export(List<Billing> billingList, OutputStream out) throws Exception {
        Document document = new Document();
        PdfWriter.getInstance(document, out);

        document.open();

        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
        Paragraph title = new Paragraph("Billing Records", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        document.add(Chunk.NEWLINE); // blank line

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{1.5f, 3.5f, 3.5f, 2.5f, 3.0f});
        table.setSpacingBefore(10);

        addTableHeader(table);
        addRows(table, billingList);

        document.add(table);
        document.close();
    }

    private static void addTableHeader(PdfPTable table) {
        Stream.of("ID", "Patient", "Doctor", "Amount", "Date")
                .forEach(headerTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setPhrase(new Phrase(headerTitle));
                    table.addCell(header);
                });
    }

    private static void addRows(PdfPTable table, List<Billing> list) {
        for (Billing b : list) {
            table.addCell(String.valueOf(b.getId()));
            table.addCell(b.getPatientName() != null ? b.getPatientName(): "N/A");
            table.addCell(b.getDoctorName() != null ? b.getDoctorName(): "N/A");
            //table.addCell(b.getTreatmentDescription() != null ? b.getTreatmentDescription(): "N/A");
            table.addCell(String.format("%.2f",b.getAmount()));
            table.addCell(b.getBillingDate()!= null ? b.getBillingDate().toString(): "N/A");
        }
    }
}
