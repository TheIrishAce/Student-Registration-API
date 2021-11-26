/*
 *   Created by Max Delaney
 *   24/11/2021
 *   Tutorial Followed: Amigoscode.
 *
 *   Data Access Layer.
 *   S04
 */

package com.delaney.student_dashboard.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository //Delcare as a JPA repository. T = Type of object I want repo to work with. Id = type of repo I want.
public interface StudentRepository extends JpaRepository<Student, Long> {

    //Will transform into SELECT * FROM student WHERE email = ?
    @Query("SELECT s FROM Student s WHERE s.email = ?1")    //Java Persistence Query Language (JPQL) statement.
    Optional<Student> findStudentByEmail(String email);
}
