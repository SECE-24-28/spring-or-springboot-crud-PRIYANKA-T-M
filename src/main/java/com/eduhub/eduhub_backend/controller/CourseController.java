package com.eduhub.eduhub_backend.controller;

import com.eduhub.eduhub_backend.component.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CourseController {
//    @Autowired
//    public Course course;

    @GetMapping("get-courses")
    public ResponseEntity<List<Course>> getCourses(){
        List<Course> courses=new ArrayList<>();
        courses.add(new Course("U23CC941","SLS",4));
        courses.add(new Course("U23CS089","CN",4));
        courses.add(new Course("U23CB791","OS",4));
        courses.add(new Course("U23EC452","SS",4));
        courses.add(new Course("U23CS791","C",3));
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }
    @GetMapping("{course-code}/{sub-Name}/{credits}")
    public ResponseEntity<Course>coursePathVariable(@PathVariable("course-code") String courseCode,
                                                    @PathVariable("sub-Name") String subjectName ,
                                                    @PathVariable("credits") int credits) {
        Course course=new Course(courseCode,subjectName,credits);
        return ResponseEntity.ok(course);

    }
    @GetMapping("query")
    public ResponseEntity<Course> courseRequestVariable(@RequestParam String courseCode,
                                                          @RequestParam String subjectName,
                                                          @RequestParam int credits){
        Course course=new Course(courseCode,subjectName,credits);
        return ResponseEntity.ok(course);
    }
    @PostMapping("create-course")
    public ResponseEntity<Course> createCourse(@RequestBody Course course){
        System.out.println(course.getCourseCode());
        System.out.println(course.getSubjectName());
        System.out.println(course.getCredits());
        return ResponseEntity.ok(course);
    }
    @PutMapping("{course-code}/update-course")
    public ResponseEntity updateCourse(@PathVariable("course-code") String courseCode){
        return ResponseEntity.accepted().body("It doesnt have business logic");
    };

    @DeleteMapping("{course-code}/delete-course")
    public ResponseEntity deleteCourse(@PathVariable("course-code") String courseCode){
        return ResponseEntity.accepted().body("Data removed or deleted successfully");
    }

}
