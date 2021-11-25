/*
 *   Created by Max Delaney
 *   24/11/2021
 *   Tutorial Followed: Amigoscode.
 *
 *   Service Layer / Business Logic Here.
 *
 */

package com.delaney.student_dashboard.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service //@Service tells spring this is a service. An @Component is also useable but is more generic.
public class StudentService {

    private final StudentRepository studentRepository;  //Declare instance of Student Repository.

    @Autowired  //Autowired makes sure the above studentRepository instance is injected into the constructor.
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    //return list of all students in the database using JPA.
    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());   //take the currently entered email and see if it can be found in the database for an existing user.

        if (studentOptional.isPresent()){   //If email is used/taken throw expection Email Taken!
            throw new IllegalStateException("Email taken!");
        }
        studentRepository.save(student);    //If email not taken, save student.
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);   //Check if studentId exists in the Student Repository.
        if (!exists){   //If it doesn't exist throw exception
            throw new IllegalStateException("student with id " + studentId + " does not exist");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional  //Entity into managed state.
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException("student with id " + studentId + " does not exist"));
        if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)){
            student.setName(name);
        }

        if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);   //take the currently entered email and see if it can be found in the database for an existing user.

            if (studentOptional.isPresent()){   //If email is used/taken throw expection Email Taken!
                throw new IllegalStateException("Email taken!");
            }
            student.setEmail(email);
        }
    }


    //Static list for initial testing.
    //    public List<Student> getStudents(){
    //        return List.of(
    //                new Student(
    //                        1L,
    //                        "Max Delaney",
    //                        "maxdelaney16@gmail.com",
    //                        LocalDate.of(2000, Month.JANUARY, 5),
    //                        21
    //                )
    //        );
    //    }

}
