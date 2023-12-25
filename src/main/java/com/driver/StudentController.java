package com.driver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("students")
public class StudentController {

    HashMap<String,Student> studentHashMap = new HashMap<>();
    HashMap<String,Teacher> teacherHashMap = new HashMap<>();
    HashMap<String, List<String>>teacherStudentHashMap = new HashMap<>();

    @PostMapping("/add-student")
    public ResponseEntity<String> addStudent(@RequestBody Student student){

        Student student1 = new Student(student.getName(),student.getAge(),student.getAverageScore());
        studentHashMap.put(student1.getName(),student1);


        return new ResponseEntity<>("New student added successfully", HttpStatus.CREATED);
    }

    @PostMapping("/add-teacher")
    public ResponseEntity<String> addTeacher(@RequestBody Teacher teacher){

        Teacher teacher1 = new Teacher(teacher.getName(),teacher.getNumberOfStudents(),teacher.getAge());
        teacherHashMap.put(teacher1.getName(),teacher1);

        return new ResponseEntity<>("New teacher added successfully", HttpStatus.CREATED);
    }

    @PutMapping("/add-student-teacher-pair")
    public ResponseEntity<String> addStudentTeacherPair(@RequestParam String student, @RequestParam String teacher){

        if (!teacherHashMap.containsKey((teacher)))
        {
            return new ResponseEntity<>("teacher not found", HttpStatus.NOT_FOUND);
        }
        if (!studentHashMap.containsKey((student)))
        {
            return new ResponseEntity<>("student not found", HttpStatus.NOT_FOUND);
        }

        Teacher teacher1 = teacherHashMap.get(teacher);
        Student student1 = studentHashMap.get(student);
        if(teacherStudentHashMap.containsKey(teacher1.getName()))
        {
            teacherStudentHashMap.get(teacher1.getName()).add(student1.getName());
        }
        else {
            List<String> arr = new ArrayList<>();
            arr.add(student1.getName());
            teacherStudentHashMap.put(teacher1.getName(), arr);
        }


        return new ResponseEntity<>("New student-teacher pair added successfully", HttpStatus.CREATED);
    }

    @GetMapping("/get-student-by-name/{name}")
    public ResponseEntity<Student> getStudentByName(@PathVariable String name){
        if(!studentHashMap.containsKey(name))
        {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
        Student student = null; // Assign student by calling service layer method
        student = studentHashMap.get(name);

        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @GetMapping("/get-teacher-by-name/{name}")
    public ResponseEntity<Teacher> getTeacherByName(@PathVariable String name){
        if(!teacherHashMap.containsKey(name))
        {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
        Teacher teacher = null; // Assign student by calling service layer method
        teacher = teacherHashMap.get(name);

        return new ResponseEntity<>(teacher, HttpStatus.CREATED);
    }

    @GetMapping("/get-students-by-teacher-name/{teacher}")
    public ResponseEntity<List<String>> getStudentsByTeacherName(@PathVariable String teacher){

        if(!teacherStudentHashMap.containsKey(teacher)) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        List<String> students = null; // Assign list of student by calling service layer method
        students = teacherStudentHashMap.get(teacher);

        return new ResponseEntity<>(students, HttpStatus.CREATED);
    }

    @GetMapping("/get-all-students")
    public ResponseEntity<List<String>> getAllStudents(){
        List<String> students = null; // Assign list of student by calling service layer method
         students = new ArrayList<>();
        if(!studentHashMap.isEmpty()) {
            for (String name : studentHashMap.keySet()) {
//                assert students != null;
                students.add(studentHashMap.get(name).getName());
            }
        }




        return new ResponseEntity<>(students, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-teacher-by-name")
    public ResponseEntity<String> deleteTeacherByName(@RequestParam String teacher){
        if(!teacherHashMap.containsKey(teacher)) {
            return new ResponseEntity<>("teacher does not exist", HttpStatus.BAD_REQUEST);
        }
        teacherHashMap.remove(teacher);
        // || !teacherStudentHashMap.containsKey(teacher)
        // && !teacherStudentHashMap.containsKey(teacher)
        //teacherStudentHashMap.remove(teacher);

        return new ResponseEntity<>(teacher + " removed successfully", HttpStatus.CREATED);
    }
    @DeleteMapping("/delete-all-teachers")
    public ResponseEntity<String> deleteAllTeachers(){

        teacherHashMap.clear();
        teacherStudentHashMap.clear();

        return new ResponseEntity<>("All teachers deleted successfully", HttpStatus.CREATED);
    }
}
