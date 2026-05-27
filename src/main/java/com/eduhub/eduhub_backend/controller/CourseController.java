package com.eduhub.eduhub_backend.controller;

import com.eduhub.eduhub_backend.component.Course;
import com.eduhub.eduhub_backend.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
//    @Autowired
//    public Course course;

    static List<Course> courses=new ArrayList<>();
    static{
        courses.add(new Course("U23CC941","SLS",4));
        courses.add(new Course("U23CS089","CN",4));
        courses.add(new Course("U23CB791","OS",4));
        courses.add(new Course("U23EC452","SS",4));
        courses.add(new Course("U23CS791","C",3));
        }
    @GetMapping("/get-courses")
    public ResponseEntity<List<Course>> getCourses(){

        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    //for static data we can do like below too
//    static List<Course> courseList=new ArrayList<>();
//    static{
//        courseList.add(new Course("U23CC941","SLS",4));
//        courseList.add(new Course("U23CS089","CN",4));
//        courseList.add(new Course("U23CB791","OS",4));
//        courseList.add(new Course("U23EC452","SS",4));
//        courseList.add(new Course("U23CS791","C",3));
//
//    }
    //get one course with code
    @GetMapping("/get-course/{code}")
    public ResponseEntity<Course> getCourse(
            @PathVariable("code") String courseCode){
        return courses.stream()
                .filter((Course c) -> c.getCourseCode().equalsIgnoreCase(courseCode)).findFirst().map(ResponseEntity::ok)
                .orElseThrow(()->new ResourceNotFoundException("Course","CourseCode",courseCode));
    }

//    query param for retrieving data with coursecode
    @GetMapping("/search/get-course")
    public ResponseEntity<Course> searchCourse(@RequestParam String courseCode){
        if(courseCode.startsWith("U23")){
            throw new IllegalArgumentException("Starts a numbers in it");
        }
        return courses.stream()
                .filter((Course c)->c.getCourseCode().equalsIgnoreCase(courseCode)).findFirst().map(ResponseEntity::ok)
                .orElseThrow(()->new ResourceNotFoundException("Course","CourseCode",courseCode));
    }
//    retrieving the course details from the URL itself
    @GetMapping("/{course-code}/{sub-Name}/{credits}")
    public ResponseEntity<Course>coursePathVariable(@PathVariable("course-code") String courseCode,
                                                    @PathVariable("sub-Name") String subjectName ,
                                                    @PathVariable("credits") int credits) {
        Course course=new Course(courseCode,subjectName,credits);
        return ResponseEntity.ok(course);

    }
    @GetMapping("/query")
    public ResponseEntity<Course> courseRequestVariable(@RequestParam String courseCode,
                                                          @RequestParam String subjectName,
                                                          @RequestParam int credits){
        Course course=new Course(courseCode,subjectName,credits);
        return ResponseEntity.ok(course);
    }
    @PostMapping("/create-course")
    public ResponseEntity<Course> createCourse(@RequestBody Course course){
        System.out.println(course.getCourseCode());
        System.out.println(course.getSubjectName());
        System.out.println(course.getCredits());
        return ResponseEntity.ok(course);
    }

    @PutMapping("/update-course/{code}")
    public ResponseEntity<Course> updateCourse(@PathVariable String courseCode, @RequestBody Course updatedCourse){
        Course course=courses.stream()
                .filter((Course c)->c.getCourseCode().equalsIgnoreCase(courseCode))
                .findFirst().orElseThrow(()->new ResourceNotFoundException("Course","CourseCode",courseCode));
        course.setSubjectName(updatedCourse.getSubjectName());
        course.setCredits(updatedCourse.getCredits());
        return ResponseEntity.ok(course);
    }

    @DeleteMapping("/delete-course/{course-code}")
    public ResponseEntity deleteCourse(@PathVariable("course-code") String courseCode){
        Course course=courses.stream().filter((Course c)->c.getCourseCode().equalsIgnoreCase(courseCode))
                .findFirst().orElseThrow(()->new ResourceNotFoundException("Course","CourseCode",courseCode));
        courses.remove(course);
        return ResponseEntity.accepted().body("Data removed or deleted successfully");
    }

    @PutMapping("/query/{coursecode}")
    public String quueryCourse(@PathVariable String courseCode) throws Exception{
        if(courseCode.startsWith("*")){
            throw new IllegalArgumentException("it is having a special character");
        }
        else if(courseCode.startsWith("6")){
            throw new Exception();
        }
        return courseCode;
    }

}
