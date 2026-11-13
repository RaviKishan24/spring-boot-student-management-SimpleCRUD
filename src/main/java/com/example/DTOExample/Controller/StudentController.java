package com.example.DTOExample.Controller;

import com.example.DTOExample.DTOS.CreateStudentRequestDTO;
import com.example.DTOExample.DTOS.CreateStudentResponseDTO;
import com.example.DTOExample.DTOS.UpdateStudentRequestDTO;
import com.example.DTOExample.DTOS.UpdateStudentResponseDTO;
import com.example.DTOExample.Entity.Student;
import com.example.DTOExample.Service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/create")
    public ResponseEntity<CreateStudentResponseDTO> create( @Valid  @RequestBody CreateStudentRequestDTO studentRequestDTO) {

        CreateStudentResponseDTO createdStudent = studentService.createStudent(studentRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }

    @GetMapping("/get")
    public ResponseEntity<CreateStudentResponseDTO> getStudent(@RequestParam Long id) {
        CreateStudentResponseDTO studentResp = studentService.getStudent(id);

        if (studentResp == null) {
           return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentResp);

    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CreateStudentResponseDTO>> getAllStudent() {
        List<CreateStudentResponseDTO> studentResp = studentService.getStudents();

        if (studentResp.isEmpty()) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentResp);
    }

    //    @PutMapping("/update/{id}") it wil take only one condtion like only id  and @RequestParam it will take multiple like id age email
//    public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable Long id) {
    @PutMapping("/update")
    public ResponseEntity<UpdateStudentResponseDTO> updateStudent(@RequestBody UpdateStudentRequestDTO updateStudentRequestDTO, @RequestParam Long id) {
        UpdateStudentResponseDTO studentReponse = studentService.updateStudent(updateStudentRequestDTO, id);

        return ResponseEntity.ok(studentReponse);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteStudent(@RequestParam Long id) {
        Boolean isDeleted = studentService.deleteStudent(id);

        if (!isDeleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Record Deleted");

    }

    @PatchMapping("/delete-soft")
    public ResponseEntity<String> deleteStudentSoftly(@RequestParam Long id) {

        Boolean isDeleted = studentService.deleteStudentSoftly(id);

        if (!isDeleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok("Record Deleted");

    }


}
