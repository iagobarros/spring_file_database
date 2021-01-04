package com.accenture.spring_file_database.controller;

import com.accenture.spring_file_database.model.DatabaseFile;
import com.accenture.spring_file_database.repository.DatabaseFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class FileExportCsvController {

    @Autowired
    private DatabaseFileRepository fileStorageService;

    @GetMapping("/exportCSV")
    public void exportToCSV(HttpServletResponse response) throws IOException {
        // TODO Inserir link para download no csv
        response.setContentType("text/csv");
        String fileName = "Files.csv";
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; fileName=" + fileName;

        response.setHeader(headerKey, headerValue);

        List<DatabaseFile> listFiles = fileStorageService.findAll();

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);

        String[] csvHeader = {"File Name", "File Type"};
        String[] nameMapping = {"fileName", "fileType"};

        csvWriter.writeHeader(csvHeader);
        for (DatabaseFile databaseFile : listFiles) {
            csvWriter.write(databaseFile, nameMapping);
        }
        csvWriter.close();
    }

}
