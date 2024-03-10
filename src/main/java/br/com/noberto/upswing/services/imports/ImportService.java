package br.com.noberto.upswing.services.imports;

import br.com.noberto.upswing.dtos.address.AddressRequest;
import br.com.noberto.upswing.dtos.student.RegisterStudent;
import br.com.noberto.upswing.dtos.student.StudentImport;
import br.com.noberto.upswing.models.Address;
import br.com.noberto.upswing.models.Student;
import br.com.noberto.upswing.repositories.StudentRepository;
import br.com.noberto.upswing.services.register.AdminRegisterService;
import br.com.noberto.upswing.util.GeneratePassword;
import br.com.noberto.upswing.util.verifications.AdminCheck;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ImportService {
    private final StudentRepository studentRepository;
    private final AdminCheck adminCheck;
    private final GeneratePassword password;
    private final AdminRegisterService adminRegisterService;

    @Autowired
    public ImportService(StudentRepository studentRepository, AdminCheck adminCheck, GeneratePassword password, AdminRegisterService adminRegisterService) {
        this.studentRepository = studentRepository;
        this.adminCheck = adminCheck;
        this.password = password;
        this.adminRegisterService = adminRegisterService;
    }

    public void saveImportedStudents(MultipartFile csvStudent) throws CsvValidationException, IOException {
        Set<Student> students = new HashSet<>(readFile(csvStudent));
        for (Student student: students) {
            adminRegisterService.registerStudent(new RegisterStudent(student));
        }
    }

    private List<Student> readFile(MultipartFile studentCsvFile) throws IOException, CsvValidationException {
        List<Student> students = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new InputStreamReader(studentCsvFile.getInputStream()))) {
            String[] nextRecord;
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            while ((nextRecord = reader.readNext()) != null) {
                StudentImport studentImport = new StudentImport(
                        nextRecord[0],
                        nextRecord[1],
                        LocalDate.parse(nextRecord[2], format),
                        nextRecord[3],
                        nextRecord[4],
                        nextRecord[5],
                        nextRecord[6],
                        nextRecord[7],
                        Integer.parseInt(nextRecord[8]),
                        nextRecord[9],
                        nextRecord[10],
                        nextRecord[11],
                        nextRecord[12]);

                students.add(new Student(studentImport));
            }
        }
        return students;
    }
}
