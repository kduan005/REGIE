package com.regie;

import org.junit.Test;

import static org.junit.Assert.*;

public class StudentTest {

    @Test
    public void register(String name) {
        Student mockStudent = new Student("fakeStudent", 1, "fakeemail@mail.com");
        Course mockCourse = new Course(name, 000, "fakeFaculty");
        mockStudent.registerCourse(mockCourse);
        assertTrue(mockStudent.courses.contains(mockCourse));
    }

    @Test
    public void drop(String name) {
        Student mockStudent = new Student("fakeStudent", 1, "fakeemail@mail.com");
        Course mockCourse = new Course(name, 000, "fakeFaculty");
        mockStudent.dropCourse(mockCourse);
        assertFalse(mockStudent.courses.contains(mockCourse));
    }

    @Test
    public void printCourse() {

    }
}