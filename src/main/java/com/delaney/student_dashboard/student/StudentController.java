/*
 *   Created by Max Delaney
 *   24/11/2021
 *   Tutorial Followed: Amigoscode.
 *
 *   API Layer / API Resources here
 *   GET, POST, PUT, DELETE functionality.
 *   S02
 */

package com.delaney.student_dashboard.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController     //Rest controller makes this class serve restful endpoints.
@RequestMapping(path = "api/v1/student")    //Mapping to reach the end point of my API.
public class StudentController {

    private final StudentService studentService;    //Create an instance of the Student Service inside the controller to be used.
                                                    //The student service class also needs to be a Spring Bean for dependency injection to work correctly. It allows auto instantiating.

    @Autowired  //Autowired makes sure the above studentService instance is injected into the constructor.
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping     //When a HTML get request is received. Run the getStudents method from the StudentService class.
    public List<Student> getStudents(){
        return studentService.getStudents();
    }

    @PostMapping    //When a HTML post request is recieved run the registerNewStudent method from the StudentService class.
    public void registerNewStudent(@RequestBody Student student) {  //@RequestBody uses the body of the request and maps it against the student instance/class.
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}")  //When a HTML delete request is received run the deleteStudent method from StudentService class.
    public void deleteStudent (@PathVariable("studentId") Long studentId){      //studentId is retrieved from the path variable.
        studentService.deleteStudent(studentId);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {
        studentService.updateStudent(studentId, name, email);
    }
}
