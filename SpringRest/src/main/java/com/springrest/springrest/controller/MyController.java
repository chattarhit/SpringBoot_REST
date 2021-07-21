package com.springrest.springrest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.springrest.Entities.Course;
import com.springrest.springrest.services.CourseService;

@RestController
public class MyController {
	@Autowired
	private CourseService courseservice;

	@GetMapping("/home")
	public String home() {
		return "This is home";
	}

	/* get the courses */
	@GetMapping("/courses")
	public List<Course> getCourses() {
		return this.courseservice.getCourses();
	}

	@GetMapping("/courses/{CourseId}")
	public Course getCourse(@PathVariable String CourseId) {
		return this.courseservice.getCourse(Long.parseLong(CourseId));
	}

	@PostMapping(path = "/courses", consumes = "application/json")
	public Course addCourse(@RequestBody Course course) {
		return this.courseservice.addCourse(course);

	}

	@PutMapping("/courses")
	public Course updateCourse(@RequestBody Course course) {
		return this.courseservice.updateCourse(course);
	}

	@DeleteMapping("/courses/{CourseId}")
	public ResponseEntity<HttpStatus> deleteCourse(@PathVariable String CourseId) {
		try {
			this.courseservice.deleteCourse(Long.parseLong(CourseId));
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
