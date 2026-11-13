package com.example.DTOExample.Service;

import com.example.DTOExample.DTOS.CreateStudentRequestDTO;
import com.example.DTOExample.DTOS.CreateStudentResponseDTO;
import com.example.DTOExample.DTOS.UpdateStudentRequestDTO;
import com.example.DTOExample.DTOS.UpdateStudentResponseDTO;
import com.example.DTOExample.Entity.Student;
import com.example.DTOExample.Repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public CreateStudentResponseDTO createStudent(CreateStudentRequestDTO studentRequestDTO) {

        Student student = mapToEntity(studentRequestDTO);  // This method take requestDTO responseDTO because databse save the Student(full data) ;
        Student studentResponse = studentRepository.save(student);


        return mapToDTO(studentResponse); // in reponse Return we will return partial data( response Dto not whole student object with all data)
    }

    public CreateStudentResponseDTO getStudent(Long id) {
        Optional<Student> dbstudentResp = studentRepository.findByIdAndDeletedIsFalse(id);

        if (dbstudentResp.isPresent()) {
            return mapToDTO(dbstudentResp.get());
        }

        return null;


    }

    public List<CreateStudentResponseDTO> getStudents() {
        List<Student> dbAllStudents = studentRepository.findByDeletedIsFalse();

        return dbAllStudents.stream().map(this::mapToDTO).toList();
    }


    public UpdateStudentResponseDTO updateStudent(UpdateStudentRequestDTO studentReq, Long id) {
        Optional<Student> existingStudent = studentRepository.findByIdAndDeletedIsFalse(id);
        if (existingStudent.isPresent()) {
            Student student = existingStudent.get();
            student.setName(studentReq.getName());
            student.setName(studentReq.getName());
            student.setAge(studentReq.getAge());
            student.setEmail(studentReq.getEmail());
            student.setUpdatedAt(LocalDateTime.now());

           Student savedStudent= studentRepository.save(student);

            return  mapToUpdateDTo(savedStudent);
        }

        return null;


    }

    public Boolean deleteStudent(Long id) {
        boolean existingStudent = studentRepository.existsById(id);

        if (!existingStudent) {
            return false;
        }

        studentRepository.deleteById(id);
        return true;


    }

    public Boolean deleteStudentSoftly(Long id) {
        //get
        // isdelted=true(1)
        // then save
        Optional<Student> existingStudent = studentRepository.findByIdAndDeletedIsFalse(id);

        if (existingStudent.isEmpty()) {
            return false;
        }

        Student student = existingStudent.get(); // .get will give you all data ( proper student)
        student.setDeleted(true);

        studentRepository.save(student);

        return true;
    }


    private Student mapToEntity(CreateStudentRequestDTO studentRequestDTO) {
        Student student = new Student();
        student.setName(studentRequestDTO.getName());
        student.setAge(studentRequestDTO.getAge());
        student.setEmail(studentRequestDTO.getEmail());
        student.setRollNo(studentRequestDTO.getAge());
        student.setSubject(studentRequestDTO.getSubject());
        student.setCreatedAt(LocalDateTime.now());
        student.setUpdatedAt(LocalDateTime.now());

        student.setDeleted(false);
        return student;

    }

    private CreateStudentResponseDTO mapToDTO(Student student) {
        CreateStudentResponseDTO responseDTO = new CreateStudentResponseDTO();
        responseDTO.setId(student.getId());
        responseDTO.setName(student.getName());
        responseDTO.setAge(student.getAge());
        responseDTO.setEmail(student.getEmail());
        responseDTO.setRollNo(student.getRollNo());
        responseDTO.setSubject(student.getSubject());

        responseDTO.setMessage("Student Saved Successfully");
        responseDTO.setCreatedAt(student.getCreatedAt());
        responseDTO.setUpdatedAt(student.getUpdatedAt());

        return  responseDTO;


    }

    private UpdateStudentResponseDTO mapToUpdateDTo(Student student){
        UpdateStudentResponseDTO responseDTO = new UpdateStudentResponseDTO();
        responseDTO.setId(student.getId());
        responseDTO.setName(student.getName());
        responseDTO.setAge(student.getAge());
        responseDTO.setEmail(student.getEmail());
        responseDTO.setRollNo(student.getRollNo());
        responseDTO.setSubject(student.getSubject());

        responseDTO.setMessage("Student Saved Successfully");

        responseDTO.setUpdatedAt(student.getUpdatedAt());

        return  responseDTO;



    }

}
