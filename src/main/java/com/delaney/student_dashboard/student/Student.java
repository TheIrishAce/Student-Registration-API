/*
 *   Created by Max Delaney
 *   24/11/2021
 *   Tutorial Followed: Amigoscode.
 *
 *   Base Student Class
 *   S01
 */

package com.delaney.student_dashboard.student;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

//To map student to database @Entity & @Table is used.
@Entity //Tell Hibernate this class is a DB entity.
@Table  //Tell it's a table.

public class Student {
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )

    @GeneratedValue (
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )

    private Long id;
    private String name;
    private String email;
    private LocalDate dob;
    @Transient  //Transient makes it so the variable or field in the database won't be a column but the value of age is still relevant and calculated in the getAge() method.
    private int age;

    //Empty Constructor.
    public Student() {
    }

    //Constructor for all variables including ID.
    public Student(Long id, String name, String email, LocalDate dob) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    //Constructor for all variables except for ID, allows database to generate an ID.
    public Student(String name, String email, LocalDate dob) {
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public int getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", age=" + age +
                '}';
    }
}
