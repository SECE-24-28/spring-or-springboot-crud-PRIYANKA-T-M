package com.eduhub.eduhub_backend.controller;

import com.eduhub.eduhub_backend.component.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @GetMapping("/student")
    public ResponseEntity<Student> getStudent(){
        Student student=new Student(1,"John","Doe");
        return new ResponseEntity<>(student, HttpStatus.OK);

    }
    @GetMapping("/students")
    public ResponseEntity<List<Student>>getStudents(){
        List<Student>students=new ArrayList<>();
        students.add(new Student(1,"John","Doe"));
        students.add(new Student(2,"Nethra","Harini"));
        students.add(new Student(3,"Priyanka","preethi"));
        return new ResponseEntity<>(students, HttpStatus.OK);

    }
    @GetMapping("/{id}/{first-name}/{last-name}")
    public ResponseEntity<Student> studentPathVariable(@PathVariable("id") int studentId,
    @PathVariable("first-name") String firstName,
    @PathVariable("last-name") String lastName){
        Student student=new Student(studentId,firstName,lastName);
        return ResponseEntity.ok(student);

    }
    @GetMapping("/query")
    public ResponseEntity<Student> studentRequestVariable(@RequestParam int studentId,
                                                          @RequestParam String firstName,
                                                          @RequestParam String lastName){
        Student student=new Student(studentId,firstName,lastName);
        return ResponseEntity.ok(student);
    }
    @PostMapping("/create")
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return ResponseEntity.ok(student);
    }
    @PutMapping("/{id}/update")
    public ResponseEntity updateStudent(@PathVariable("id")int studentId){
        return ResponseEntity.accepted().body("it doesn't have business logic");
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity deleteStudent(@PathVariable("id")int studentId){
        return ResponseEntity.accepted().body("Data removes successfully");
    }


}
