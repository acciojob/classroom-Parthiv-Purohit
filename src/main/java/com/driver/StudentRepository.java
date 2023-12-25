package com.driver;

import java.util.*;

import org.springframework.stereotype.Repository;


@Repository
public class StudentRepository {

    Map<String,Student>student_H_M;
    Map<String,Teacher>teacher_H_M;
    Map<String,List<Student>>studentANDteacher_H_M;


    public StudentRepository() {
        student_H_M =new HashMap<>();
        teacher_H_M =new HashMap<>();
        studentANDteacher_H_M =new HashMap<>();
    }

    public void addSTUDEnt(Student stU) {
        student_H_M.put(stU.getName(), stU);
    }
    public void addTEACHer(Teacher tch) {
        teacher_H_M.put(tch.getName(),tch);
    }
    public void add_S_T_Pair(String studentes,String teacher) {

        if(studentANDteacher_H_M.containsKey(teacher)){
            List<Student>studentsList=studentANDteacher_H_M.get(teacher);
            studentsList.add(student_H_M.get(studentes));
            studentANDteacher_H_M.put(teacher, studentsList);
        }else {
            List<Student>studentsList=new ArrayList<>();
            studentsList.add(student_H_M.get(studentes));
            studentANDteacher_H_M.put(teacher, studentsList);
        }
    }

    public Student getstudenTbynamE(String name) {
        return student_H_M.get(name);

    }

    public Teacher getteacheRbynamE(String name) {

        return teacher_H_M.get(name);

    }

    public List<String>getStudentsByTeacherNAME(String teacher_name){

        List<String>students_Name=new ArrayList<>();
        if(studentANDteacher_H_M.containsKey(teacher_name)) {
            List<Student>stu_list=studentANDteacher_H_M.get(teacher_name);
            for(Student stU:stu_list) {
                students_Name.add(stU.getName());
            }
        }
        return students_Name;
    }

    public List<String>getallSTUDEnt(){
        List<String>all_students=new ArrayList<>();
        for(String h:student_H_M.keySet())
            all_students.add(h);
        return all_students;


    }

    public void deleteTEACHERbyName(String teacher_name) {
        if(studentANDteacher_H_M.containsKey(teacher_name)) {
            List<Student>all_students=studentANDteacher_H_M.get(teacher_name);
            for(Student stU:all_students) {
                student_H_M.remove(stU.getName());
            }
            teacher_H_M.remove(teacher_name);
            studentANDteacher_H_M.remove(teacher_name);

        }else {
            if(teacher_H_M.containsKey(teacher_name))
                teacher_H_M.remove(teacher_name);
            return;
        }

    }

    public void deleteallTEACHER() {
        for(String name:teacher_H_M.keySet()) {
            if(studentANDteacher_H_M.containsKey(name)) {
                List<Student>students=studentANDteacher_H_M.get(name);
                for(Student stU:students) {
                    student_H_M.remove(stU.getName());
                }
                teacher_H_M.remove(name);
                studentANDteacher_H_M.remove(name);

            }else {
                teacher_H_M.remove(name);
            }
        }
    }

}