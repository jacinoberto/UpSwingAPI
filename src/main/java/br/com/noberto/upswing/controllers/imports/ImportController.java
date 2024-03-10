package br.com.noberto.upswing.controllers.imports;

import br.com.noberto.upswing.services.imports.ImportService;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.data.annotation.Transient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.rmi.RemoteException;

@RestController
@RequestMapping("api/import")
public class ImportController {
    private final ImportService service;

    public ImportController(ImportService service) {
        this.service = service;
    }

    @PostMapping("student")
    @Transient
    public ResponseEntity<String> saveImportStudents(@RequestParam() MultipartFile csvStudents) throws CsvValidationException, IOException {
        service.saveImportedStudents(csvStudents);
        return ResponseEntity.ok("Alunos importados com sucesso");
    }
}
