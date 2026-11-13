package com.example.DTOExample.Repository;

import com.example.DTOExample.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

 //it should not compulsury in case of iterface beause we can not create object of interface
//<Student is a handled class , Long is type of primary key(id)  >
 @Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByIdAndDeletedIsFalse(Long id);

    List<Student> findByDeletedIsFalse(); // synatax findBy+fieldName(deleted)+condition(false)




}
